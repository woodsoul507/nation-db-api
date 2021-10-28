package me.givo.nationdbapiproject.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import me.givo.nationdbapiproject.dto.ContinentDto;
import me.givo.nationdbapiproject.model.Continent;
import me.givo.nationdbapiproject.repository.IContinentsJpaRepository;


@Service
public class ContinentsServiceImpl implements IContinentsService {

    private final IContinentsJpaRepository continentsJpaRepository;
    private final ModelMapper modelMapper;

    public ContinentsServiceImpl(IContinentsJpaRepository continentsJpaRepository, ModelMapper modelMapper) {
        this.continentsJpaRepository = continentsJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "findContinentByName", allEntries = true),
            @CacheEvict(value = "findContinentById", allEntries = true),
            @CacheEvict(value = "findAllContinents", allEntries = true) })
    public ContinentDto create(String name) {
        ContinentDto continentDto = new ContinentDto(name);
        Continent continentsEntity = modelMapper.map(continentDto, Continent.class);
        continentsJpaRepository.save(continentsEntity);
        return modelMapper.map(continentsEntity, ContinentDto.class);
    }

    @Override
    @Cacheable("findAllContinents")
    public List<ContinentDto> findAll() {
        List<Continent> entity = continentsJpaRepository.findAll();
        List<ContinentDto> dto = entity.stream().map(e -> modelMapper.map(e, ContinentDto.class)).toList();
        return dto;
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "findContinentByName", allEntries = true),
            @CacheEvict(value = "findContinentById", allEntries = true),
            @CacheEvict(value = "findAllContinents", allEntries = true) })
    public void delete(Integer id) {
        continentsJpaRepository.deleteById(id);
    }

}

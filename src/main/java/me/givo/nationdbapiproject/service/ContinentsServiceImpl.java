package me.givo.nationdbapiproject.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.givo.nationdbapiproject.dto.ContinentsDto;
import me.givo.nationdbapiproject.model.Continent;
import me.givo.nationdbapiproject.repository.IContinentsJpaRepository;

@Service
@Validated
public class ContinentsServiceImpl implements IContinentsService {

    private IContinentsJpaRepository continentsJpaRepository;
    private ModelMapper modelMapper;

    public ContinentsServiceImpl(IContinentsJpaRepository continentsJpaRepository, ModelMapper modelMapper) {
        this.continentsJpaRepository = continentsJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "findContinentByName", allEntries = true),
            @CacheEvict(value = "findContinentById", allEntries = true),
            @CacheEvict(value = "findAllContinents", allEntries = true) })
    public ContinentsDto create(ContinentsDto continentsDto) {
        Continent continentsEntity = modelMapper.map(continentsDto, Continent.class);
        continentsJpaRepository.save(continentsEntity);
        return modelMapper.map(continentsEntity, ContinentsDto.class);
    }

    @Override
    @Cacheable("findContinentByName")
    public ContinentsDto findByName(String name) {
        Continent entity = continentsJpaRepository.findByName(name);
        ContinentsDto dto = modelMapper.map(entity, ContinentsDto.class);
        return dto;
    }

    @Override
    @Cacheable("findContinentById")
    public ContinentsDto findById(Integer id) {
        Continent entity = continentsJpaRepository.getById(id);
        ContinentsDto dto = modelMapper.map(entity, ContinentsDto.class);
        return dto;
    }

    @Override
    @Cacheable("findAllContinents")
    public List<ContinentsDto> findAll() {
        List<Continent> entity = continentsJpaRepository.findAll();
        List<ContinentsDto> dto = entity.stream().map(e -> modelMapper.map(e, ContinentsDto.class)).toList();
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

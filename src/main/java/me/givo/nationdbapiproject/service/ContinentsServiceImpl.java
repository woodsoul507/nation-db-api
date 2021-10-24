package me.givo.nationdbapiproject.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.givo.nationdbapiproject.dto.ContinentDto;
import me.givo.nationdbapiproject.model.Continent;
import me.givo.nationdbapiproject.repository.IContinentsJpaRepository;

import javax.validation.Valid;

@Service
@Validated
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
        Continent continentsEntity = modelMapper.map(validDto(continentDto), Continent.class);
        continentsJpaRepository.save(continentsEntity);
        return modelMapper.map(continentsEntity, ContinentDto.class);
    }

    @Override
    @Cacheable("findContinentByName")
    public ContinentDto findByName(String name) {
        Continent entity = continentsJpaRepository.findByName(name);
        ContinentDto dto = modelMapper.map(entity, ContinentDto.class);
        return dto;
    }

    @Override
    @Cacheable("findContinentById")
    public ContinentDto findById(Integer id) {
        Continent entity = continentsJpaRepository.getById(id);
        ContinentDto dto = modelMapper.map(entity, ContinentDto.class);
        return dto;
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

    @Override
    public ContinentDto validDto(@Valid ContinentDto dto) {
        return dto;
    }

}

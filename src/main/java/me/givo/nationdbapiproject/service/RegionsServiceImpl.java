package me.givo.nationdbapiproject.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.givo.nationdbapiproject.dto.RegionDto;
import me.givo.nationdbapiproject.model.Region;
import me.givo.nationdbapiproject.repository.IContinentsJpaRepository;
import me.givo.nationdbapiproject.repository.IRegionsJpaRepository;

@Service
@Validated
public class RegionsServiceImpl implements IRegionsService {

    private final IContinentsJpaRepository continentsJpaRepository;
    private final IRegionsJpaRepository regionsJpaRepository;
    private final ModelMapper modelMapper;

    public RegionsServiceImpl(IRegionsJpaRepository regionsJpaRepository,
            IContinentsJpaRepository continentsJpaRepository, ModelMapper modelMapper) {
        this.continentsJpaRepository = continentsJpaRepository;
        this.regionsJpaRepository = regionsJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "findAllRegions", allEntries = true),
            @CacheEvict(value = "findRegionById", allEntries = true),
            @CacheEvict(value = "findRegionByName", allEntries = true) })
    public RegionDto create(String regionName, String continentName) {
        RegionDto regionDto = new RegionDto(regionName);
        Region regionsEntity = modelMapper.map(validDto(regionDto), Region.class);
        regionsEntity.setContinents(continentsJpaRepository.findByName(continentName));
        regionsJpaRepository.save(regionsEntity);
        regionDto = modelMapper.map(regionsEntity, RegionDto.class);
        return regionDto;
    }

    @Override
    @Cacheable("findAllRegions")
    public List<RegionDto> findAll() {
        List<Region> entity = regionsJpaRepository.findAll();
        List<RegionDto> dto = entity.stream()
                .map(e -> modelMapper.typeMap(Region.class, RegionDto.class)
                        .addMapping(r -> r.getContinents().getContinentId(), RegionDto::setContinentId).map(e))
                .toList();
        return dto;
    }

    @Override
    @Cacheable("findRegionById")
    public RegionDto findById(Integer id) {
        Region entity = regionsJpaRepository.getById(id);
        RegionDto dto = modelMapper.typeMap(Region.class, RegionDto.class)
                .addMapping(r -> r.getContinents().getContinentId(), RegionDto::setContinentId).map(entity);
        return dto;
    }

    @Override
    @Cacheable("findRegionByName")
    public RegionDto findByName(String name) {
        Region entity = regionsJpaRepository.findByName(name);
        RegionDto dto = modelMapper.typeMap(Region.class, RegionDto.class)
                .addMapping(r -> r.getContinents().getContinentId(), RegionDto::setContinentId).map(entity);
        return dto;
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "findAllRegions", allEntries = true),
            @CacheEvict(value = "findRegionById", allEntries = true),
            @CacheEvict(value = "findRegionByName", allEntries = true) })
    public void delete(Integer id) {
        regionsJpaRepository.deleteById(id);
    }

    @Override
    public RegionDto validDto(@Valid RegionDto dto) {
        return dto;
    }

}

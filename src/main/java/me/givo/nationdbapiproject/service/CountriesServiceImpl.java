package me.givo.nationdbapiproject.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.givo.nationdbapiproject.dto.CountriesDto;
import me.givo.nationdbapiproject.model.Country;
import me.givo.nationdbapiproject.repository.ICountriesJpaRepository;
import me.givo.nationdbapiproject.repository.IRegionsJpaRepository;

@Service
@Validated
public class CountriesServiceImpl implements ICountriesService {

    private ICountriesJpaRepository countriesJpaRepository;
    private IRegionsJpaRepository regionsJpaRepository;
    private ModelMapper modelMapper;

    public CountriesServiceImpl(IRegionsJpaRepository regionsJpaRepository,
            ICountriesJpaRepository countriesJpaRepository, ModelMapper modelMapper) {
        this.countriesJpaRepository = countriesJpaRepository;
        this.regionsJpaRepository = regionsJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "findCountryByName", allEntries = true),
            @CacheEvict(value = "findCountryById", allEntries = true),
            @CacheEvict(value = "findAllCountries", allEntries = true) })
    public CountriesDto create(String name, BigDecimal area, Long nationalDay, String countryCode2, String countryCode3,
            String region) {
        CountriesDto countryDto = nationalDay == null ? new CountriesDto(name, area, countryCode2, countryCode3)
                : new CountriesDto(name, area, new Date(nationalDay), countryCode2, countryCode3);
        Country countryEntity = modelMapper.map(validDto(countryDto), Country.class);
        countryEntity.setRegions(regionsJpaRepository.findByName(region));
        countriesJpaRepository.save(countryEntity);
        countryDto = modelMapper.map(countryEntity, CountriesDto.class);
        return countryDto;
    }

    @Override
    @Cacheable("findAllCountries")
    public List<CountriesDto> findAll() {
        List<Country> entity = countriesJpaRepository.findAll();
        List<CountriesDto> dto = entity.stream().map(e -> modelMapper.typeMap(Country.class, CountriesDto.class)
                .addMapping(r -> r.getRegions().getRegionId(), CountriesDto::setRegionId).map(e)).toList();
        return dto;
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "findCountryByName", allEntries = true),
            @CacheEvict(value = "findCountryById", allEntries = true),
            @CacheEvict(value = "findAllCountries", allEntries = true) })
    public void delete(Integer id) {
        countriesJpaRepository.deleteById(id);
    }

    @Override
    @Cacheable("findCountryById")
    public CountriesDto findById(Integer id) {
        Country entity = countriesJpaRepository.getById(id);
        CountriesDto dto = modelMapper.typeMap(Country.class, CountriesDto.class)
                .addMapping(r -> r.getRegions().getRegionId(), CountriesDto::setRegionId).map(entity);
        return dto;
    }

    @Override
    @Cacheable("findCountryByName")
    public CountriesDto findByName(String name) {
        Country entity = countriesJpaRepository.findByName(name);
        CountriesDto dto = modelMapper.typeMap(Country.class, CountriesDto.class)
                .addMapping(r -> r.getRegions().getRegionId(), CountriesDto::setRegionId).map(entity);
        return dto;
    }

    @Override
    public CountriesDto validDto(@Valid CountriesDto dto) {
        return dto;
    }

}

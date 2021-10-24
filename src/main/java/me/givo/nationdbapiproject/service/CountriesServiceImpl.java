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

import me.givo.nationdbapiproject.dto.CountryDto;
import me.givo.nationdbapiproject.model.Country;
import me.givo.nationdbapiproject.repository.ICountriesJpaRepository;
import me.givo.nationdbapiproject.repository.IRegionsJpaRepository;

@Service
@Validated
public class CountriesServiceImpl implements ICountriesService {

    private final ICountriesJpaRepository countriesJpaRepository;
    private final IRegionsJpaRepository regionsJpaRepository;
    private final ModelMapper modelMapper;

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
    public CountryDto create(String name, BigDecimal area, Long nationalDay, String countryCode2, String countryCode3,
                             String region) {
        CountryDto countryDto = nationalDay == null ? new CountryDto(name, area, countryCode2, countryCode3)
                : new CountryDto(name, area, new Date(nationalDay), countryCode2, countryCode3);
        Country countryEntity = modelMapper.map(validDto(countryDto), Country.class);
        countryEntity.setRegions(regionsJpaRepository.findByName(region));
        countriesJpaRepository.save(countryEntity);
        countryDto = modelMapper.map(countryEntity, CountryDto.class);
        return countryDto;
    }

    @Override
    @Cacheable("findAllCountries")
    public List<CountryDto> findAll() {
        List<Country> entity = countriesJpaRepository.findAll();
        List<CountryDto> dto = entity.stream().map(e -> modelMapper.typeMap(Country.class, CountryDto.class)
                .addMapping(r -> r.getRegions().getRegionId(), CountryDto::setRegionId).map(e)).toList();
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
    public CountryDto findById(Integer id) {
        Country entity = countriesJpaRepository.getById(id);
        CountryDto dto = modelMapper.typeMap(Country.class, CountryDto.class)
                .addMapping(r -> r.getRegions().getRegionId(), CountryDto::setRegionId).map(entity);
        return dto;
    }

    @Override
    @Cacheable("findCountryByName")
    public CountryDto findByName(String name) {
        Country entity = countriesJpaRepository.findByName(name);
        CountryDto dto = modelMapper.typeMap(Country.class, CountryDto.class)
                .addMapping(r -> r.getRegions().getRegionId(), CountryDto::setRegionId).map(entity);
        return dto;
    }

    @Override
    public CountryDto validDto(@Valid CountryDto dto) {
        return dto;
    }

}

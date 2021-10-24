package me.givo.nationdbapiproject.service;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.givo.nationdbapiproject.dto.CountryStatsDto;
import me.givo.nationdbapiproject.model.Country;
import me.givo.nationdbapiproject.model.CountryStats;
import me.givo.nationdbapiproject.model.CountryStatsId;
import me.givo.nationdbapiproject.repository.ICountriesJpaRepository;
import me.givo.nationdbapiproject.repository.ICountryStatsJpaRepository;

@Service
@Validated
public class CountryStatsServiceImpl implements ICountryStatsService {

    private final ICountryStatsJpaRepository countryStatsJpaRepository;
    private final ICountriesJpaRepository countriesJpaRepository;
    private final ModelMapper modelMapper;

    public CountryStatsServiceImpl(ICountryStatsJpaRepository countryStatsJpaRepository,
            ICountriesJpaRepository countriesJpaRepository, ModelMapper modelMapper) {
        this.countryStatsJpaRepository = countryStatsJpaRepository;
        this.countriesJpaRepository = countriesJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "findAllCountryStats", allEntries = true),
            @CacheEvict(value = "findCountryStatsById", allEntries = true),
            @CacheEvict(value = "findCountryStatsByCountry", allEntries = true),
            @CacheEvict(value = "findCountryStatsByYear", allEntries = true) })
    public CountryStatsDto create(String country, Integer year, Long population, BigDecimal gdp) {
        CountryStatsDto countryStatsDto = new CountryStatsDto(population, gdp);
        CountryStats countryStatsEntity = modelMapper.map(validDto(countryStatsDto), CountryStats.class);
        countryStatsEntity.setId(new CountryStatsId(countriesJpaRepository.findByName(country).getCountryId(), year));
        countryStatsEntity.setCountries(countriesJpaRepository.findByName(country));
        countryStatsJpaRepository.save(countryStatsEntity);
        countryStatsDto = modelMapper.map(countryStatsEntity, CountryStatsDto.class);
        return countryStatsDto;
    }

    @Override
    @Cacheable("findAllCountryStats")
    public List<CountryStatsDto> findAll() {
        List<CountryStats> entity = countryStatsJpaRepository.findAll();
        List<CountryStatsDto> dto = entity.stream().map(e -> modelMapper.map(e, CountryStatsDto.class)).toList();
        return dto;
    }

    @Override
    @Cacheable("findCountryStatsById")
    public CountryStatsDto findById(String country, Integer year) {
        CountryStatsId id = new CountryStatsId(countriesJpaRepository.findByName(country).getCountryId(), year);
        CountryStats entity = countryStatsJpaRepository.getById(id);
        CountryStatsDto dto = modelMapper.map(entity, CountryStatsDto.class);
        return dto;
    }

    @Override
    @Cacheable("findCountryStatsByCountry")
    public List<CountryStatsDto> findByCountry(String country) {
        Country countryEntity = countriesJpaRepository.findByName(country);
        List<CountryStats> entity = countryStatsJpaRepository.findByCountries(countryEntity);
        List<CountryStatsDto> dto = entity.stream().map(e -> modelMapper.map(e, CountryStatsDto.class)).toList();
        return dto;
    }

    @Override
    @Cacheable("findCountryStatsByYear")
    public List<CountryStatsDto> findByYear(Integer year) {
        List<CountryStats> entity = countryStatsJpaRepository.findAll().stream()
                .filter(e -> e.getId().getYear().intValue() == year).toList();
        List<CountryStatsDto> dto = entity.stream().map(e -> modelMapper.map(e, CountryStatsDto.class)).toList();
        return dto;
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "findAllCountryStats", allEntries = true),
            @CacheEvict(value = "findCountryStatsById", allEntries = true),
            @CacheEvict(value = "findCountryStatsByCountry", allEntries = true),
            @CacheEvict(value = "findCountryStatsByYear", allEntries = true) })
    public void delete(String country, Integer year) {
        countryStatsJpaRepository
                .deleteById(new CountryStatsId(countriesJpaRepository.findByName(country).getCountryId(), year));
    }

    @Override
    public CountryStatsDto validDto(@Valid CountryStatsDto dto) {
        return dto;
    }

}

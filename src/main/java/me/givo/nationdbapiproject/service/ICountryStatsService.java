package me.givo.nationdbapiproject.service;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import me.givo.nationdbapiproject.dto.CountryStatsDto;

@Component
public interface ICountryStatsService {

    public CountryStatsDto create(String country, Integer year, Long population, BigDecimal gdp);

    public List<CountryStatsDto> findAll();

    public void delete(String country, Integer year);

    public CountryStatsDto findById(String country, Integer year);

    public List<CountryStatsDto> findByCountry(String country);

    public List<CountryStatsDto> findByYear(Integer year);

    public CountryStatsDto validDto(@Valid CountryStatsDto dto);

}

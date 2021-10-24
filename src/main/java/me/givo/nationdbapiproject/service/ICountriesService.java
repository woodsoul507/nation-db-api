package me.givo.nationdbapiproject.service;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import me.givo.nationdbapiproject.dto.CountryDto;

@Component
public interface ICountriesService {

    public CountryDto create(String name, BigDecimal area, Long nationalDay, String countryCode2, String countryCode3,
                             String region);

    public List<CountryDto> findAll();

    public void delete(Integer id);

    public CountryDto findById(Integer id);

    public CountryDto findByName(String name);

    public CountryDto validDto(@Valid CountryDto dto);

}

package me.givo.nationdbapiproject.service;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import me.givo.nationdbapiproject.dto.CountriesDto;

@Component
public interface ICountriesService {

    public CountriesDto create(String name, BigDecimal area, Long nationalDay, String countryCode2, String countryCode3,
            String region);

    public List<CountriesDto> findAll();

    public void delete(Integer id);

    public CountriesDto findById(Integer id);

    public CountriesDto findByName(String name);

    public CountriesDto validDto(@Valid CountriesDto dto);

}

package me.givo.nationdbapiproject.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import me.givo.nationdbapiproject.dto.CountryLanguagesDto;

@Component
public interface ICountryLanguagesService {

    public CountryLanguagesDto create(String country, String language, Boolean official);

    public List<CountryLanguagesDto> findAll();

    public void delete(String country, String language);

    public CountryLanguagesDto findById(String country, String language);

    public List<CountryLanguagesDto> findByCountry(String country);

    public List<CountryLanguagesDto> findByLanguage(String language);

    public CountryLanguagesDto validDto(@Valid CountryLanguagesDto dto);

}

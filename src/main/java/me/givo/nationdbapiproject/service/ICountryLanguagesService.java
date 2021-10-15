package me.givo.nationdbapiproject.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import me.givo.nationdbapiproject.dto.CountryLanguagesDto;

@Component
public interface ICountryLanguagesService {

    public CountryLanguagesDto create(Boolean official);

    public List<CountryLanguagesDto> findAll();

    public void delete(String country, String language);

    public CountryLanguagesDto findById(Integer id);

    public CountryLanguagesDto findByName(String id);

    public CountryLanguagesDto validDto(@Valid CountryLanguagesDto dto);

}

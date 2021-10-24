package me.givo.nationdbapiproject.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import me.givo.nationdbapiproject.dto.LanguageDto;

@Component
public interface ILanguagesService {

    public LanguageDto create(@Valid LanguageDto languageDto);

    public List<LanguageDto> findAll();

    public void delete(Integer id);

    public LanguageDto findById(Integer id);

    public LanguageDto findByName(String name);

    public LanguageDto validDto(@Valid LanguageDto dto);



    
}

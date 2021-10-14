package me.givo.nationdbapiproject.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import me.givo.nationdbapiproject.dto.LanguagesDto;

@Component
public interface ILanguagesService {

    public LanguagesDto create(@Valid LanguagesDto languagesDto);

    public List<LanguagesDto> findAll();

    public void delete(Integer id);

    public LanguagesDto findById(Integer id);

    public LanguagesDto findByName(String name);

    public LanguagesDto validDto(@Valid LanguagesDto dto);



    
}

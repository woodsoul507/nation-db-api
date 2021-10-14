package me.givo.nationdbapiproject.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import me.givo.nationdbapiproject.dto.RegionsDto;

@Component
public interface IRegionsService {
    public RegionsDto create(String regionName, String continentName);

    public List<RegionsDto> findAll();

    public void delete(Integer id);

    public RegionsDto findById(Integer id);

    public RegionsDto findByName(String name);

    public RegionsDto validDto(@Valid RegionsDto dto);

}

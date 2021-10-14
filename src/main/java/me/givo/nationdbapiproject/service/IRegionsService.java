package me.givo.nationdbapiproject.service;

import java.util.List;

import org.springframework.stereotype.Component;

import me.givo.nationdbapiproject.dto.RegionsDto;

@Component
public interface IRegionsService {
    public RegionsDto create(String regionName, String continentName);

    public List<RegionsDto> getAll();

    public void delete(Integer id);

    public RegionsDto findById(Integer id);

    public RegionsDto findByName(String name);

}

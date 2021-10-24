package me.givo.nationdbapiproject.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import me.givo.nationdbapiproject.dto.RegionDto;

@Component
public interface IRegionsService {
    public RegionDto create(String regionName, String continentName);

    public List<RegionDto> findAll();

    public void delete(Integer id);

    public RegionDto findById(Integer id);

    public RegionDto findByName(String name);

    public RegionDto validDto(@Valid RegionDto dto);

}

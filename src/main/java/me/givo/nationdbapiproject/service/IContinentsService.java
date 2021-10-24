package me.givo.nationdbapiproject.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import me.givo.nationdbapiproject.dto.ContinentDto;

@Component
public interface IContinentsService {
    public ContinentDto create(String name);

    public List<ContinentDto> findAll();

    public void delete(Integer id);

    public ContinentDto findById(Integer id);

    public ContinentDto findByName(String name);

    ContinentDto validDto(@Valid ContinentDto dto);
}

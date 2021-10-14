package me.givo.nationdbapiproject.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import me.givo.nationdbapiproject.dto.ContinentsDto;

@Component
public interface IContinentsService {
    public ContinentsDto create(@Valid ContinentsDto continentsDto);

    public List<ContinentsDto> findAll();

    public void delete(Integer id);

    public ContinentsDto findById(Integer id);

    public ContinentsDto findByName(String name);
}

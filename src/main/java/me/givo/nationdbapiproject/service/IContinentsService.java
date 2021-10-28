package me.givo.nationdbapiproject.service;

import me.givo.nationdbapiproject.dto.ContinentDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IContinentsService {
    public ContinentDto create(String name);

    public List<ContinentDto> findAll();

    public void delete(Integer id);

}

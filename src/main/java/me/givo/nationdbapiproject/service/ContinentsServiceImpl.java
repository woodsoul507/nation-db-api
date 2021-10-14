package me.givo.nationdbapiproject.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.givo.nationdbapiproject.dto.ContinentsDto;
import me.givo.nationdbapiproject.model.Continents;
import me.givo.nationdbapiproject.repository.IContinentsJpaRepository;

@Service
@Validated
public class ContinentsServiceImpl implements IContinentsService {

    private IContinentsJpaRepository continentsJpaRepository;
    private ModelMapper modelMapper;

    public ContinentsServiceImpl(IContinentsJpaRepository continentsJpaRepository, ModelMapper modelMapper) {
        this.continentsJpaRepository = continentsJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ContinentsDto create(ContinentsDto continentsDto) {
        Continents continentsEntity = modelMapper.map(continentsDto, Continents.class);
        continentsJpaRepository.save(continentsEntity);
        return modelMapper.map(continentsEntity, ContinentsDto.class);
    }

    @Override
    public ContinentsDto findByName(String name) {
        Continents entity = continentsJpaRepository.findByName(name);
        ContinentsDto dto = modelMapper.map(entity, ContinentsDto.class);
        return dto;
    }

    @Override
    public ContinentsDto findById(Integer id) {
        Continents entity = continentsJpaRepository.getById(id);
        ContinentsDto dto = modelMapper.map(entity, ContinentsDto.class);
        return dto;
    }

    @Override
    public List<ContinentsDto> getAll() {
        List<Continents> entity = continentsJpaRepository.findAll();
        List<ContinentsDto> dto = entity.stream().map(e -> modelMapper.map(e, ContinentsDto.class)).toList();
        return dto;
    }

    @Override
    public void delete(Integer id) {
        continentsJpaRepository.deleteById(id);
    }

}

package me.givo.nationdbapiproject.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.givo.nationdbapiproject.dto.RegionsDto;
import me.givo.nationdbapiproject.model.Regions;
import me.givo.nationdbapiproject.repository.IContinentsJpaRepository;
import me.givo.nationdbapiproject.repository.IRegionsJpaRepository;

@Service
@Validated
public class RegionsServiceImpl implements IRegionsService {

    private IContinentsJpaRepository IContinentsJpaRepository;
    private IRegionsJpaRepository regionsJpaRepository;
    private ModelMapper modelMapper;

    public RegionsServiceImpl(IRegionsJpaRepository regionsJpaRepository,
            IContinentsJpaRepository continentsJpaRepository, ModelMapper modelMapper) {
        this.IContinentsJpaRepository = continentsJpaRepository;
        this.regionsJpaRepository = regionsJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RegionsDto create(@Valid RegionsDto regionsDto) {
        Regions regionsEntity = modelMapper.map(regionsDto, Regions.class);
        regionsJpaRepository.save(regionsEntity);
        return modelMapper.map(regionsEntity, RegionsDto.class);
    }

    @Override
    public List<RegionsDto> getAll() {
        List<Regions> entity = regionsJpaRepository.findAll();
        List<RegionsDto> dto = entity.stream().map(e -> modelMapper.map(e, RegionsDto.class)).toList();
        return dto;
    }

    @Override
    public void delete(Integer id) {
        regionsJpaRepository.deleteById(id);
    }

    @Override
    public RegionsDto findById(Integer id) {
        Regions entity = regionsJpaRepository.getById(id);
        RegionsDto dto = modelMapper.map(entity, RegionsDto.class);
        return dto;
    }

    @Override
    public RegionsDto findByName(String name) {
        Regions entity = regionsJpaRepository.findByName(name);
        RegionsDto dto = modelMapper.map(entity, RegionsDto.class);
        return dto;
    }

}

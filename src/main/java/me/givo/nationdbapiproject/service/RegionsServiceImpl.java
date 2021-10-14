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

    private IContinentsJpaRepository continentsJpaRepository;
    private IRegionsJpaRepository regionsJpaRepository;
    private ModelMapper modelMapper;

    public RegionsServiceImpl(IRegionsJpaRepository regionsJpaRepository,
            IContinentsJpaRepository continentsJpaRepository, ModelMapper modelMapper) {
        this.continentsJpaRepository = continentsJpaRepository;
        this.regionsJpaRepository = regionsJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RegionsDto create(String regionName, String continentName) {
        RegionsDto regionsDto = new RegionsDto(regionName);
        Regions regionsEntity = modelMapper.map(validDto(regionsDto), Regions.class);
        regionsEntity.setContinents(continentsJpaRepository.findByName(continentName));
        regionsJpaRepository.save(regionsEntity);
        regionsDto = modelMapper.map(regionsEntity, RegionsDto.class);
        return regionsDto;
    }

    @Override
    public List<RegionsDto> findAll() {
        List<Regions> entity = regionsJpaRepository.findAll();
        List<RegionsDto> dto = entity.stream().map(e -> modelMapper.typeMap(Regions.class, RegionsDto.class)
        .addMapping(r -> r.getContinents().getContinentId(), RegionsDto::setContinentId).map(e)).toList();
        return dto;
    }

    @Override
    public void delete(Integer id) {
        regionsJpaRepository.deleteById(id);
    }

    @Override
    public RegionsDto findById(Integer id) {
        Regions entity = regionsJpaRepository.getById(id);
        RegionsDto dto = modelMapper.typeMap(Regions.class, RegionsDto.class)
        .addMapping(r -> r.getContinents().getContinentId(), RegionsDto::setContinentId).map(entity);
        return dto;
    }

    @Override
    public RegionsDto findByName(String name) {
        Regions entity = regionsJpaRepository.findByName(name);
        RegionsDto dto = modelMapper.typeMap(Regions.class, RegionsDto.class)
        .addMapping(r -> r.getContinents().getContinentId(), RegionsDto::setContinentId).map(entity);
        return dto;
    }

    @Override
    public RegionsDto validDto(@Valid RegionsDto dto) {
        return dto;
    }

}

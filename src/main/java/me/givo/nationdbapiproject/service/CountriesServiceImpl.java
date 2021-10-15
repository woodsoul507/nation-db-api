package me.givo.nationdbapiproject.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.givo.nationdbapiproject.dto.CountriesDto;
import me.givo.nationdbapiproject.model.Countries;
import me.givo.nationdbapiproject.repository.ICountriesJpaRepository;
import me.givo.nationdbapiproject.repository.IRegionsJpaRepository;

@Service
@Validated
public class CountriesServiceImpl implements ICountriesService {

    private ICountriesJpaRepository countriesJpaRepository;
    private IRegionsJpaRepository regionsJpaRepository;
    private ModelMapper modelMapper;

    public CountriesServiceImpl(IRegionsJpaRepository regionsJpaRepository,
            ICountriesJpaRepository countriesJpaRepository, ModelMapper modelMapper) {
        this.countriesJpaRepository = countriesJpaRepository;
        this.regionsJpaRepository = regionsJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CountriesDto create(String name, BigDecimal area, Long nationalDay, String countryCode2, String countryCode3,
            String region) {
        CountriesDto countryDto = new CountriesDto(name, area, new Date(nationalDay), countryCode2, countryCode3);
        Countries countryEntity = modelMapper.map(validDto(countryDto), Countries.class);
        countryEntity.setRegions(regionsJpaRepository.findByName(region));
        countriesJpaRepository.save(countryEntity);
        countryDto = modelMapper.map(countryEntity, CountriesDto.class);
        return countryDto;
    }

    @Override
    public List<CountriesDto> findAll() {
        List<Countries> entity = countriesJpaRepository.findAll();
        List<CountriesDto> dto = entity.stream().map(e -> modelMapper.typeMap(Countries.class, CountriesDto.class)
                .addMapping(r -> r.getRegions().getRegionId(), CountriesDto::setRegionId).map(e)).toList();
        return dto;
    }

    @Override
    public void delete(Integer id) {
        countriesJpaRepository.deleteById(id);
    }

    @Override
    public CountriesDto findById(Integer id) {
        Countries entity = countriesJpaRepository.getById(id);
        CountriesDto dto = modelMapper.typeMap(Countries.class, CountriesDto.class)
                .addMapping(r -> r.getRegions().getRegionId(), CountriesDto::setRegionId).map(entity);
        return dto;
    }

    @Override
    public CountriesDto findByName(String name) {
        Countries entity = countriesJpaRepository.findByName(name);
        CountriesDto dto = modelMapper.typeMap(Countries.class, CountriesDto.class)
                .addMapping(r -> r.getRegions().getRegionId(), CountriesDto::setRegionId).map(entity);
        return dto;
    }

    @Override
    public CountriesDto validDto(@Valid CountriesDto dto) {
        return dto;
    }

}

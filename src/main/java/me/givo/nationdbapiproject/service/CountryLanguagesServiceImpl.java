package me.givo.nationdbapiproject.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.givo.nationdbapiproject.dto.CountryLanguagesDto;
import me.givo.nationdbapiproject.repository.ICountriesJpaRepository;
import me.givo.nationdbapiproject.repository.ILanguagesJpaRepository;

@Service
@Validated
public class CountryLanguagesServiceImpl implements ICountryLanguagesService {

    private ICountriesJpaRepository countriesJpaRepository;
    private ILanguagesJpaRepository languagesJpaRepository;
    private ModelMapper modelMapper;

    public CountryLanguagesServiceImpl(ILanguagesJpaRepository languagesJpaRepository,
            ICountriesJpaRepository countriesJpaRepository, ModelMapper modelMapper) {
        this.countriesJpaRepository = countriesJpaRepository;
        this.languagesJpaRepository = languagesJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CountryLanguagesDto create(Boolean official) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CountryLanguagesDto> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(String country, String language) {
        // TODO Auto-generated method stub

    }

    @Override
    public CountryLanguagesDto findById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CountryLanguagesDto findByName(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CountryLanguagesDto validDto(@Valid CountryLanguagesDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

}

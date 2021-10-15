package me.givo.nationdbapiproject.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.givo.nationdbapiproject.dto.CountryLanguagesDto;
import me.givo.nationdbapiproject.model.CountryLanguages;
import me.givo.nationdbapiproject.repository.ICountriesJpaRepository;
import me.givo.nationdbapiproject.repository.ICountryLanguagesJpaRepository;
import me.givo.nationdbapiproject.repository.ILanguagesJpaRepository;

@Service
@Validated
public class CountryLanguagesServiceImpl implements ICountryLanguagesService {

    private ICountryLanguagesJpaRepository countryLanguagesJpaRepository;
    private ICountriesJpaRepository countriesJpaRepository;
    private ILanguagesJpaRepository languagesJpaRepository;
    private ModelMapper modelMapper;

    public CountryLanguagesServiceImpl(ICountryLanguagesJpaRepository countryLanguagesJpaRepository,
            ILanguagesJpaRepository languagesJpaRepository, ICountriesJpaRepository countriesJpaRepository,
            ModelMapper modelMapper) {
        this.countryLanguagesJpaRepository = countryLanguagesJpaRepository;
        this.countriesJpaRepository = countriesJpaRepository;
        this.languagesJpaRepository = languagesJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CountryLanguagesDto create(String country, String language, Boolean official) {
        CountryLanguagesDto countryLanguageDto = new CountryLanguagesDto(official == true ? 1 : 0);
        CountryLanguages countryLanguageEntity = modelMapper.map(validDto(countryLanguageDto), CountryLanguages.class);
        countryLanguageEntity.setCountries(countriesJpaRepository.findByName(country));
        countryLanguageEntity.setLanguages(languagesJpaRepository.findByLanguage(language));
        countryLanguagesJpaRepository.save(countryLanguageEntity);
        countryLanguageDto = modelMapper.map(countryLanguageEntity, CountryLanguagesDto.class);
        return countryLanguageDto;
    }

    @Override
    public List<CountryLanguagesDto> findAll() {
        List<CountryLanguages> entity = countryLanguagesJpaRepository.findAll();
        List<CountryLanguagesDto> dto = entity.stream()
                .map(e -> modelMapper.typeMap(CountryLanguages.class, CountryLanguagesDto.class)
                        .addMappings(r -> r.map(CountryLanguages::getId, CountryLanguagesDto::setCountryId))
                        .addMapping(r -> r.getCountries().getCountryId(), CountryLanguagesDto::setCountryId).map(e))
                .toList();
        return dto;
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

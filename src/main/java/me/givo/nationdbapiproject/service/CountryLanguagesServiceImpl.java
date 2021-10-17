package me.givo.nationdbapiproject.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.givo.nationdbapiproject.dto.CountryLanguagesDto;
import me.givo.nationdbapiproject.model.Countries;
import me.givo.nationdbapiproject.model.CountryLanguages;
import me.givo.nationdbapiproject.model.CountryLanguagesId;
import me.givo.nationdbapiproject.model.Languages;
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
        countryLanguageEntity.setId(new CountryLanguagesId(countriesJpaRepository.findByName(country).getCountryId(),
                languagesJpaRepository.findByLanguage(language).getLanguageId()));
        countryLanguageEntity.setCountries(countriesJpaRepository.findByName(country));
        countryLanguageEntity.setLanguages(languagesJpaRepository.findByLanguage(language));
        countryLanguagesJpaRepository.save(countryLanguageEntity);
        countryLanguageDto = modelMapper.map(countryLanguageEntity, CountryLanguagesDto.class);
        return countryLanguageDto;
    }

    @Override
    public List<CountryLanguagesDto> findAll() {
        List<CountryLanguages> entity = countryLanguagesJpaRepository.findAll();
        List<CountryLanguagesDto> dto = entity.stream().map(e -> modelMapper.map(e, CountryLanguagesDto.class))
                .toList();
        return dto;
    }

    @Override
    public CountryLanguagesDto findById(String country, String language) {
        CountryLanguagesId id = new CountryLanguagesId(countriesJpaRepository.findByName(country).getCountryId(),
                languagesJpaRepository.findByLanguage(language).getLanguageId());
        CountryLanguages entity = countryLanguagesJpaRepository.getById(id);
        CountryLanguagesDto dto = modelMapper.map(entity, CountryLanguagesDto.class);
        return dto;
    }

    @Override
    public List<CountryLanguagesDto> findByCountry(String country) {
        Countries countryEntity = countriesJpaRepository.findByName(country);
        List<CountryLanguages> entity = countryLanguagesJpaRepository.findByCountries(countryEntity);
        List<CountryLanguagesDto> dto = entity.stream().map(e -> modelMapper.map(e, CountryLanguagesDto.class))
                .toList();
        return dto;
    }

    @Override
    public List<CountryLanguagesDto> findByLanguage(String language) {
        Languages languageEntity = languagesJpaRepository.findByLanguage(language);
        List<CountryLanguages> entity = countryLanguagesJpaRepository.findByLanguages(languageEntity);
        List<CountryLanguagesDto> dto = entity.stream().map(e -> modelMapper.map(e, CountryLanguagesDto.class))
                .toList();
        return dto;
    }

    @Override
    public void delete(String country, String language) {
        countryLanguagesJpaRepository
                .deleteById(new CountryLanguagesId(countriesJpaRepository.findByName(country).getCountryId(),
                        languagesJpaRepository.findByLanguage(language).getLanguageId()));
    }

    @Override
    public CountryLanguagesDto validDto(@Valid CountryLanguagesDto dto) {
        return dto;
    }

}

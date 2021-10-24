package me.givo.nationdbapiproject.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.givo.nationdbapiproject.dto.CountryLanguagesDto;
import me.givo.nationdbapiproject.model.Country;
import me.givo.nationdbapiproject.model.CountryLanguage;
import me.givo.nationdbapiproject.model.CountryLanguageId;
import me.givo.nationdbapiproject.model.Language;
import me.givo.nationdbapiproject.repository.ICountriesJpaRepository;
import me.givo.nationdbapiproject.repository.ICountryLanguagesJpaRepository;
import me.givo.nationdbapiproject.repository.ILanguagesJpaRepository;

@Service
@Validated
public class CountryLanguagesServiceImpl implements ICountryLanguagesService {

    private final ICountryLanguagesJpaRepository countryLanguagesJpaRepository;
    private final ICountriesJpaRepository countriesJpaRepository;
    private final ILanguagesJpaRepository languagesJpaRepository;
    private final ModelMapper modelMapper;

    public CountryLanguagesServiceImpl(ICountryLanguagesJpaRepository countryLanguagesJpaRepository,
            ILanguagesJpaRepository languagesJpaRepository, ICountriesJpaRepository countriesJpaRepository,
            ModelMapper modelMapper) {
        this.countryLanguagesJpaRepository = countryLanguagesJpaRepository;
        this.countriesJpaRepository = countriesJpaRepository;
        this.languagesJpaRepository = languagesJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "findAllCountryLanguages", allEntries = true),
            @CacheEvict(value = "findCountryLanguagesById", allEntries = true),
            @CacheEvict(value = "findCountryLanguagesByCountry", allEntries = true),
            @CacheEvict(value = "findCountryLanguagesByLanguage", allEntries = true) })
    public CountryLanguagesDto create(String country, String language, Boolean official) {
        CountryLanguagesDto countryLanguageDto = new CountryLanguagesDto(official == true ? 1 : 0);
        CountryLanguage countryLanguageEntity = modelMapper.map(validDto(countryLanguageDto), CountryLanguage.class);
        countryLanguageEntity.setId(new CountryLanguageId(countriesJpaRepository.findByName(country).getCountryId(),
                languagesJpaRepository.findByLanguage(language).getLanguageId()));
        countryLanguageEntity.setCountries(countriesJpaRepository.findByName(country));
        countryLanguageEntity.setLanguages(languagesJpaRepository.findByLanguage(language));
        countryLanguagesJpaRepository.save(countryLanguageEntity);
        countryLanguageDto = modelMapper.map(countryLanguageEntity, CountryLanguagesDto.class);
        return countryLanguageDto;
    }

    @Override
    @Cacheable("findAllCountryLanguages")
    public List<CountryLanguagesDto> findAll() {
        List<CountryLanguage> entity = countryLanguagesJpaRepository.findAll();
        List<CountryLanguagesDto> dto = entity.stream().map(e -> modelMapper.map(e, CountryLanguagesDto.class))
                .toList();
        return dto;
    }

    @Override
    @Cacheable("findCountryLanguagesById")
    public CountryLanguagesDto findById(String country, String language) {
        CountryLanguageId id = new CountryLanguageId(countriesJpaRepository.findByName(country).getCountryId(),
                languagesJpaRepository.findByLanguage(language).getLanguageId());
        CountryLanguage entity = countryLanguagesJpaRepository.getById(id);
        CountryLanguagesDto dto = modelMapper.map(entity, CountryLanguagesDto.class);
        return dto;
    }

    @Override
    @Cacheable("findCountryLanguagesByCountry")
    public List<CountryLanguagesDto> findByCountry(String country) {
        Country countryEntity = countriesJpaRepository.findByName(country);
        List<CountryLanguage> entity = countryLanguagesJpaRepository.findByCountries(countryEntity);
        List<CountryLanguagesDto> dto = entity.stream().map(e -> modelMapper.map(e, CountryLanguagesDto.class))
                .toList();
        return dto;
    }

    @Override
    @Cacheable("findCountryLanguagesByLanguage")
    public List<CountryLanguagesDto> findByLanguage(String language) {
        Language languageEntity = languagesJpaRepository.findByLanguage(language);
        List<CountryLanguage> entity = countryLanguagesJpaRepository.findByLanguages(languageEntity);
        List<CountryLanguagesDto> dto = entity.stream().map(e -> modelMapper.map(e, CountryLanguagesDto.class))
                .toList();
        return dto;
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "findAllCountryLanguages", allEntries = true),
            @CacheEvict(value = "findCountryLanguagesById", allEntries = true),
            @CacheEvict(value = "findCountryLanguagesByCountry", allEntries = true),
            @CacheEvict(value = "findCountryLanguagesByLanguage", allEntries = true) })
    public void delete(String country, String language) {
        countryLanguagesJpaRepository
                .deleteById(new CountryLanguageId(countriesJpaRepository.findByName(country).getCountryId(),
                        languagesJpaRepository.findByLanguage(language).getLanguageId()));
    }

    @Override
    public CountryLanguagesDto validDto(@Valid CountryLanguagesDto dto) {
        return dto;
    }

}

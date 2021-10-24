package me.givo.nationdbapiproject.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.givo.nationdbapiproject.dto.LanguageDto;
import me.givo.nationdbapiproject.model.Language;
import me.givo.nationdbapiproject.repository.ILanguagesJpaRepository;

@Service
@Validated
public class LanguagesServiceImpl implements ILanguagesService {

    private final ILanguagesJpaRepository languagesJpaRepository;
    private final ModelMapper modelMapper;

    public LanguagesServiceImpl(ILanguagesJpaRepository languagesJpaRepository, ModelMapper modelMapper) {
        this.languagesJpaRepository = languagesJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "findAllLanguages", allEntries = true),
            @CacheEvict(value = "findLanguageById", allEntries = true),
            @CacheEvict(value = "findLanguageByName", allEntries = true) })
    public LanguageDto create(LanguageDto languageDto) {
        Language languagesEntity = modelMapper.map(languageDto, Language.class);
        languagesJpaRepository.save(languagesEntity);
        return modelMapper.map(languagesEntity, LanguageDto.class);
    }

    @Override
    @Cacheable("findAllLanguages")
    public List<LanguageDto> findAll() {
        List<Language> entity = languagesJpaRepository.findAll();
        List<LanguageDto> dto = entity.stream().map(e -> modelMapper.map(e, LanguageDto.class)).toList();
        return dto;
    }

    @Override
    @Cacheable("findLanguageById")
    public LanguageDto findById(Integer id) {
        Language entity = languagesJpaRepository.getById(id);
        LanguageDto dto = modelMapper.map(entity, LanguageDto.class);
        return dto;
    }

    @Override
    @Cacheable("findLanguageByName")
    public LanguageDto findByName(String name) {
        Language entity = languagesJpaRepository.findByLanguage(name);
        LanguageDto dto = modelMapper.map(entity, LanguageDto.class);
        return dto;
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "findAllLanguages", allEntries = true),
            @CacheEvict(value = "findLanguageById", allEntries = true),
            @CacheEvict(value = "findLanguageByName", allEntries = true) })
    public void delete(Integer id) {
        languagesJpaRepository.deleteById(id);
    }

    @Override
    public LanguageDto validDto(@Valid LanguageDto dto) {
        return dto;
    }

}

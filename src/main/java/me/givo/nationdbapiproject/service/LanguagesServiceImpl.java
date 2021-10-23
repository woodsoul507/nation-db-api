package me.givo.nationdbapiproject.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.givo.nationdbapiproject.dto.LanguagesDto;
import me.givo.nationdbapiproject.model.Language;
import me.givo.nationdbapiproject.repository.ILanguagesJpaRepository;

@Service
@Validated
public class LanguagesServiceImpl implements ILanguagesService {

    private ILanguagesJpaRepository languagesJpaRepository;
    private ModelMapper modelMapper;

    public LanguagesServiceImpl(ILanguagesJpaRepository languagesJpaRepository, ModelMapper modelMapper) {
        this.languagesJpaRepository = languagesJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LanguagesDto create(LanguagesDto languagesDto) {
        Language languagesEntity = modelMapper.map(languagesDto, Language.class);
        languagesJpaRepository.save(languagesEntity);
        return modelMapper.map(languagesEntity, LanguagesDto.class);
    }

    @Override
    public List<LanguagesDto> findAll() {
        List<Language> entity = languagesJpaRepository.findAll();
        List<LanguagesDto> dto = entity.stream().map(e -> modelMapper.map(e, LanguagesDto.class)).toList();
        return dto;
    }

    @Override
    public void delete(Integer id) {
        languagesJpaRepository.deleteById(id);
    }

    @Override
    public LanguagesDto findById(Integer id) {
        Language entity = languagesJpaRepository.getById(id);
        LanguagesDto dto = modelMapper.map(entity, LanguagesDto.class);
        return dto;
    }

    @Override
    public LanguagesDto findByName(String name) {
        Language entity = languagesJpaRepository.findByLanguage(name);
        LanguagesDto dto = modelMapper.map(entity, LanguagesDto.class);
        return dto;
    }

    @Override
    public LanguagesDto validDto(@Valid LanguagesDto dto) {
        return dto;
    }

}

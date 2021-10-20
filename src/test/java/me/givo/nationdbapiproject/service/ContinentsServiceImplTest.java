package me.givo.nationdbapiproject.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import me.givo.nationdbapiproject.dto.ContinentsDto;
import me.givo.nationdbapiproject.model.Continents;
import me.givo.nationdbapiproject.repository.IContinentsJpaRepository;

@ExtendWith(MockitoExtension.class)
public class ContinentsServiceImplTest {

    @Mock
    private IContinentsJpaRepository continentsJpaRepository;
    private ContinentsServiceImpl underTest;
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        underTest = new ContinentsServiceImpl(continentsJpaRepository, modelMapper);
    }

    @Test
    void testCreate() {
        // Given
        ContinentsDto continentDto = new ContinentsDto(1, "Asia");
        Continents continentEntity = new Continents(1, "Asia");
        // When
        underTest.create(continentDto);
        // Then
        ArgumentCaptor<ContinentsDto> cArgumentCaptor = ArgumentCaptor.forClass(ContinentsDto.class);

        verify(underTest).create(cArgumentCaptor.capture());

        ContinentsDto capturedContinent = cArgumentCaptor.getValue();

        assertThat(capturedContinent).isEqualTo(continentDto);
    }

    @Test
    void testDelete() {
        // When
        underTest.delete(1);
        // Then
        verify(continentsJpaRepository).deleteById(1);
    }

    @Test
    void testFindAll() {
        // When
        underTest.findAll();
        // Then
        verify(continentsJpaRepository).findAll();
    }

    @Test
    void testFindById() {
        // When
        underTest.findById(1);
        // Then
        verify(continentsJpaRepository).getById(1);
    }

    @Test
    void testFindByName() {
        // When
        underTest.findByName("Asia");
        // Then
        verify(continentsJpaRepository).findByName("Asia");
    }
}

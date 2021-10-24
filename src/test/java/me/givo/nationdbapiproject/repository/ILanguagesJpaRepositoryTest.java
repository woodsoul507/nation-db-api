package me.givo.nationdbapiproject.repository;

import me.givo.nationdbapiproject.model.Language;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ILanguagesJpaRepositoryTest {

    @Autowired
    ILanguagesJpaRepository languagesJpaRepository;

    @AfterEach
    void tearDown() {
        languagesJpaRepository.deleteAll();
    }

    @Test
    @DisplayName("Should find a Language by its name")
    void findByLanguage() {

        // Given
        Language english = new Language("English");
        languagesJpaRepository.save(english);

        // When
        Language expected = languagesJpaRepository.findByLanguage("English");

        // Then
        assertThat(expected.getLanguage().equals("English")).isTrue();

    }
}
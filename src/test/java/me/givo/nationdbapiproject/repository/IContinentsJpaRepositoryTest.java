package me.givo.nationdbapiproject.repository;

import me.givo.nationdbapiproject.model.Continent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IContinentsJpaRepositoryTest {

    @Autowired
    IContinentsJpaRepository continentsJpaRepository;

    @Test
    @DisplayName("Should find a Continent by its name")
    void findByName() {

        // Given
        Continent asia = new Continent("Asia");
        continentsJpaRepository.save(asia);

        // When
        Continent expected = continentsJpaRepository.findByName("Asia");

        // Then
        assertThat(expected.getName().equals(asia.getName())).isTrue();
    }
}
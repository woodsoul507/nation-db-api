package me.givo.nationdbapiproject.repository;

import me.givo.nationdbapiproject.model.Continent;
import me.givo.nationdbapiproject.model.Region;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IRegionsJpaRepositoryTest {

    @Autowired
    IContinentsJpaRepository continentsJpaRepository;
    @Autowired
    IRegionsJpaRepository regionsJpaRepository;

    @AfterEach
    void tearDown() {
        regionsJpaRepository.deleteAll();
        continentsJpaRepository.deleteAll();
    }

    @Test
    @DisplayName("Should find a Region by its name")
    void findByName() {

        // Given
        Continent america = new Continent("America");
        Region caribbean = new Region("Caribbean", america);
        continentsJpaRepository.save(america);
        regionsJpaRepository.save(caribbean);

        // When
        Region expected = regionsJpaRepository.findByName("Caribbean");

        // Then
        assertThat(expected.getName().equals("Caribbean")).isTrue();

    }
}
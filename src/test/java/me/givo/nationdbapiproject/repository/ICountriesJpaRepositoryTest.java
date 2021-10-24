package me.givo.nationdbapiproject.repository;

import me.givo.nationdbapiproject.model.Continent;
import me.givo.nationdbapiproject.model.Country;
import me.givo.nationdbapiproject.model.Region;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ICountriesJpaRepositoryTest {

    @Autowired
    ICountriesJpaRepository countriesJpaRepository;
    @Autowired
    IRegionsJpaRepository regionsJpaRepository;
    @Autowired
    IContinentsJpaRepository continentsJpaRepository;

    static Continent america = new Continent("America");
    static Region caribbean = new Region("Caribbean", america);

    @BeforeEach
    void beforeEach() {
        continentsJpaRepository.save(america);
        regionsJpaRepository.save(caribbean);
    }

    @AfterEach
    void tearDown() {
        countriesJpaRepository.deleteAll();
        regionsJpaRepository.deleteAll();
        continentsJpaRepository.deleteAll();
    }

    @Test
    @DisplayName("Should find a Country by its name")
    void findByName() {

        // Given
        Country panama = new Country("Panama",
                new BigDecimal("112.32"),
                new Date(34554L),
                "PA",
                "PAN",
                caribbean);
        countriesJpaRepository.save(panama);

        // When
        Country expected = countriesJpaRepository.findByName("Panama");
        System.out.println(expected);

        // Then
        assertThat(expected.getName().equals("Panama")).isTrue();
    }

    @Test
    @DisplayName("Should find all Countries")
    @Disabled
    void findAll() {
    }

    @Test
    @DisplayName("Should delete a Country by its id")
    @Disabled
    void deleteById() {
    }

    @Test
    @DisplayName("Should delete a Country by its name")
    @Disabled
    void deleteByName() {
    }
}
package me.givo.nationdbapiproject.repository;

import me.givo.nationdbapiproject.model.Continent;
import me.givo.nationdbapiproject.model.Country;
import me.givo.nationdbapiproject.model.Region;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ICountriesJpaRepositoryTest {

    @Autowired
    ICountriesJpaRepository countriesJpaRepository;
    @Autowired
    IRegionsJpaRepository regionsJpaRepository;
    @Autowired
    IContinentsJpaRepository continentsJpaRepository;

    @BeforeEach
    void beforeEach() {
        Continent america = new Continent("America");
        Region caribbean = new Region("Caribbean", america);
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
                regionsJpaRepository.findByName("Caribbean"));
        countriesJpaRepository.save(panama);

        // When
        Country expected = countriesJpaRepository.findByName("Panama");

        // Then
        assertThat(expected.getName().equals("Panama")).isTrue();

    }

    @Test
    @DisplayName("Should find all Countries")
    void findAll() {

        // Given
        Country panama = new Country("Panama",
                new BigDecimal("112.32"),
                new Date(34554L),
                "PA",
                "PAN",
                regionsJpaRepository.findByName("Caribbean"));
        Country china = new Country("China",
                new BigDecimal("13412.32"),
                new Date(6634554L),
                "CH",
                "CHN",
                regionsJpaRepository.findByName("Caribbean"));
        countriesJpaRepository.saveAll(Arrays.asList(panama, china));

        // When
        List<Country> expected = countriesJpaRepository.findAll();

        // Then
        assertThat(2).isEqualTo(expected.size());

    }

    @Test
    @DisplayName("Should delete a Country by its id")
    void deleteById() {

        // Given
        Country panama = new Country("Panama",
                new BigDecimal("112.32"),
                new Date(34554L),
                "PA",
                "PAN",
                regionsJpaRepository.findByName("Caribbean"));
        countriesJpaRepository.save(panama);

        // When
        countriesJpaRepository.deleteById(countriesJpaRepository.findByName("Panama").getCountryId());
        List<Country> expected = countriesJpaRepository.findAll();

        // Then
        assertThat(expected.size()).isEqualTo(0);

    }

    @Test
    @DisplayName("Should delete a Country by its name")
    void deleteByName() {

        // Given
        Country panama = new Country("Panama",
                new BigDecimal("112.32"),
                new Date(34554L),
                "PA",
                "PAN",
                regionsJpaRepository.findByName("Caribbean"));
        countriesJpaRepository.save(panama);

        // When
        countriesJpaRepository.deleteByName("Panama");
        List<Country> expected = countriesJpaRepository.findAll();

        // Then
        assertThat(expected.size()).isEqualTo(0);

    }
}
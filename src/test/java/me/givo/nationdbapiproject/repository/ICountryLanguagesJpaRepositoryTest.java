package me.givo.nationdbapiproject.repository;

import me.givo.nationdbapiproject.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ICountryLanguagesJpaRepositoryTest {

    @Autowired
    ICountriesJpaRepository countriesJpaRepository;
    @Autowired
    ILanguagesJpaRepository languagesJpaRepository;
    @Autowired
    ICountryLanguagesJpaRepository countryLanguagesJpaRepository;
    @Autowired
    IRegionsJpaRepository regionsJpaRepository;
    @Autowired
    IContinentsJpaRepository continentsJpaRepository;

    @BeforeEach
    void setUp() {
        Continent america = new Continent("America");
        Region caribbean = new Region("Caribbean", america);
        continentsJpaRepository.save(america);
        regionsJpaRepository.save(caribbean);
        Country panama = new Country("Panama",
                new BigDecimal("23423.22"),
                new Date(43454L),
                "PA",
                "PAN",
                caribbean);
        countriesJpaRepository.save(panama);
        Language english = new Language("English");
        languagesJpaRepository.save(english);
    }

    @AfterEach
    void tearDown() {
        countryLanguagesJpaRepository.deleteAll();
        countriesJpaRepository.deleteAll();
        regionsJpaRepository.deleteAll();
        continentsJpaRepository.deleteAll();
    }

    @Test
    @DisplayName("Should find a CountryLanguages list by Language entity")
    void findByLanguages() {

        // Given
        Country panama = countriesJpaRepository.findByName("Panama");
        Language english = languagesJpaRepository.findByLanguage("English");
        CountryLanguage panamaCountryLanguage = new CountryLanguage(panama,english,0);
        countryLanguagesJpaRepository.save(panamaCountryLanguage);

        // When
        List<CountryLanguage> expected = countryLanguagesJpaRepository.findByLanguages(english);

        // Then
        assertThat(1).isEqualTo(expected.size());

    }

    @Test
    @DisplayName("Should find a CountryLanguages list by Country entity")
    void findByCountries() {

        // Given
        Country panama = countriesJpaRepository.findByName("Panama");
        Language english = languagesJpaRepository.findByLanguage("English");
        CountryLanguage panamaCountryLanguage = new CountryLanguage(panama,english,0);
        countryLanguagesJpaRepository.save(panamaCountryLanguage);

        // When
        List<CountryLanguage> expected = countryLanguagesJpaRepository.findByCountries(panama);

        // Then
        assertThat(1).isEqualTo(expected.size());

    }
}
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
class ICountryStatsJpaRepositoryTest {

    @Autowired
    ICountryStatsJpaRepository countryStatsJpaRepository;
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
        Country panama = new Country("Panama",
                new BigDecimal("23423.22"),
                new Date(43454L),
                "PA",
                "PAN",
                caribbean);
        countriesJpaRepository.save(panama);
    }

    @AfterEach
    void tearDown() {
        countryStatsJpaRepository.deleteAll();
        countriesJpaRepository.deleteAll();
        regionsJpaRepository.deleteAll();
        continentsJpaRepository.deleteAll();
    }

    @Test
    @DisplayName("Should find a CountryStats list by Country entity")
    void findByCountries() {

        // Given
        CountryStats panamaCountryStats = new CountryStats(countriesJpaRepository.findByName("Panama"),
                2021,
                456786L,
                new BigDecimal("33465.34"));
        countryStatsJpaRepository.save(panamaCountryStats);

        // When
        List<CountryStats> expected =
                countryStatsJpaRepository.findByCountries(countriesJpaRepository.findByName("Panama"));

        // Then
        assertThat(1).isEqualTo(expected.size());

    }
}
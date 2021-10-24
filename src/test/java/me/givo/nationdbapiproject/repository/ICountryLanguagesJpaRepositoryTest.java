package me.givo.nationdbapiproject.repository;

import me.givo.nationdbapiproject.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ICountryLanguagesJpaRepositoryTest {

    @Autowired
    ICountriesJpaRepository countriesJpaRepository;
    @Autowired
    ILanguagesJpaRepository languagesJpaRepository;
    @Autowired
    ICountryLanguagesJpaRepository countryLanguagesJpaRepository;

    @Test
    void findByLanguages() {
    }

    @Test
    void findByCountries() {
    }
}
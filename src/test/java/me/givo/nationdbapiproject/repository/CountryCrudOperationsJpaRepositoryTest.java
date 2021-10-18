package me.givo.nationdbapiproject.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import me.givo.nationdbapiproject.model.Continents;
import me.givo.nationdbapiproject.model.Countries;
import me.givo.nationdbapiproject.model.CountryLanguages;
import me.givo.nationdbapiproject.model.Languages;
import me.givo.nationdbapiproject.model.Regions;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CountryCrudOperationsJpaRepositoryTest {

        @Autowired
        private ICountriesJpaRepository countries;
        @Autowired
        private ILanguagesJpaRepository languages;
        @Autowired
        private IRegionsJpaRepository regions;
        @Autowired
        private IContinentsJpaRepository continents;
        @Autowired
        private ICountryLanguagesJpaRepository countryLanguages;

        Continents pulatina = new Continents("Pulatina");
        Regions mastil = new Regions("Mastil", pulatina);
        Languages patuano = new Languages("Patuano");
        Languages xramano = new Languages("Xramano");
        Countries patu = new Countries("Patu", new BigDecimal(444444), new Date(55555L), "PU", "PTU", mastil);
        Countries xrama = new Countries("Xrama", new BigDecimal(999999), new Date(7777L), "XM", "XRA", mastil);
        CountryLanguages cLPatuano = new CountryLanguages(patu, patuano, 0);
        CountryLanguages cLXramano = new CountryLanguages(xrama, xramano, 1);

        @Test
        @Order(1)
        public void saveNewCountryRecord() {

                continents.save(pulatina);

                assertEquals("Pulatina", continents.findByName("Pulatina").getName());

                regions.save(mastil);

                assertEquals("Mastil", regions.findByName("Mastil").getName());

                languages.saveAll(Arrays.asList(patuano, xramano));

                assertEquals("Patuano", languages.findByLanguage("Patuano").getLanguage());
                assertEquals("Xramano", languages.findByLanguage("Xramano").getLanguage());

                countries.saveAll(Arrays.asList(patu, xrama));
                countryLanguages.saveAllAndFlush(Arrays.asList(cLPatuano, cLXramano));
                countries.findByName("Patu").setCountryLanguages(
                                countryLanguages.findByLanguages(patuano).stream().collect(Collectors.toSet()));
                countries.findByName("Xrama").setCountryLanguages(
                                countryLanguages.findByLanguages(xramano).stream().collect(Collectors.toSet()));

                assertEquals("Patu", countries.findByName("Patu").getName());
                assertEquals("Xrama", countries.findByName("Xrama").getName());

        }

        @Test
        @Order(2)
        public void removeNewCountryRecord() {

                countries.delete(countries.findByName("Patu"));
                assertEquals(null, countries.findByName("Patu"));

                countries.delete(countries.findByName("Xrama"));
                assertEquals(null, countries.findByName("Xrama"));

                regions.delete(mastil);
                assertEquals(null, regions.findByName("Mastil"));

                continents.delete(pulatina);
                assertEquals(null, regions.findByName("Pulatina"));

        }

}

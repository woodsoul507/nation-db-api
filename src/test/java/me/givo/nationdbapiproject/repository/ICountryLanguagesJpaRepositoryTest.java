package me.givo.nationdbapiproject.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import me.givo.nationdbapiproject.model.Continents;
import me.givo.nationdbapiproject.model.Countries;
import me.givo.nationdbapiproject.model.CountryLanguages;
import me.givo.nationdbapiproject.model.Languages;
import me.givo.nationdbapiproject.model.Regions;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ICountryLanguagesJpaRepositoryTest {

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

        @Test
        public void saveAndRemoveCountryLanguages() {

                Continents america = new Continents("America");

                continents.save(america);

                Regions caribe = new Regions("Caribe", continents.getById(1));

                regions.save(caribe);

                Languages patuano = new Languages("Patuano");
                Languages xramano = new Languages("Xramano");
                languages.saveAll(Arrays.asList(patuano, xramano));

                Countries patu = new Countries("Patu", new BigDecimal(4345452), new Date(23234344L), "PU", "PTU",
                                regions.findByName("Caribe"));
                Countries xrama = new Countries("Xrama", new BigDecimal(97867), new Date(4434344L), "XM", "XRA",
                                regions.findByName("Caribe"));

                countries.saveAll(Arrays.asList(patu, xrama));

                System.out.println("/");
                System.out.println("|");
                System.out.println("/");

                System.out.println("Creating and saving CountryLanguages");

                System.out.println("/");
                System.out.println("|");
                System.out.println("/");

                CountryLanguages bp1 = new CountryLanguages(countries.findByName("Patu"), languages.getById(1), 0);
                CountryLanguages bp2 = new CountryLanguages(countries.findByName("Xrama"), languages.getById(2), 1);
                countryLanguages.saveAll(Arrays.asList(bp1, bp2));

                System.out.println("/");
                System.out.println("|");
                System.out.println("/");

                System.out.println("Adding CountryLanguages to Countries");

                System.out.println("/");
                System.out.println("|");
                System.out.println("/");

                countries.findByName("Patu").setCountryLanguages(countryLanguages.findAll().stream()
                                .filter(l -> l.getCountries().getName().equals("Patu")).collect(Collectors.toSet()));
                countries.findByName("Xrama").setCountryLanguages(countryLanguages.findAll().stream()
                                .filter(l -> l.getCountries().getName().equals("Xrama")).collect(Collectors.toSet()));

                System.out.println("/");
                System.out.println("|");
                System.out.println("/");

                System.out.println("Adding CountryLanguages to Countries DONE!");

                System.out.println("/");
                System.out.println("|");
                System.out.println("/");

                assertEquals(2, countries.count());

                System.out.println("/");
                System.out.println("|");
                System.out.println("/");

                System.out.println(countries.findByName("Patu"));
                System.out.println(countries.findByName("Xrama"));

                System.out.println("/");
                System.out.println("|");
                System.out.println("/");

                countries.delete(countries.findByName("Patu"));
                countries.delete(countries.findByName("Xrama"));

                System.out.println("/");
                System.out.println("|");
                System.out.println("/");

                System.out.println(countries.findByName("Patu"));
                System.out.println(countries.findByName("Xrama"));

                System.out.println("/");
                System.out.println("|");
                System.out.println("/");

                assertEquals(null, countries.findByName("Patu"));
                assertEquals(null, countries.findByName("Xrama"));

        }

}

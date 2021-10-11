package me.givo.nationdbapiproject.repository;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import me.givo.nationdbapiproject.model.CountryStats;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class StatsCrudOperationsJpaRepositoryTest {

        @Autowired
        private ICountryStatsJpaRepository countryStats;
        @Autowired
        private ICountriesJpaRepository countries;

        @Test
        @Order(1)
        public void saveNewCountryStatRecord() {

                CountryStats afga1 = new CountryStats(countries.getById(2), 2023, (long) 18898898,
                                new BigDecimal("1232323.2323"));

                countryStats.saveAndFlush(afga1);

                assertEquals(2023, countryStats.findById(afga1.getId()).get().getYear());

        }

        @Test
        @Order(2)
        public void removeNewCountryStatRecord() {

                CountryStats afga1 = new CountryStats(countries.getById(2), 2023, (long) 18898898,
                                new BigDecimal("1232323.2323"));

                countryStats.delete(afga1);

                assertNotEquals(afga1, countryStats.findById(afga1.getId()));

        }

}

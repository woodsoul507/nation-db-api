package me.givo.nationdbapiproject.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import me.givo.nationdbapiproject.model.Continents;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class IContinentsJpaRepositoryTest {

    @Autowired
    IContinentsJpaRepository underTest;

    @Test
    @Order(1)
    void shouldCreateContinent() {
        // Given
        Continents continent = new Continents("America");
        // When
        underTest.save(continent);
        Continents expected = underTest.findByName("America");
        // Then
        assertThat(expected).hasNoNullFieldsOrProperties();

    }

    @Test
    void testGetById() {

    }
}

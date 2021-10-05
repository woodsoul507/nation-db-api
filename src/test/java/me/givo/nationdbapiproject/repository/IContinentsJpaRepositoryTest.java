package me.givo.nationdbapiproject.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
// @AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class IContinentsJpaRepositoryTest {

    @Autowired
    private IContinentsJpaRepository repository;

    @Test
    public void shouldBeSevenContinents() {

        assertEquals(7, repository.findAll().size());

    }

    @Test
    public void shouldGetEurope() {

        assertEquals("Europe", repository.findByName("Europe").getName());

    }

}

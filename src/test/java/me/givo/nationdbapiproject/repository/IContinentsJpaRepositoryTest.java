package me.givo.nationdbapiproject.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
// @DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class IContinentsJpaRepositoryTest {

    @Autowired
    private IContinentsJpaRepository repository;

    @Test
    public void shouldBeSevenContinents() {

        assertEquals("Pulatina", repository.findByName("Pulatina").getName());

        assertEquals(8, repository.findAll().size());

        System.out.println(repository.findAll());

        repository.delete(repository.findByName("Pulatina"));

        assertEquals(7, repository.findAll().size());

        System.out.println(repository.findAll());

    }

    @Test
    public void shouldGetEurope() {

        assertEquals("Europe", repository.findByName("Europe").getName());

    }

}

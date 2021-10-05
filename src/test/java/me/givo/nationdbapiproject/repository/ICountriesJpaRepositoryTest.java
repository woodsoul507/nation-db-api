package me.givo.nationdbapiproject.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ICountriesJpaRepositoryTest {
    @Autowired
    private ICountriesJpaRepository repository;

    // @Test
    // public void saveContinents() {

    // Continents patu = new Continents("Patu") {

    // };

    // repository.save(patu);

    // repository.flush();

    // assertEquals(8, repository.findAll().size());

    // }

    @Test
    public void countCountries() {

        // repository.findAll().remove(8);

        // repository.flush();

        System.out.println(repository.count());
        System.out.println(repository.findAll());
        System.out.println(repository.getById(166).getNationalDay());

        // assertEquals(239, repository.findAll().size());
    }

    @Test
    public void shouldGetAngola() {
        System.out.println("|");
        System.out.println("I");
        System.out.println("|");
        System.out.println("National day: " + repository.findByName("Angola").toString());
        System.out.println("|");
        System.out.println("I");
        System.out.println("|");

        assertEquals("Angola", repository.findByName("Angola").getName(), "Not Angola!");
    }
}

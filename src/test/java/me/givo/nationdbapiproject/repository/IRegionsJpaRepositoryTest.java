package me.givo.nationdbapiproject.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class IRegionsJpaRepositoryTest {

    @Autowired
    private IRegionsJpaRepository repository;

    // @Test
    // public void saveContinents() {

    // Continents patu = new Continents("Patu") {

    // };

    // repository.save(patu);

    // repository.flush();

    // assertEquals(8, repository.findAll().size());

    // }

    @Test
    public void countRegions() {

        // repository.findAll().remove(8);

        // repository.flush();

        assertEquals(25, repository.findAll().size());
    }
}

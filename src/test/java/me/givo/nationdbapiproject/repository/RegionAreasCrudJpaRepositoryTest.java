package me.givo.nationdbapiproject.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import me.givo.nationdbapiproject.model.RegionAreas;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RegionAreasCrudJpaRepositoryTest {

    @Autowired
    private IRegionAreasJpaRepository regionAreas;

    @Test
    @Order(1)
    public void saveNewRegionAreasRecord() {

        RegionAreas putalinaArea = new RegionAreas("Putalina", new BigDecimal("899989.8989"));

        regionAreas.save(putalinaArea);

        assertEquals("Putalina", regionAreas.findById(putalinaArea.getRegionName()).get().getRegionName());

    }

    @Test
    @Order(2)
    public void removeNewRegionAreasRecord() {

        RegionAreas putalinaArea = new RegionAreas("Putalina", new BigDecimal("899989.8989"));

        regionAreas.delete(putalinaArea);

        assertNotEquals(putalinaArea, regionAreas.findById(putalinaArea.getRegionName()));

    }

}
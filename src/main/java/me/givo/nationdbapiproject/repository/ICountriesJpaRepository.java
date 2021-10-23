package me.givo.nationdbapiproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.givo.nationdbapiproject.model.Country;

@Repository
public interface ICountriesJpaRepository extends JpaRepository<Country, Integer> {

    @EntityGraph(value = "countries-graph")
    Country findByName(String name);

    @EntityGraph(value = "countries-graph")
    List<Country> findAll();

    @EntityGraph(value = "countries-graph")
    void deleteById(Integer id);

    @EntityGraph(value = "countries-graph")
    void deleteByName(String name);
}
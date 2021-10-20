package me.givo.nationdbapiproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.givo.nationdbapiproject.model.Countries;

@Repository
public interface ICountriesJpaRepository extends JpaRepository<Countries, Integer> {

    @EntityGraph(value = "countries-graph")
    Countries findByName(String name);

    @EntityGraph(value = "countries-graph")
    List<Countries> findAll();

    @EntityGraph(value = "countries-graph")
    void deleteById(Integer id);

    @EntityGraph(value = "countries-graph")
    void deleteByName(String name);
}
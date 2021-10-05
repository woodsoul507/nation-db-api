package me.givo.nationdbapiproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.givo.nationdbapiproject.model.Countries;

@Repository
public interface ICountriesJpaRepository extends JpaRepository<Countries, Integer> {
    // select fields from countries where name='[param]'
    Countries findByName(String name);
}
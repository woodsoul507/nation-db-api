package me.givo.nationdbapiproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.givo.nationdbapiproject.model.Continents;

@Repository
public interface IContinentsJpaRepository extends JpaRepository<Continents, Integer> {
    // select fields from countries where name='[param]'
    Continents findByName(String name);
}
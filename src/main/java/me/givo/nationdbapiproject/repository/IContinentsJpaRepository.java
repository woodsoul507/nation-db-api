package me.givo.nationdbapiproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.givo.nationdbapiproject.model.Continent;

@Repository
public interface IContinentsJpaRepository extends JpaRepository<Continent, Integer> {
    Continent findByName(String name);
}
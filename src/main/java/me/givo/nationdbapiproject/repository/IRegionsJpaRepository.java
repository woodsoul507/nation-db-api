package me.givo.nationdbapiproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.givo.nationdbapiproject.model.Regions;

@Repository
public interface IRegionsJpaRepository extends JpaRepository<Regions, Integer> {
    Regions findByName(String name);
}
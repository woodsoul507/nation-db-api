package me.givo.nationdbapiproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.givo.nationdbapiproject.model.Region;

@Repository
public interface IRegionsJpaRepository extends JpaRepository<Region, Integer> {

    Region findByName(String name);

}
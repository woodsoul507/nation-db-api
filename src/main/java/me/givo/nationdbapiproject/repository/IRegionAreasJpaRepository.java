package me.givo.nationdbapiproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.givo.nationdbapiproject.model.RegionAreas;

public interface IRegionAreasJpaRepository extends JpaRepository<RegionAreas, String> {

}

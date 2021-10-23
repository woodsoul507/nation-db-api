package me.givo.nationdbapiproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.givo.nationdbapiproject.model.RegionArea;

public interface IRegionAreasJpaRepository extends JpaRepository<RegionArea, String> {

}

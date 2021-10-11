package me.givo.nationdbapiproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.givo.nationdbapiproject.model.CountryStats;
import me.givo.nationdbapiproject.model.CountryStatsId;

@Repository
public interface ICountryStatsJpaRepository extends JpaRepository<CountryStats, CountryStatsId> {

}
package me.givo.nationdbapiproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.givo.nationdbapiproject.model.CountryLanguages;
import me.givo.nationdbapiproject.model.CountryLanguagesId;

@Repository
public interface ICountryLanguagesJpaRepository extends JpaRepository<CountryLanguages, CountryLanguagesId> {

}
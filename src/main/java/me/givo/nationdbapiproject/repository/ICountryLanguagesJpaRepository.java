package me.givo.nationdbapiproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.givo.nationdbapiproject.model.Countries;
import me.givo.nationdbapiproject.model.CountryLanguages;
import me.givo.nationdbapiproject.model.CountryLanguagesId;
import me.givo.nationdbapiproject.model.Languages;

@Repository
public interface ICountryLanguagesJpaRepository extends JpaRepository<CountryLanguages, CountryLanguagesId> {
    List<CountryLanguages> findByLanguages(Languages language);

    List<CountryLanguages> findByCountries(Countries countries);
}
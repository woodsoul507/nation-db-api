package me.givo.nationdbapiproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.givo.nationdbapiproject.model.Country;
import me.givo.nationdbapiproject.model.CountryLanguage;
import me.givo.nationdbapiproject.model.CountryLanguageId;
import me.givo.nationdbapiproject.model.Language;

@Repository
public interface ICountryLanguagesJpaRepository extends JpaRepository<CountryLanguage, CountryLanguageId> {

    List<CountryLanguage> findByLanguages(Language language);

    List<CountryLanguage> findByCountries(Country countries);
}
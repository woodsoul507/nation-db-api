package me.givo.nationdbapiproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.givo.nationdbapiproject.model.Language;

@Repository
public interface ILanguagesJpaRepository extends JpaRepository<Language, Integer> {

    Language findByLanguage(String language);

}
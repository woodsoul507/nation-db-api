package me.givo.nationdbapiproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.givo.nationdbapiproject.model.Languages;

@Repository
public interface ILanguagesJpaRepository extends JpaRepository<Languages, Integer> {
}
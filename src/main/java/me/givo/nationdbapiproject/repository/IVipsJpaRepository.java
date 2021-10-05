package me.givo.nationdbapiproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.givo.nationdbapiproject.model.Vips;

@Repository
public interface IVipsJpaRepository extends JpaRepository<Vips, Integer> {
}
package de.wwag.hackathon.team1.repository;

import de.wwag.hackathon.team1.domain.Gruppe;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Gruppe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GruppeRepository extends JpaRepository<Gruppe, Long> {

	List<Gruppe> findByDatumAndRaum_Id(LocalDate datum, Long id);
}

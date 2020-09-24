package de.wwag.hackathon.team1.repository;

import de.wwag.hackathon.team1.domain.Buchung;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Buchung entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BuchungRepository extends JpaRepository<Buchung, Long> {

	List<Buchung> findByDatumAndRaum_Id(LocalDate datum, Long id);
}

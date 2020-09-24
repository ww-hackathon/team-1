package de.wwag.hackathon.team1.repository;

import de.wwag.hackathon.team1.domain.Buchung;
import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Buchung entity.
 */
@Repository
public interface BuchungRepository extends JpaRepository<Buchung, Long> {

	List<Buchung> findAllByDatumAndRaum_Id(LocalDate datum, Long id);

	List<Buchung> findAllByUser_Id(long userId);

}

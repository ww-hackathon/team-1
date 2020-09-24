package de.wwag.hackathon.team1.repository;

import de.wwag.hackathon.team1.domain.Raum;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Raum entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RaumRepository extends JpaRepository<Raum, Long> {

	Long findByHausAndStockwerkAndRiegel(String haus, String stockwerk, String riegel);
}

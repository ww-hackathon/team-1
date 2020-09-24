package de.wwag.hackathon.team1.repository;

import de.wwag.hackathon.team1.domain.Raum;
import de.wwag.hackathon.team1.domain.User;

import java.util.List;
import java.util.Optional;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Raum entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RaumRepository extends JpaRepository<Raum, Long> {

  Raum findByHausAndStockwerkAndRiegel(String haus, String stockwerk, String riegel);
	
	@Query(value = "SELECT DISTINCT HAUS FROM RAUM", nativeQuery = true)
	List<String> findDistinctHaus();
	
	@Query(value = "SELECT DISTINCT Stockwerk FROM RAUM", nativeQuery = true)
	List<String> findDistinctStockwerk();
	
	@Query(value = "SELECT DISTINCT Riegel FROM RAUM", nativeQuery = true)
	List<String> findDistinctRiegel();

}

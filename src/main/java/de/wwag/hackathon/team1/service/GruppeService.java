package de.wwag.hackathon.team1.service;

import de.wwag.hackathon.team1.domain.Gruppe;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Gruppe}.
 */
public interface GruppeService {

    /**
     * Save a Gruppe.
     *
     * @param gruppe the entity to save.
     * @return the persisted entity.
     */
    Gruppe save(Gruppe gruppe);

    /**
     * Get all the gruppes.
     *
     * @return the list of entities.
     */
    List<Gruppe> findAll();

    /**
     * Get all the gruppes for a given date and given raum.
     *
     * @return the list of entities.
     */
    List<Gruppe> findMultipleByDatumAndRaumId(LocalDate datum, Long id);

    /**
     * Get the "id" gruppe.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Gruppe> findOne(Long id);

    /**
     * Delete the "id" gruppe.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

	
}

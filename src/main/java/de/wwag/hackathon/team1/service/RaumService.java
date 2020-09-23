package de.wwag.hackathon.team1.service;

import de.wwag.hackathon.team1.domain.Raum;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Raum}.
 */
public interface RaumService {

    /**
     * Save a raum.
     *
     * @param raum the entity to save.
     * @return the persisted entity.
     */
    Raum save(Raum raum);

    /**
     * Get all the raums.
     *
     * @return the list of entities.
     */
    List<Raum> findAll();


    /**
     * Get the "id" raum.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Raum> findOne(Long id);

    /**
     * Delete the "id" raum.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

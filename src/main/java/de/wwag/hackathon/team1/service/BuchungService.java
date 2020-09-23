package de.wwag.hackathon.team1.service;

import de.wwag.hackathon.team1.domain.Buchung;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Buchung}.
 */
public interface BuchungService {

    /**
     * Save a buchung.
     *
     * @param buchung the entity to save.
     * @return the persisted entity.
     */
    Buchung save(Buchung buchung);

    /**
     * Get all the buchungs.
     *
     * @return the list of entities.
     */
    List<Buchung> findAll();


    /**
     * Get the "id" buchung.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Buchung> findOne(Long id);

    /**
     * Delete the "id" buchung.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

package de.wwag.hackathon.team1.service;

import java.util.Optional;

import de.wwag.hackathon.team1.domain.Raum;
import de.wwag.hackathon.team1.service.dto.RaumauswahlDTO;

/**
 * Service Interface for managing {@link Raumauswahl}.
 */
public interface RaumauswahlService {

	/**
     * Get all the Raumauswahl.
     *
     * @return the list of entities.
     */
	Optional<RaumauswahlDTO> getRaumauswahl();
	
	

}

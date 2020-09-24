package de.wwag.hackathon.team1.service.impl;

import de.wwag.hackathon.team1.service.BuchungService;
import de.wwag.hackathon.team1.service.RaumauswahlService;
import de.wwag.hackathon.team1.service.dto.RaumauswahlDTO;
import de.wwag.hackathon.team1.domain.Buchung;
import de.wwag.hackathon.team1.repository.BuchungRepository;
import de.wwag.hackathon.team1.repository.RaumRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Buchung}.
 */
@Service
@Transactional
public class RaumauswahlServiceImpl implements RaumauswahlService {

    private final Logger log = LoggerFactory.getLogger(RaumauswahlServiceImpl.class);

    private final RaumRepository raumRepository;

    public RaumauswahlServiceImpl(RaumRepository raumRepository) {
        this.raumRepository = raumRepository;
    }

	@Override
	public Optional<RaumauswahlDTO> getRaumauswahl() {
		log.debug("Request to get all Raumauswahl");
		RaumauswahlDTO raumauswahl = new RaumauswahlDTO(raumRepository.findDistinctHaus(), 
				raumRepository.findDistinctRiegel(), raumRepository.findDistinctStockwerk());
		return Optional.of(raumauswahl);
	}

    
}

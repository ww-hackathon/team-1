package de.wwag.hackathon.team1.service.impl;

import de.wwag.hackathon.team1.service.GruppeService;
import de.wwag.hackathon.team1.domain.Gruppe;
import de.wwag.hackathon.team1.repository.GruppeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Gruppe}.
 */
@Service
@Transactional
public class GruppeServiceImpl implements GruppeService {

    private final Logger log = LoggerFactory.getLogger(GruppeServiceImpl.class);

    private final GruppeRepository gruppeRepository;

    public GruppeServiceImpl(GruppeRepository gruppeRepository) {
        this.gruppeRepository = gruppeRepository;
    }

    @Override
    public Gruppe save(Gruppe gruppe) {
        log.debug("Request to save Gruppe : {}", gruppe);
        return gruppeRepository.save(gruppe);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gruppe> findAll() {
        log.debug("Request to get all Gruppes");
        return gruppeRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Gruppe> findOne(Long id) {
        log.debug("Request to get Gruppe : {}", id);
        return gruppeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Gruppe : {}", id);
        gruppeRepository.deleteById(id);
    }

	@Override
	public List<Gruppe> findMultipleByDatumAndRaumId(LocalDate datum, Long id) {
		log.debug("Request to find Gruppen by Datum : {} and Id: {}", datum, id);
		return gruppeRepository.findByDatumAndRaum_Id(datum, id);
	}
}

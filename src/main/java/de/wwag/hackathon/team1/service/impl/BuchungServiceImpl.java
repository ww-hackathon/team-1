package de.wwag.hackathon.team1.service.impl;

import de.wwag.hackathon.team1.service.BuchungService;
import de.wwag.hackathon.team1.domain.Buchung;
import de.wwag.hackathon.team1.repository.BuchungRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Buchung}.
 */
@Service
@Transactional
public class BuchungServiceImpl implements BuchungService {

    private final Logger log = LoggerFactory.getLogger(BuchungServiceImpl.class);

    private final BuchungRepository buchungRepository;

    public BuchungServiceImpl(BuchungRepository buchungRepository) {
        this.buchungRepository = buchungRepository;
    }

    @Override
    public Buchung save(Buchung buchung) {
        log.debug("Request to save Buchung : {}", buchung);
        return buchungRepository.save(buchung);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Buchung> findAll() {
        log.debug("Request to get all Buchungs");
        return buchungRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Buchung> findOne(Long id) {
        log.debug("Request to get Buchung : {}", id);
        return buchungRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Buchung : {}", id);
        buchungRepository.deleteById(id);
    }

	@Override
	public List<Buchung> findMultipleByDatumAndRaumId(LocalDate datum, Long id) {
		log.debug("Request to find Buchung for Datum: {}, Id: {}", datum + ";" + id);
		return buchungRepository.findAllByDatumAndRaum_Id(datum, id);
	}
		
	public List<Buchung> findBuchungByUserId(Long userId) {
		log.debug("Request to get Buchung of user : {}", userId);
		return buchungRepository.findAllByUser_Id(userId);
		
	}
}

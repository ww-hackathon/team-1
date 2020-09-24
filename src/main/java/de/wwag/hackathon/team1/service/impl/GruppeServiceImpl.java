package de.wwag.hackathon.team1.service.impl;

import de.wwag.hackathon.team1.domain.Buchung;
import de.wwag.hackathon.team1.domain.Gruppe;
import de.wwag.hackathon.team1.repository.GruppeRepository;
import de.wwag.hackathon.team1.service.BuchungService;
import de.wwag.hackathon.team1.service.GruppeService;
import de.wwag.hackathon.team1.service.dto.GruppeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private final BuchungService buchungService;

    public GruppeServiceImpl(GruppeRepository gruppeRepository, BuchungService buchungService) {
        this.gruppeRepository = gruppeRepository;
        this.buchungService = buchungService;
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
	public Optional<GruppeDTO> findMultipleByDatumAndRaumId(LocalDate datum, Long raumId) {
		log.debug("Request to find Gruppen by Datum : {} and Id: {}", datum, raumId);

        List<Buchung> erledigteBuchungen = buchungService.findMultipleByDatumAndRaumId(datum, raumId);
        List<Gruppe> gruppen = gruppeRepository.findAll();

        for (Gruppe gruppe : gruppen) {
            for (Buchung buchung : erledigteBuchungen) {
                if (gruppe.getId() == buchung.getGruppe().getId()) {
                    gruppe.setAnzahlPlaetze(gruppe.getAnzahlPlaetze()-1);
                }
            }
        }

        List<Gruppe> freieGruppen = new ArrayList<Gruppe>();

        for (Gruppe gruppe : gruppen) {
            if (gruppe.getAnzahlPlaetze() > 0) {
                freieGruppen.add(gruppe);
            }
        }

        GruppeDTO gruppeDTO = new GruppeDTO();
        gruppeDTO.setGruppenList(freieGruppen);
        return Optional.of(gruppeDTO);
	}
}

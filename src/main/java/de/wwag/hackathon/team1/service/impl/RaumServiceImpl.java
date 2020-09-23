package de.wwag.hackathon.team1.service.impl;

import de.wwag.hackathon.team1.service.RaumService;
import de.wwag.hackathon.team1.domain.Raum;
import de.wwag.hackathon.team1.repository.RaumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Raum}.
 */
@Service
@Transactional
public class RaumServiceImpl implements RaumService {

    private final Logger log = LoggerFactory.getLogger(RaumServiceImpl.class);

    private final RaumRepository raumRepository;

    public RaumServiceImpl(RaumRepository raumRepository) {
        this.raumRepository = raumRepository;
    }

    @Override
    public Raum save(Raum raum) {
        log.debug("Request to save Raum : {}", raum);
        return raumRepository.save(raum);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Raum> findAll() {
        log.debug("Request to get all Raums");
        return raumRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Raum> findOne(Long id) {
        log.debug("Request to get Raum : {}", id);
        return raumRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Raum : {}", id);
        raumRepository.deleteById(id);
    }
}

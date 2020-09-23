package de.wwag.hackathon.team1.web.rest;

import de.wwag.hackathon.team1.domain.Buchung;
import de.wwag.hackathon.team1.service.BuchungService;
import de.wwag.hackathon.team1.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link de.wwag.hackathon.team1.domain.Buchung}.
 */
@RestController
@RequestMapping("/api")
public class BuchungResource {

    private final Logger log = LoggerFactory.getLogger(BuchungResource.class);

    private static final String ENTITY_NAME = "buchung";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BuchungService buchungService;

    public BuchungResource(BuchungService buchungService) {
        this.buchungService = buchungService;
    }

    /**
     * {@code POST  /buchungs} : Create a new buchung.
     *
     * @param buchung the buchung to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new buchung, or with status {@code 400 (Bad Request)} if the buchung has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/buchungs")
    public ResponseEntity<Buchung> createBuchung(@RequestBody Buchung buchung) throws URISyntaxException {
        log.debug("REST request to save Buchung : {}", buchung);
        if (buchung.getId() != null) {
            throw new BadRequestAlertException("A new buchung cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Buchung result = buchungService.save(buchung);
        return ResponseEntity.created(new URI("/api/buchungs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /buchungs} : Updates an existing buchung.
     *
     * @param buchung the buchung to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated buchung,
     * or with status {@code 400 (Bad Request)} if the buchung is not valid,
     * or with status {@code 500 (Internal Server Error)} if the buchung couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/buchungs")
    public ResponseEntity<Buchung> updateBuchung(@RequestBody Buchung buchung) throws URISyntaxException {
        log.debug("REST request to update Buchung : {}", buchung);
        if (buchung.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Buchung result = buchungService.save(buchung);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, buchung.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /buchungs} : get all the buchungs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of buchungs in body.
     */
    @GetMapping("/buchungs")
    public List<Buchung> getAllBuchungs() {
        log.debug("REST request to get all Buchungs");
        return buchungService.findAll();
    }

    /**
     * {@code GET  /buchungs/:id} : get the "id" buchung.
     *
     * @param id the id of the buchung to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the buchung, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/buchungs/{id}")
    public ResponseEntity<Buchung> getBuchung(@PathVariable Long id) {
        log.debug("REST request to get Buchung : {}", id);
        Optional<Buchung> buchung = buchungService.findOne(id);
        return ResponseUtil.wrapOrNotFound(buchung);
    }

    /**
     * {@code DELETE  /buchungs/:id} : delete the "id" buchung.
     *
     * @param id the id of the buchung to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/buchungs/{id}")
    public ResponseEntity<Void> deleteBuchung(@PathVariable Long id) {
        log.debug("REST request to delete Buchung : {}", id);
        buchungService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
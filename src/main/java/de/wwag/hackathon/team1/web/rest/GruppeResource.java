package de.wwag.hackathon.team1.web.rest;

import de.wwag.hackathon.team1.domain.Buchung;
import de.wwag.hackathon.team1.domain.Gruppe;
import de.wwag.hackathon.team1.service.BuchungService;
import de.wwag.hackathon.team1.service.GruppeService;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link de.wwag.hackathon.team1.domain.Gruppe}.
 */
@RestController
@RequestMapping("/api")
public class GruppeResource {

    private final Logger log = LoggerFactory.getLogger(GruppeResource.class);

    private final int DEFAULT_ANZAHLGRUPPEN = 10;
    
    private static final String ENTITY_NAME = "gruppe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GruppeService gruppeService;
    
    private final BuchungService buchungService;

    public GruppeResource(GruppeService gruppeService, BuchungService buchungService) {
        this.gruppeService = gruppeService;
        this.buchungService = buchungService;
    }

    /**
     * {@code POST  /gruppe} : Create a new gruppe.
     *
     * @param gruppe the gruppe to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gruppe, or with status {@code 400 (Bad Request)} if the gruppe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gruppe")
    public ResponseEntity<Gruppe> createGruppe(@RequestBody Gruppe gruppe) throws URISyntaxException {
        log.debug("REST request to save Gruppe : {}", gruppe);
        if (gruppe.getId() != null) {
            throw new BadRequestAlertException("A new gruppe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Gruppe result = gruppeService.save(gruppe);
        return ResponseEntity.created(new URI("/api/gruppe/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gruppe} : Updates an existing gruppe.
     *
     * @param gruppe the gruppe to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gruppe,
     * or with status {@code 400 (Bad Request)} if the gruppe is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gruppe couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gruppe")
    public ResponseEntity<Gruppe> updateGruppe(@RequestBody Gruppe gruppe) throws URISyntaxException {
        log.debug("REST request to update Gruppe : {}", gruppe);
        if (gruppe.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Gruppe result = gruppeService.save(gruppe);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gruppe.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gruppe} : get all the gruppe.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gruppe in body.
     */
    @GetMapping("/gruppe")
    public List<Gruppe> getAllGruppen() {
        log.debug("REST request to get all Gruppe");
        return gruppeService.findAll();
    }

    /**
     * {@code GET  /gruppe/:id} : get the "id" gruppe.
     *
     * @param id the id of the gruppe to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gruppe, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gruppe/{id}")
    public ResponseEntity<Gruppe> getGruppe(@PathVariable Long id) {
        log.debug("REST request to get Gruppe : {}", id);
        Optional<Gruppe> gruppe = gruppeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gruppe);
    }

    /**
     * {@code GET  /gruppe/:datum/raum/:id} : get the "id" and "datum" gruppe.
     * 
     * @param datum the datum of the gruppe to retrieve.
     * @param id the id of the gruppe to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gruppe, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gruppe/{datum}/raum/{id}")
    public ResponseEntity<List<Buchung>> getBuchung(@PathVariable String datum, @PathVariable String id) {
        log.debug("REST request to get Gruppen by Datum: {}, Raum Id: {}", datum, id);
        
        Long raum_id = Long.parseLong(id);
        
        int year = Integer.parseInt(datum.split("-")[0]);
        int month = Integer.parseInt(datum.split("-")[1]);
        int day = Integer.parseInt(datum.split("-")[2]);
        
        LocalDate localDate = LocalDate.of(year, month, day);
        
        List<Buchung> raumBuchungen = buchungService.findMultipleByDatumAndRaumId(localDate, raum_id);
        
        List<Gruppe> raumGruppen= new ArrayList<>();
        
        
        raumGruppen.add(new Gruppe( 1L, "Gruppe 1", 1));
        raumGruppen.add(new Gruppe( 2L, "Gruppe 2", 2));
        raumGruppen.add(new Gruppe( 3L, "Gruppe 3", 2));
        raumGruppen.add(new Gruppe( 4L, "Gruppe 4", 2));
        raumGruppen.add(new Gruppe( 5L, "Gruppe 5", 2));
        raumGruppen.add(new Gruppe( 6L, "Gruppe 6", 2));
        raumGruppen.add(new Gruppe( 7L, "Gruppe 7", 2));
        raumGruppen.add(new Gruppe( 8L, "Gruppe 8", 2));
        raumGruppen.add(new Gruppe( 9L, "Einzelbuero 1", 1));
        raumGruppen.add(new Gruppe( 10L, "Einzelbuero 2", 1));
        raumGruppen.add(new Gruppe( 11L, "Einzelbuero 3", 1));
        
        for(Buchung b : raumBuchungen) {
        	Gruppe gp = b.getGruppe(); 
        	if(gp.getAnzahlPlaetze() == 1) {
        		raumGruppen.remove(gp.getId());
        	}
        	else if(gp.getAnzahlPlaetze() == 2) {
        		
        	}
        }
        
        return null;
    }
    /**
     * {@code DELETE  /gruppe/:id} : delete the "id" gruppe.
     *
     * @param id the id of the gruppe to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gruppe/{id}")
    public ResponseEntity<Void> deleteGruppe(@PathVariable Long id) {
        log.debug("REST request to delete Gruppe : {}", id);
        gruppeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

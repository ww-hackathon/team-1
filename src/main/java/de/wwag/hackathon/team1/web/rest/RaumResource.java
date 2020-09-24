package de.wwag.hackathon.team1.web.rest;

import de.wwag.hackathon.team1.domain.Raum;
import de.wwag.hackathon.team1.service.RaumService;
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
 * REST controller for managing {@link de.wwag.hackathon.team1.domain.Raum}.
 */
@RestController
@RequestMapping("/api")
public class RaumResource {

    private final Logger log = LoggerFactory.getLogger(RaumResource.class);

    private static final String ENTITY_NAME = "raum";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RaumService raumService;

    public RaumResource(RaumService raumService) {
        this.raumService = raumService;
    }

    /**
     * {@code POST  /raums} : Create a new raum.
     *
     * @param raum the raum to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new raum, or with status {@code 400 (Bad Request)} if the raum has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/raums")
    public ResponseEntity<Raum> createRaum(@RequestBody Raum raum) throws URISyntaxException {
        log.debug("REST request to save Raum : {}", raum);
        if (raum.getId() != null) {
            throw new BadRequestAlertException("A new raum cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Raum result = raumService.save(raum);
        return ResponseEntity.created(new URI("/api/raums/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /raums} : Updates an existing raum.
     *
     * @param raum the raum to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated raum,
     * or with status {@code 400 (Bad Request)} if the raum is not valid,
     * or with status {@code 500 (Internal Server Error)} if the raum couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/raums")
    public ResponseEntity<Raum> updateRaum(@RequestBody Raum raum) throws URISyntaxException {
        log.debug("REST request to update Raum : {}", raum);
        if (raum.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Raum result = raumService.save(raum);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, raum.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /raums} : get all the raums.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of raums in body.
     */
    @GetMapping("/raums")
    public List<Raum> getAllRaums() {
        log.debug("REST request to get all Raums");
        return raumService.findAll();
        
    }

    /**
     * {@code GET  /raums/:id} : get the "id" raum.
     *
     * @param id the id of the raum to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the raum, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/raums/{id}")
    public ResponseEntity<Raum> getRaum(@PathVariable Long id) {
        log.debug("REST request to get Raum : {}", id);
        Optional<Raum> raum = raumService.findOne(id);
        return ResponseUtil.wrapOrNotFound(raum);
    }
    
    /**
     * {@code GET  /raums/:haus:stockwer:riegel} : get the "haus, stockwerk, riegel" raum.
     *
     * @param haus the id of the raum to retrieve.
     * @param stockwerk the id of the raum to retrieve.
     * @param riegel the id of the raum to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the raum, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/raums/{haus}/{stockwerk}/{riegel}")
    @ResponseBody
    public ResponseEntity<Long> getRaumId(@PathVariable String haus, String stockwerk, String riegel) {
    	log.debug("REST request to get Raum : {}", haus + stockwerk + riegel);
    	Long raum_id = raumService.findOneByHausStockwerkRiegel(haus, stockwerk, riegel);
    	return ResponseEntity.ok().body(raum_id);
    }

    /**
     * {@code DELETE  /raums/:id} : delete the "id" raum.
     *
     * @param id the id of the raum to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/raums/{id}")
    public ResponseEntity<Void> deleteRaum(@PathVariable Long id) {
        log.debug("REST request to delete Raum : {}", id);
        raumService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

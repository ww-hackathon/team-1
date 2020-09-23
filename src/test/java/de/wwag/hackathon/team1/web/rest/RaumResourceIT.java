package de.wwag.hackathon.team1.web.rest;

import de.wwag.hackathon.team1.WwHackathonTeam1App;
import de.wwag.hackathon.team1.domain.Raum;
import de.wwag.hackathon.team1.repository.RaumRepository;
import de.wwag.hackathon.team1.service.RaumService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RaumResource} REST controller.
 */
@SpringBootTest(classes = WwHackathonTeam1App.class)
@AutoConfigureMockMvc
@WithMockUser
public class RaumResourceIT {

    private static final String DEFAULT_HAUS = "AAAAAAAAAA";
    private static final String UPDATED_HAUS = "BBBBBBBBBB";

    private static final String DEFAULT_RIEGEL = "AAAAAAAAAA";
    private static final String UPDATED_RIEGEL = "BBBBBBBBBB";

    private static final String DEFAULT_STOCKWERK = "AAAAAAAAAA";
    private static final String UPDATED_STOCKWERK = "BBBBBBBBBB";

    @Autowired
    private RaumRepository raumRepository;

    @Autowired
    private RaumService raumService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRaumMockMvc;

    private Raum raum;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Raum createEntity(EntityManager em) {
        Raum raum = new Raum()
            .haus(DEFAULT_HAUS)
            .riegel(DEFAULT_RIEGEL)
            .stockwerk(DEFAULT_STOCKWERK);
        return raum;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Raum createUpdatedEntity(EntityManager em) {
        Raum raum = new Raum()
            .haus(UPDATED_HAUS)
            .riegel(UPDATED_RIEGEL)
            .stockwerk(UPDATED_STOCKWERK);
        return raum;
    }

    @BeforeEach
    public void initTest() {
        raum = createEntity(em);
    }

    @Test
    @Transactional
    public void createRaum() throws Exception {
        int databaseSizeBeforeCreate = raumRepository.findAll().size();
        // Create the Raum
        restRaumMockMvc.perform(post("/api/raums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(raum)))
            .andExpect(status().isCreated());

        // Validate the Raum in the database
        List<Raum> raumList = raumRepository.findAll();
        assertThat(raumList).hasSize(databaseSizeBeforeCreate + 1);
        Raum testRaum = raumList.get(raumList.size() - 1);
        assertThat(testRaum.getHaus()).isEqualTo(DEFAULT_HAUS);
        assertThat(testRaum.getRiegel()).isEqualTo(DEFAULT_RIEGEL);
        assertThat(testRaum.getStockwerk()).isEqualTo(DEFAULT_STOCKWERK);
    }

    @Test
    @Transactional
    public void createRaumWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = raumRepository.findAll().size();

        // Create the Raum with an existing ID
        raum.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRaumMockMvc.perform(post("/api/raums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(raum)))
            .andExpect(status().isBadRequest());

        // Validate the Raum in the database
        List<Raum> raumList = raumRepository.findAll();
        assertThat(raumList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRaums() throws Exception {
        // Initialize the database
        raumRepository.saveAndFlush(raum);

        // Get all the raumList
        restRaumMockMvc.perform(get("/api/raums?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(raum.getId().intValue())))
            .andExpect(jsonPath("$.[*].haus").value(hasItem(DEFAULT_HAUS)))
            .andExpect(jsonPath("$.[*].riegel").value(hasItem(DEFAULT_RIEGEL)))
            .andExpect(jsonPath("$.[*].stockwerk").value(hasItem(DEFAULT_STOCKWERK)));
    }
    
    @Test
    @Transactional
    public void getRaum() throws Exception {
        // Initialize the database
        raumRepository.saveAndFlush(raum);

        // Get the raum
        restRaumMockMvc.perform(get("/api/raums/{id}", raum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(raum.getId().intValue()))
            .andExpect(jsonPath("$.haus").value(DEFAULT_HAUS))
            .andExpect(jsonPath("$.riegel").value(DEFAULT_RIEGEL))
            .andExpect(jsonPath("$.stockwerk").value(DEFAULT_STOCKWERK));
    }
    @Test
    @Transactional
    public void getNonExistingRaum() throws Exception {
        // Get the raum
        restRaumMockMvc.perform(get("/api/raums/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRaum() throws Exception {
        // Initialize the database
        raumService.save(raum);

        int databaseSizeBeforeUpdate = raumRepository.findAll().size();

        // Update the raum
        Raum updatedRaum = raumRepository.findById(raum.getId()).get();
        // Disconnect from session so that the updates on updatedRaum are not directly saved in db
        em.detach(updatedRaum);
        updatedRaum
            .haus(UPDATED_HAUS)
            .riegel(UPDATED_RIEGEL)
            .stockwerk(UPDATED_STOCKWERK);

        restRaumMockMvc.perform(put("/api/raums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRaum)))
            .andExpect(status().isOk());

        // Validate the Raum in the database
        List<Raum> raumList = raumRepository.findAll();
        assertThat(raumList).hasSize(databaseSizeBeforeUpdate);
        Raum testRaum = raumList.get(raumList.size() - 1);
        assertThat(testRaum.getHaus()).isEqualTo(UPDATED_HAUS);
        assertThat(testRaum.getRiegel()).isEqualTo(UPDATED_RIEGEL);
        assertThat(testRaum.getStockwerk()).isEqualTo(UPDATED_STOCKWERK);
    }

    @Test
    @Transactional
    public void updateNonExistingRaum() throws Exception {
        int databaseSizeBeforeUpdate = raumRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRaumMockMvc.perform(put("/api/raums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(raum)))
            .andExpect(status().isBadRequest());

        // Validate the Raum in the database
        List<Raum> raumList = raumRepository.findAll();
        assertThat(raumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRaum() throws Exception {
        // Initialize the database
        raumService.save(raum);

        int databaseSizeBeforeDelete = raumRepository.findAll().size();

        // Delete the raum
        restRaumMockMvc.perform(delete("/api/raums/{id}", raum.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Raum> raumList = raumRepository.findAll();
        assertThat(raumList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

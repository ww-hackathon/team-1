package de.wwag.hackathon.team1.web.rest;

import de.wwag.hackathon.team1.WwHackathonTeam1App;
import de.wwag.hackathon.team1.domain.Buchung;
import de.wwag.hackathon.team1.repository.BuchungRepository;
import de.wwag.hackathon.team1.service.BuchungService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BuchungResource} REST controller.
 */
@SpringBootTest(classes = WwHackathonTeam1App.class)
@AutoConfigureMockMvc
@WithMockUser
public class BuchungResourceIT {

    private static final LocalDate DEFAULT_DATUM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUM = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER = "BBBBBBBBBB";

    @Autowired
    private BuchungRepository buchungRepository;

    @Autowired
    private BuchungService buchungService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBuchungMockMvc;

    private Buchung buchung;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Buchung createEntity(EntityManager em) {
        Buchung buchung = new Buchung()
            .datum(DEFAULT_DATUM)
            .user(DEFAULT_USER);
        return buchung;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Buchung createUpdatedEntity(EntityManager em) {
        Buchung buchung = new Buchung()
            .datum(UPDATED_DATUM)
            .user(UPDATED_USER);
        return buchung;
    }

    @BeforeEach
    public void initTest() {
        buchung = createEntity(em);
    }

    @Test
    @Transactional
    public void createBuchung() throws Exception {
        int databaseSizeBeforeCreate = buchungRepository.findAll().size();
        // Create the Buchung
        restBuchungMockMvc.perform(post("/api/buchungen")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(buchung)))
            .andExpect(status().isCreated());

        // Validate the Buchung in the database
        List<Buchung> buchungList = buchungRepository.findAll();
        assertThat(buchungList).hasSize(databaseSizeBeforeCreate + 1);
        Buchung testBuchung = buchungList.get(buchungList.size() - 1);
        assertThat(testBuchung.getDatum()).isEqualTo(DEFAULT_DATUM);
        assertThat(testBuchung.getUser()).isEqualTo(DEFAULT_USER);
    }

    @Test
    @Transactional
    public void createBuchungWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = buchungRepository.findAll().size();

        // Create the Buchung with an existing ID
        buchung.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBuchungMockMvc.perform(post("/api/buchungen")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(buchung)))
            .andExpect(status().isBadRequest());

        // Validate the Buchung in the database
        List<Buchung> buchungList = buchungRepository.findAll();
        assertThat(buchungList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBuchungs() throws Exception {
        // Initialize the database
        buchungRepository.saveAndFlush(buchung);

        // Get all the buchungList
        restBuchungMockMvc.perform(get("/api/buchungen?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(buchung.getId().intValue())))
            .andExpect(jsonPath("$.[*].datum").value(hasItem(DEFAULT_DATUM.toString())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER)));
    }

    @Test
    @Transactional
    public void getBuchung() throws Exception {
        // Initialize the database
        buchungRepository.saveAndFlush(buchung);

        // Get the buchung
        restBuchungMockMvc.perform(get("/api/buchungen/{id}", buchung.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(buchung.getId().intValue()))
            .andExpect(jsonPath("$.datum").value(DEFAULT_DATUM.toString()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER));
    }
    @Test
    @Transactional
    public void getNonExistingBuchung() throws Exception {
        // Get the buchung
        restBuchungMockMvc.perform(get("/api/buchungen/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBuchung() throws Exception {
        // Initialize the database
        buchungService.save(buchung);

        int databaseSizeBeforeUpdate = buchungRepository.findAll().size();

        // Update the buchung
        Buchung updatedBuchung = buchungRepository.findById(buchung.getId()).get();
        // Disconnect from session so that the updates on updatedBuchung are not directly saved in db
        em.detach(updatedBuchung);
        updatedBuchung
            .datum(UPDATED_DATUM)
            .user(UPDATED_USER);

        restBuchungMockMvc.perform(put("/api/buchungen")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBuchung)))
            .andExpect(status().isOk());

        // Validate the Buchung in the database
        List<Buchung> buchungList = buchungRepository.findAll();
        assertThat(buchungList).hasSize(databaseSizeBeforeUpdate);
        Buchung testBuchung = buchungList.get(buchungList.size() - 1);
        assertThat(testBuchung.getDatum()).isEqualTo(UPDATED_DATUM);
        assertThat(testBuchung.getUser()).isEqualTo(UPDATED_USER);
    }

    @Test
    @Transactional
    public void updateNonExistingBuchung() throws Exception {
        int databaseSizeBeforeUpdate = buchungRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBuchungMockMvc.perform(put("/api/buchungen")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(buchung)))
            .andExpect(status().isBadRequest());

        // Validate the Buchung in the database
        List<Buchung> buchungList = buchungRepository.findAll();
        assertThat(buchungList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBuchung() throws Exception {
        // Initialize the database
        buchungService.save(buchung);

        int databaseSizeBeforeDelete = buchungRepository.findAll().size();

        // Delete the buchung
        restBuchungMockMvc.perform(delete("/api/buchungen/{id}", buchung.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Buchung> buchungList = buchungRepository.findAll();
        assertThat(buchungList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

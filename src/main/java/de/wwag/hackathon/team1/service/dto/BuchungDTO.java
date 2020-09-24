package de.wwag.hackathon.team1.service.dto;

import de.wwag.hackathon.team1.config.Constants;

import de.wwag.hackathon.team1.domain.Authority;
import de.wwag.hackathon.team1.domain.Raum;
import de.wwag.hackathon.team1.domain.User;

import javax.validation.constraints.*;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a buchung.
 */
public class BuchungDTO {

    private Long id;
    


    public BuchungDTO() {
        // Empty constructor needed for Jackson.
    }


}

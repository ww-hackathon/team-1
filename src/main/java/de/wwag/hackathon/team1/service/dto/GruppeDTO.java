package de.wwag.hackathon.team1.service.dto;

import de.wwag.hackathon.team1.config.Constants;
import de.wwag.hackathon.team1.domain.Authority;
import de.wwag.hackathon.team1.domain.Gruppe;
import de.wwag.hackathon.team1.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
public class GruppeDTO {

    List<Gruppe> gruppenList;

    public List<Gruppe> getGruppenList() {
        return gruppenList;
    }

    public void setGruppenList(List<Gruppe> gruppenList) {
        this.gruppenList = gruppenList;
    }
}

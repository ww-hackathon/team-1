package de.wwag.hackathon.team1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Buchung.
 */
@Entity
@Table(name = "buchung")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Buchung implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datum")
    private LocalDate datum;

    @Column(name = "user")
    private String user;

    @Column(name = "gruppe")
    private String gruppe;

    @ManyToOne
    @JsonIgnoreProperties(value = "buchungs", allowSetters = true)
    private Raum raum;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public Buchung datum(LocalDate datum) {
        this.datum = datum;
        return this;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getUser() {
        return user;
    }

    public Buchung user(String user) {
        this.user = user;
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGruppe() {
        return gruppe;
    }

    public Buchung gruppe(String gruppe) {
        this.gruppe = gruppe;
        return this;
    }

    public void setGruppe(String gruppe) {
        this.gruppe = gruppe;
    }

    public Raum getRaum() {
        return raum;
    }

    public Buchung raum(Raum raum) {
        this.raum = raum;
        return this;
    }

    public void setRaum(Raum raum) {
        this.raum = raum;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Buchung)) {
            return false;
        }
        return id != null && id.equals(((Buchung) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Buchung{" +
            "id=" + getId() +
            ", datum='" + getDatum() + "'" +
            ", user='" + getUser() + "'" +
            ", gruppe='" + getGruppe() + "'" +
            "}";
    }
}
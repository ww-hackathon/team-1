package de.wwag.hackathon.team1.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Gruppe.
 */
@Entity
@Table(name = "gruppe")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Gruppe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "anzahl_plaetze")
    private int anzahlPlaetze;

    public Gruppe(Long id, String name, int anzahlPlaetze) {
		this.id = id;
		this.name = name;
		this.anzahlPlaetze = anzahlPlaetze;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Gruppe name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnzahlPlaetze() {
        return anzahlPlaetze;
    }

    public Gruppe anzahlPlaetze(int anzahlPlaetze) {
        this.anzahlPlaetze = anzahlPlaetze;
        return this;
    }

    public void setAnzahlPlaetze(int anzahlPlaetze) {
        this.anzahlPlaetze = anzahlPlaetze;
    }


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Gruppe)) {
            return false;
        }
        return id != null && id.equals(((Gruppe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Gruppe{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", anzahlPlaetze='" + getAnzahlPlaetze() + "'" +
            "}";
    }
}

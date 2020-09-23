package de.wwag.hackathon.team1.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Raum.
 */
@Entity
@Table(name = "raum")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Raum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "haus")
    private String haus;

    @Column(name = "riegel")
    private String riegel;

    @Column(name = "stockwerk")
    private String stockwerk;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHaus() {
        return haus;
    }

    public Raum haus(String haus) {
        this.haus = haus;
        return this;
    }

    public void setHaus(String haus) {
        this.haus = haus;
    }

    public String getRiegel() {
        return riegel;
    }

    public Raum riegel(String riegel) {
        this.riegel = riegel;
        return this;
    }

    public void setRiegel(String riegel) {
        this.riegel = riegel;
    }

    public String getStockwerk() {
        return stockwerk;
    }

    public Raum stockwerk(String stockwerk) {
        this.stockwerk = stockwerk;
        return this;
    }

    public void setStockwerk(String stockwerk) {
        this.stockwerk = stockwerk;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Raum)) {
            return false;
        }
        return id != null && id.equals(((Raum) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Raum{" +
            "id=" + getId() +
            ", haus='" + getHaus() + "'" +
            ", riegel='" + getRiegel() + "'" +
            ", stockwerk='" + getStockwerk() + "'" +
            "}";
    }
}

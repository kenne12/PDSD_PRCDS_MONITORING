/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "facteur_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacteurRegion.findAll", query = "SELECT f FROM FacteurRegion f"),
    @NamedQuery(name = "FacteurRegion.findByIdfacteurRegion", query = "SELECT f FROM FacteurRegion f WHERE f.idfacteurRegion = :idfacteurRegion"),
    @NamedQuery(name = "FacteurRegion.findByObservation", query = "SELECT f FROM FacteurRegion f WHERE f.observation = :observation")})
public class FacteurRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idfacteur_region")
    private Long idfacteurRegion;
    @Size(max = 2147483647)
    private String observation;
    @JoinColumn(name = "idfacteur", referencedColumnName = "idfacteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Facteur idfacteur;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;

    public FacteurRegion() {
    }

    public FacteurRegion(Long idfacteurRegion) {
        this.idfacteurRegion = idfacteurRegion;
    }

    public Long getIdfacteurRegion() {
        return idfacteurRegion;
    }

    public void setIdfacteurRegion(Long idfacteurRegion) {
        this.idfacteurRegion = idfacteurRegion;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Facteur getIdfacteur() {
        return idfacteur;
    }

    public void setIdfacteur(Facteur idfacteur) {
        this.idfacteur = idfacteur;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfacteurRegion != null ? idfacteurRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacteurRegion)) {
            return false;
        }
        FacteurRegion other = (FacteurRegion) object;
        if ((this.idfacteurRegion == null && other.idfacteurRegion != null) || (this.idfacteurRegion != null && !this.idfacteurRegion.equals(other.idfacteurRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.FacteurRegion[ idfacteurRegion=" + idfacteurRegion + " ]";
    }
    
}

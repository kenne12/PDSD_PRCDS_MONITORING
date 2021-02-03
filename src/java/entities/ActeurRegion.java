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
@Table(name = "acteur_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActeurRegion.findAll", query = "SELECT a FROM ActeurRegion a"),
    @NamedQuery(name = "ActeurRegion.findByIdacteurRegion", query = "SELECT a FROM ActeurRegion a WHERE a.idacteurRegion = :idacteurRegion"),
    @NamedQuery(name = "ActeurRegion.findByObservation", query = "SELECT a FROM ActeurRegion a WHERE a.observation = :observation")})
public class ActeurRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idacteur_region")
    private Long idacteurRegion;
    @Size(max = 2147483647)
    private String observation;
    @JoinColumn(name = "idacteur", referencedColumnName = "idacteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Acteur idacteur;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;

    public ActeurRegion() {
    }

    public ActeurRegion(Long idacteurRegion) {
        this.idacteurRegion = idacteurRegion;
    }

    public Long getIdacteurRegion() {
        return idacteurRegion;
    }

    public void setIdacteurRegion(Long idacteurRegion) {
        this.idacteurRegion = idacteurRegion;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Acteur getIdacteur() {
        return idacteur;
    }

    public void setIdacteur(Acteur idacteur) {
        this.idacteur = idacteur;
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
        hash += (idacteurRegion != null ? idacteurRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActeurRegion)) {
            return false;
        }
        ActeurRegion other = (ActeurRegion) object;
        if ((this.idacteurRegion == null && other.idacteurRegion != null) || (this.idacteurRegion != null && !this.idacteurRegion.equals(other.idacteurRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ActeurRegion[ idacteurRegion=" + idacteurRegion + " ]";
    }
    
}

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
@Table(name = "opportunite_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpportuniteRegion.findAll", query = "SELECT o FROM OpportuniteRegion o"),
    @NamedQuery(name = "OpportuniteRegion.findByIdopportuniteRegion", query = "SELECT o FROM OpportuniteRegion o WHERE o.idopportuniteRegion = :idopportuniteRegion"),
    @NamedQuery(name = "OpportuniteRegion.findByNom", query = "SELECT o FROM OpportuniteRegion o WHERE o.nom = :nom"),
    @NamedQuery(name = "OpportuniteRegion.findByFacteur", query = "SELECT o FROM OpportuniteRegion o WHERE o.facteur = :facteur")})
public class OpportuniteRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idopportunite_region")
    private Long idopportuniteRegion;
    @Size(max = 2147483647)
    private String nom;
    private Boolean facteur;
    @JoinColumn(name = "idacteur", referencedColumnName = "idacteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Acteur idacteur;
    @JoinColumn(name = "idfacteur", referencedColumnName = "idfacteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Facteur idfacteur;
    @JoinColumn(name = "idffom_region", referencedColumnName = "idffom_region")
    @ManyToOne(fetch = FetchType.LAZY)
    private FfomRegion idffomRegion;

    public OpportuniteRegion() {
    }

    public OpportuniteRegion(Long idopportuniteRegion) {
        this.idopportuniteRegion = idopportuniteRegion;
    }

    public Long getIdopportuniteRegion() {
        return idopportuniteRegion;
    }

    public void setIdopportuniteRegion(Long idopportuniteRegion) {
        this.idopportuniteRegion = idopportuniteRegion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getFacteur() {
        return facteur;
    }

    public void setFacteur(Boolean facteur) {
        this.facteur = facteur;
    }

    public Acteur getIdacteur() {
        return idacteur;
    }

    public void setIdacteur(Acteur idacteur) {
        this.idacteur = idacteur;
    }

    public Facteur getIdfacteur() {
        return idfacteur;
    }

    public void setIdfacteur(Facteur idfacteur) {
        this.idfacteur = idfacteur;
    }

    public FfomRegion getIdffomRegion() {
        return idffomRegion;
    }

    public void setIdffomRegion(FfomRegion idffomRegion) {
        this.idffomRegion = idffomRegion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idopportuniteRegion != null ? idopportuniteRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpportuniteRegion)) {
            return false;
        }
        OpportuniteRegion other = (OpportuniteRegion) object;
        if ((this.idopportuniteRegion == null && other.idopportuniteRegion != null) || (this.idopportuniteRegion != null && !this.idopportuniteRegion.equals(other.idopportuniteRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OpportuniteRegion[ idopportuniteRegion=" + idopportuniteRegion + " ]";
    }
    
}

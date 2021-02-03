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
@Table(name = "force_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ForceRegion.findAll", query = "SELECT f FROM ForceRegion f"),
    @NamedQuery(name = "ForceRegion.findByIdforceRegion", query = "SELECT f FROM ForceRegion f WHERE f.idforceRegion = :idforceRegion"),
    @NamedQuery(name = "ForceRegion.findByFacteur", query = "SELECT f FROM ForceRegion f WHERE f.facteur = :facteur"),
    @NamedQuery(name = "ForceRegion.findByNom", query = "SELECT f FROM ForceRegion f WHERE f.nom = :nom")})
public class ForceRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idforce_region")
    private Long idforceRegion;
    private Boolean facteur;
    @Size(max = 2147483647)
    private String nom;
    @JoinColumn(name = "idacteur", referencedColumnName = "idacteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Acteur idacteur;
    @JoinColumn(name = "idfacteur", referencedColumnName = "idfacteur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Facteur idfacteur;
    @JoinColumn(name = "idffom_region", referencedColumnName = "idffom_region")
    @ManyToOne(fetch = FetchType.LAZY)
    private FfomRegion idffomRegion;

    public ForceRegion() {
    }

    public ForceRegion(Long idforceRegion) {
        this.idforceRegion = idforceRegion;
    }

    public Long getIdforceRegion() {
        return idforceRegion;
    }

    public void setIdforceRegion(Long idforceRegion) {
        this.idforceRegion = idforceRegion;
    }

    public Boolean getFacteur() {
        return facteur;
    }

    public void setFacteur(Boolean facteur) {
        this.facteur = facteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
        hash += (idforceRegion != null ? idforceRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ForceRegion)) {
            return false;
        }
        ForceRegion other = (ForceRegion) object;
        if ((this.idforceRegion == null && other.idforceRegion != null) || (this.idforceRegion != null && !this.idforceRegion.equals(other.idforceRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ForceRegion[ idforceRegion=" + idforceRegion + " ]";
    }
    
}

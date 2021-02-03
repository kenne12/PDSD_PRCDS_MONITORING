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
@Table(name = "faiblesse_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FaiblesseRegion.findAll", query = "SELECT f FROM FaiblesseRegion f"),
    @NamedQuery(name = "FaiblesseRegion.findByIdfaiblesseRegion", query = "SELECT f FROM FaiblesseRegion f WHERE f.idfaiblesseRegion = :idfaiblesseRegion"),
    @NamedQuery(name = "FaiblesseRegion.findByNom", query = "SELECT f FROM FaiblesseRegion f WHERE f.nom = :nom"),
    @NamedQuery(name = "FaiblesseRegion.findByFacteur", query = "SELECT f FROM FaiblesseRegion f WHERE f.facteur = :facteur")})
public class FaiblesseRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idfaiblesse_region")
    private Long idfaiblesseRegion;
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

    public FaiblesseRegion() {
    }

    public FaiblesseRegion(Long idfaiblesseRegion) {
        this.idfaiblesseRegion = idfaiblesseRegion;
    }

    public Long getIdfaiblesseRegion() {
        return idfaiblesseRegion;
    }

    public void setIdfaiblesseRegion(Long idfaiblesseRegion) {
        this.idfaiblesseRegion = idfaiblesseRegion;
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
        hash += (idfaiblesseRegion != null ? idfaiblesseRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaiblesseRegion)) {
            return false;
        }
        FaiblesseRegion other = (FaiblesseRegion) object;
        if ((this.idfaiblesseRegion == null && other.idfaiblesseRegion != null) || (this.idfaiblesseRegion != null && !this.idfaiblesseRegion.equals(other.idfaiblesseRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.FaiblesseRegion[ idfaiblesseRegion=" + idfaiblesseRegion + " ]";
    }
    
}

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
@Table(name = "menace_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenaceRegion.findAll", query = "SELECT m FROM MenaceRegion m"),
    @NamedQuery(name = "MenaceRegion.findByIdmenaceRegion", query = "SELECT m FROM MenaceRegion m WHERE m.idmenaceRegion = :idmenaceRegion"),
    @NamedQuery(name = "MenaceRegion.findByNom", query = "SELECT m FROM MenaceRegion m WHERE m.nom = :nom"),
    @NamedQuery(name = "MenaceRegion.findByFacteur", query = "SELECT m FROM MenaceRegion m WHERE m.facteur = :facteur")})
public class MenaceRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmenace_region")
    private Long idmenaceRegion;
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

    public MenaceRegion() {
    }

    public MenaceRegion(Long idmenaceRegion) {
        this.idmenaceRegion = idmenaceRegion;
    }

    public Long getIdmenaceRegion() {
        return idmenaceRegion;
    }

    public void setIdmenaceRegion(Long idmenaceRegion) {
        this.idmenaceRegion = idmenaceRegion;
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
        hash += (idmenaceRegion != null ? idmenaceRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenaceRegion)) {
            return false;
        }
        MenaceRegion other = (MenaceRegion) object;
        if ((this.idmenaceRegion == null && other.idmenaceRegion != null) || (this.idmenaceRegion != null && !this.idmenaceRegion.equals(other.idmenaceRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MenaceRegion[ idmenaceRegion=" + idmenaceRegion + " ]";
    }
    
}

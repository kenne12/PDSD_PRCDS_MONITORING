/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notationproblemeregion.findAll", query = "SELECT n FROM Notationproblemeregion n"),
    @NamedQuery(name = "Notationproblemeregion.findByIdnotationproblemeregion", query = "SELECT n FROM Notationproblemeregion n WHERE n.idnotationproblemeregion = :idnotationproblemeregion"),
    @NamedQuery(name = "Notationproblemeregion.findByValeur", query = "SELECT n FROM Notationproblemeregion n WHERE n.valeur = :valeur")})
public class Notationproblemeregion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idnotationproblemeregion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double valeur;
    @JoinColumn(name = "idprobleme_region", referencedColumnName = "idprobleme_region")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProblemeRegion idproblemeRegion;
    @JoinColumn(name = "idsousrubriquenotationprobleme", referencedColumnName = "idsousrubriquenotationprobleme")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sousrubriquenotationprobleme idsousrubriquenotationprobleme;

    public Notationproblemeregion() {
    }

    public Notationproblemeregion(Long idnotationproblemeregion) {
        this.idnotationproblemeregion = idnotationproblemeregion;
    }

    public Long getIdnotationproblemeregion() {
        return idnotationproblemeregion;
    }

    public void setIdnotationproblemeregion(Long idnotationproblemeregion) {
        this.idnotationproblemeregion = idnotationproblemeregion;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public ProblemeRegion getIdproblemeRegion() {
        return idproblemeRegion;
    }

    public void setIdproblemeRegion(ProblemeRegion idproblemeRegion) {
        this.idproblemeRegion = idproblemeRegion;
    }

    public Sousrubriquenotationprobleme getIdsousrubriquenotationprobleme() {
        return idsousrubriquenotationprobleme;
    }

    public void setIdsousrubriquenotationprobleme(Sousrubriquenotationprobleme idsousrubriquenotationprobleme) {
        this.idsousrubriquenotationprobleme = idsousrubriquenotationprobleme;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnotationproblemeregion != null ? idnotationproblemeregion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notationproblemeregion)) {
            return false;
        }
        Notationproblemeregion other = (Notationproblemeregion) object;
        if ((this.idnotationproblemeregion == null && other.idnotationproblemeregion != null) || (this.idnotationproblemeregion != null && !this.idnotationproblemeregion.equals(other.idnotationproblemeregion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Notationproblemeregion[ idnotationproblemeregion=" + idnotationproblemeregion + " ]";
    }
    
}

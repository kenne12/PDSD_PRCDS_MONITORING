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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "activite_region_element_cout")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActiviteRegionElementCout.findAll", query = "SELECT a FROM ActiviteRegionElementCout a"),
    @NamedQuery(name = "ActiviteRegionElementCout.findByIdactiviteRegionElementCout", query = "SELECT a FROM ActiviteRegionElementCout a WHERE a.idactiviteRegionElementCout = :idactiviteRegionElementCout"),
    @NamedQuery(name = "ActiviteRegionElementCout.findByCoutunitaire", query = "SELECT a FROM ActiviteRegionElementCout a WHERE a.coutunitaire = :coutunitaire"),
    @NamedQuery(name = "ActiviteRegionElementCout.findByQte", query = "SELECT a FROM ActiviteRegionElementCout a WHERE a.qte = :qte"),
    @NamedQuery(name = "ActiviteRegionElementCout.findByNbreJr", query = "SELECT a FROM ActiviteRegionElementCout a WHERE a.nbreJr = :nbreJr")})
public class ActiviteRegionElementCout implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idactivite_region_element_cout")
    private Long idactiviteRegionElementCout;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double coutunitaire;
    private Double qte;
    @Column(name = "nbre_jr")
    private Double nbreJr;
    @JoinColumn(name = "idactivite_region", referencedColumnName = "idactivite_region")
    @ManyToOne(fetch = FetchType.LAZY)
    private ActiviteRegion idactiviteRegion;
    @JoinColumn(name = "idelementcout", referencedColumnName = "idelement_cout")
    @ManyToOne(fetch = FetchType.LAZY)
    private ElementCout idelementcout;

    public ActiviteRegionElementCout() {
    }

    public ActiviteRegionElementCout(Long idactiviteRegionElementCout) {
        this.idactiviteRegionElementCout = idactiviteRegionElementCout;
    }

    public Long getIdactiviteRegionElementCout() {
        return idactiviteRegionElementCout;
    }

    public void setIdactiviteRegionElementCout(Long idactiviteRegionElementCout) {
        this.idactiviteRegionElementCout = idactiviteRegionElementCout;
    }

    public Double getCoutunitaire() {
        return coutunitaire;
    }

    public void setCoutunitaire(Double coutunitaire) {
        this.coutunitaire = coutunitaire;
    }

    public Double getQte() {
        return qte;
    }

    public void setQte(Double qte) {
        this.qte = qte;
    }

    public Double getNbreJr() {
        return nbreJr;
    }

    public void setNbreJr(Double nbreJr) {
        this.nbreJr = nbreJr;
    }

    public ActiviteRegion getIdactiviteRegion() {
        return idactiviteRegion;
    }

    public void setIdactiviteRegion(ActiviteRegion idactiviteRegion) {
        this.idactiviteRegion = idactiviteRegion;
    }

    public ElementCout getIdelementcout() {
        return idelementcout;
    }

    public void setIdelementcout(ElementCout idelementcout) {
        this.idelementcout = idelementcout;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactiviteRegionElementCout != null ? idactiviteRegionElementCout.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActiviteRegionElementCout)) {
            return false;
        }
        ActiviteRegionElementCout other = (ActiviteRegionElementCout) object;
        if ((this.idactiviteRegionElementCout == null && other.idactiviteRegionElementCout != null) || (this.idactiviteRegionElementCout != null && !this.idactiviteRegionElementCout.equals(other.idactiviteRegionElementCout))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ActiviteRegionElementCout[ idactiviteRegionElementCout=" + idactiviteRegionElementCout + " ]";
    }
    
}

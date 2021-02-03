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
@Table(name = "hospitalisation_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HospitalisationRegion.findAll", query = "SELECT h FROM HospitalisationRegion h"),
    @NamedQuery(name = "HospitalisationRegion.findByIdhospitalisationRegion", query = "SELECT h FROM HospitalisationRegion h WHERE h.idhospitalisationRegion = :idhospitalisationRegion"),
    @NamedQuery(name = "HospitalisationRegion.findByValeur", query = "SELECT h FROM HospitalisationRegion h WHERE h.valeur = :valeur"),
    @NamedQuery(name = "HospitalisationRegion.findByConsolide", query = "SELECT h FROM HospitalisationRegion h WHERE h.consolide = :consolide")})
public class HospitalisationRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idhospitalisation_region")
    private Long idhospitalisationRegion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double valeur;
    private Boolean consolide;
    @JoinColumn(name = "idhospitalisation", referencedColumnName = "idhospitalisation")
    @ManyToOne(fetch = FetchType.LAZY)
    private Hospitalisation idhospitalisation;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @JoinColumn(name = "idrubriquehospitalisation", referencedColumnName = "idrubriquehospitalisation")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriquehospitalisation idrubriquehospitalisation;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public HospitalisationRegion() {
    }

    public HospitalisationRegion(Long idhospitalisationRegion) {
        this.idhospitalisationRegion = idhospitalisationRegion;
    }

    public Long getIdhospitalisationRegion() {
        return idhospitalisationRegion;
    }

    public void setIdhospitalisationRegion(Long idhospitalisationRegion) {
        this.idhospitalisationRegion = idhospitalisationRegion;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public Boolean getConsolide() {
        return consolide;
    }

    public void setConsolide(Boolean consolide) {
        this.consolide = consolide;
    }

    public Hospitalisation getIdhospitalisation() {
        return idhospitalisation;
    }

    public void setIdhospitalisation(Hospitalisation idhospitalisation) {
        this.idhospitalisation = idhospitalisation;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    public Rubriquehospitalisation getIdrubriquehospitalisation() {
        return idrubriquehospitalisation;
    }

    public void setIdrubriquehospitalisation(Rubriquehospitalisation idrubriquehospitalisation) {
        this.idrubriquehospitalisation = idrubriquehospitalisation;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhospitalisationRegion != null ? idhospitalisationRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HospitalisationRegion)) {
            return false;
        }
        HospitalisationRegion other = (HospitalisationRegion) object;
        if ((this.idhospitalisationRegion == null && other.idhospitalisationRegion != null) || (this.idhospitalisationRegion != null && !this.idhospitalisationRegion.equals(other.idhospitalisationRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.HospitalisationRegion[ idhospitalisationRegion=" + idhospitalisationRegion + " ]";
    }
    
}

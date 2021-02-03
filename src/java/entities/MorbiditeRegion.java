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
@Table(name = "morbidite_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MorbiditeRegion.findAll", query = "SELECT m FROM MorbiditeRegion m"),
    @NamedQuery(name = "MorbiditeRegion.findByIdmorbiditeRegion", query = "SELECT m FROM MorbiditeRegion m WHERE m.idmorbiditeRegion = :idmorbiditeRegion"),
    @NamedQuery(name = "MorbiditeRegion.findByValeur", query = "SELECT m FROM MorbiditeRegion m WHERE m.valeur = :valeur"),
    @NamedQuery(name = "MorbiditeRegion.findByConsolide", query = "SELECT m FROM MorbiditeRegion m WHERE m.consolide = :consolide")})
public class MorbiditeRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmorbidite_region")
    private Long idmorbiditeRegion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double valeur;
    private Boolean consolide;
    @JoinColumn(name = "idmorbidite", referencedColumnName = "idmorbidite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Morbidite idmorbidite;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @JoinColumn(name = "idrubriquemorbidite", referencedColumnName = "idrubriquemorbidite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriquemorbidite idrubriquemorbidite;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public MorbiditeRegion() {
    }

    public MorbiditeRegion(Long idmorbiditeRegion) {
        this.idmorbiditeRegion = idmorbiditeRegion;
    }

    public Long getIdmorbiditeRegion() {
        return idmorbiditeRegion;
    }

    public void setIdmorbiditeRegion(Long idmorbiditeRegion) {
        this.idmorbiditeRegion = idmorbiditeRegion;
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

    public Morbidite getIdmorbidite() {
        return idmorbidite;
    }

    public void setIdmorbidite(Morbidite idmorbidite) {
        this.idmorbidite = idmorbidite;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    public Rubriquemorbidite getIdrubriquemorbidite() {
        return idrubriquemorbidite;
    }

    public void setIdrubriquemorbidite(Rubriquemorbidite idrubriquemorbidite) {
        this.idrubriquemorbidite = idrubriquemorbidite;
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
        hash += (idmorbiditeRegion != null ? idmorbiditeRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MorbiditeRegion)) {
            return false;
        }
        MorbiditeRegion other = (MorbiditeRegion) object;
        if ((this.idmorbiditeRegion == null && other.idmorbiditeRegion != null) || (this.idmorbiditeRegion != null && !this.idmorbiditeRegion.equals(other.idmorbiditeRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MorbiditeRegion[ idmorbiditeRegion=" + idmorbiditeRegion + " ]";
    }
    
}

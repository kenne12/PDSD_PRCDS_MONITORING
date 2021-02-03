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
@Table(name = "mortalite_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MortaliteRegion.findAll", query = "SELECT m FROM MortaliteRegion m"),
    @NamedQuery(name = "MortaliteRegion.findByIdmortaliteRegion", query = "SELECT m FROM MortaliteRegion m WHERE m.idmortaliteRegion = :idmortaliteRegion"),
    @NamedQuery(name = "MortaliteRegion.findByValeur", query = "SELECT m FROM MortaliteRegion m WHERE m.valeur = :valeur"),
    @NamedQuery(name = "MortaliteRegion.findByConsolide", query = "SELECT m FROM MortaliteRegion m WHERE m.consolide = :consolide")})
public class MortaliteRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmortalite_region")
    private Long idmortaliteRegion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double valeur;
    private Boolean consolide;
    @JoinColumn(name = "idmortalite", referencedColumnName = "idmortalite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mortalite idmortalite;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @JoinColumn(name = "idrubriquemortalite", referencedColumnName = "idrubriquemortalite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriquemortalite idrubriquemortalite;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public MortaliteRegion() {
    }

    public MortaliteRegion(Long idmortaliteRegion) {
        this.idmortaliteRegion = idmortaliteRegion;
    }

    public Long getIdmortaliteRegion() {
        return idmortaliteRegion;
    }

    public void setIdmortaliteRegion(Long idmortaliteRegion) {
        this.idmortaliteRegion = idmortaliteRegion;
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

    public Mortalite getIdmortalite() {
        return idmortalite;
    }

    public void setIdmortalite(Mortalite idmortalite) {
        this.idmortalite = idmortalite;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    public Rubriquemortalite getIdrubriquemortalite() {
        return idrubriquemortalite;
    }

    public void setIdrubriquemortalite(Rubriquemortalite idrubriquemortalite) {
        this.idrubriquemortalite = idrubriquemortalite;
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
        hash += (idmortaliteRegion != null ? idmortaliteRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MortaliteRegion)) {
            return false;
        }
        MortaliteRegion other = (MortaliteRegion) object;
        if ((this.idmortaliteRegion == null && other.idmortaliteRegion != null) || (this.idmortaliteRegion != null && !this.idmortaliteRegion.equals(other.idmortaliteRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MortaliteRegion[ idmortaliteRegion=" + idmortaliteRegion + " ]";
    }
    
}

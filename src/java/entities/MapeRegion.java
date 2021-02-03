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
@Table(name = "mape_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MapeRegion.findAll", query = "SELECT m FROM MapeRegion m"),
    @NamedQuery(name = "MapeRegion.findByIdmapeRegion", query = "SELECT m FROM MapeRegion m WHERE m.idmapeRegion = :idmapeRegion"),
    @NamedQuery(name = "MapeRegion.findByValeur", query = "SELECT m FROM MapeRegion m WHERE m.valeur = :valeur"),
    @NamedQuery(name = "MapeRegion.findByConsolide", query = "SELECT m FROM MapeRegion m WHERE m.consolide = :consolide")})
public class MapeRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmape_region")
    private Long idmapeRegion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double valeur;
    private Boolean consolide;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idmape", referencedColumnName = "idmape")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mape idmape;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public MapeRegion() {
    }

    public MapeRegion(Long idmapeRegion) {
        this.idmapeRegion = idmapeRegion;
    }

    public Long getIdmapeRegion() {
        return idmapeRegion;
    }

    public void setIdmapeRegion(Long idmapeRegion) {
        this.idmapeRegion = idmapeRegion;
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

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Mape getIdmape() {
        return idmape;
    }

    public void setIdmape(Mape idmape) {
        this.idmape = idmape;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
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
        hash += (idmapeRegion != null ? idmapeRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MapeRegion)) {
            return false;
        }
        MapeRegion other = (MapeRegion) object;
        if ((this.idmapeRegion == null && other.idmapeRegion != null) || (this.idmapeRegion != null && !this.idmapeRegion.equals(other.idmapeRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MapeRegion[ idmapeRegion=" + idmapeRegion + " ]";
    }
    
}

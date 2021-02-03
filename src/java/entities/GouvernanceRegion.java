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
@Table(name = "gouvernance_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GouvernanceRegion.findAll", query = "SELECT g FROM GouvernanceRegion g"),
    @NamedQuery(name = "GouvernanceRegion.findByIdgouvernanceRegion", query = "SELECT g FROM GouvernanceRegion g WHERE g.idgouvernanceRegion = :idgouvernanceRegion"),
    @NamedQuery(name = "GouvernanceRegion.findByValeur", query = "SELECT g FROM GouvernanceRegion g WHERE g.valeur = :valeur")})
public class GouvernanceRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idgouvernance_region")
    private Long idgouvernanceRegion;
    @Size(max = 2147483647)
    private String valeur;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @JoinColumn(name = "idrubriquegouvernance", referencedColumnName = "idrubriquegouvernance")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriquegouvernance idrubriquegouvernance;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public GouvernanceRegion() {
    }

    public GouvernanceRegion(Long idgouvernanceRegion) {
        this.idgouvernanceRegion = idgouvernanceRegion;
    }

    public Long getIdgouvernanceRegion() {
        return idgouvernanceRegion;
    }

    public void setIdgouvernanceRegion(Long idgouvernanceRegion) {
        this.idgouvernanceRegion = idgouvernanceRegion;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    public Rubriquegouvernance getIdrubriquegouvernance() {
        return idrubriquegouvernance;
    }

    public void setIdrubriquegouvernance(Rubriquegouvernance idrubriquegouvernance) {
        this.idrubriquegouvernance = idrubriquegouvernance;
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
        hash += (idgouvernanceRegion != null ? idgouvernanceRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GouvernanceRegion)) {
            return false;
        }
        GouvernanceRegion other = (GouvernanceRegion) object;
        if ((this.idgouvernanceRegion == null && other.idgouvernanceRegion != null) || (this.idgouvernanceRegion != null && !this.idgouvernanceRegion.equals(other.idgouvernanceRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.GouvernanceRegion[ idgouvernanceRegion=" + idgouvernanceRegion + " ]";
    }
    
}

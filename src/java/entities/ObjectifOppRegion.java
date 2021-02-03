/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "objectif_opp_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjectifOppRegion.findAll", query = "SELECT o FROM ObjectifOppRegion o"),
    @NamedQuery(name = "ObjectifOppRegion.findByIdobjectifOppRegion", query = "SELECT o FROM ObjectifOppRegion o WHERE o.idobjectifOppRegion = :idobjectifOppRegion"),
    @NamedQuery(name = "ObjectifOppRegion.findByObjectif", query = "SELECT o FROM ObjectifOppRegion o WHERE o.objectif = :objectif")})
public class ObjectifOppRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idobjectif_opp_region")
    private Long idobjectifOppRegion;
    @Size(max = 2147483647)
    private String objectif;
    @JoinColumn(name = "idintervention", referencedColumnName = "idinterventionpnds")
    @ManyToOne(fetch = FetchType.LAZY)
    private Interventionpnds idintervention;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @OneToMany(mappedBy = "ididobjectifOpp", fetch = FetchType.LAZY)
    private List<ActiviteRegion> activiteRegionList;

    public ObjectifOppRegion() {
    }

    public ObjectifOppRegion(Long idobjectifOppRegion) {
        this.idobjectifOppRegion = idobjectifOppRegion;
    }

    public Long getIdobjectifOppRegion() {
        return idobjectifOppRegion;
    }

    public void setIdobjectifOppRegion(Long idobjectifOppRegion) {
        this.idobjectifOppRegion = idobjectifOppRegion;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public Interventionpnds getIdintervention() {
        return idintervention;
    }

    public void setIdintervention(Interventionpnds idintervention) {
        this.idintervention = idintervention;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    @XmlTransient
    public List<ActiviteRegion> getActiviteRegionList() {
        return activiteRegionList;
    }

    public void setActiviteRegionList(List<ActiviteRegion> activiteRegionList) {
        this.activiteRegionList = activiteRegionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idobjectifOppRegion != null ? idobjectifOppRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjectifOppRegion)) {
            return false;
        }
        ObjectifOppRegion other = (ObjectifOppRegion) object;
        if ((this.idobjectifOppRegion == null && other.idobjectifOppRegion != null) || (this.idobjectifOppRegion != null && !this.idobjectifOppRegion.equals(other.idobjectifOppRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ObjectifOppRegion[ idobjectifOppRegion=" + idobjectifOppRegion + " ]";
    }
    
}

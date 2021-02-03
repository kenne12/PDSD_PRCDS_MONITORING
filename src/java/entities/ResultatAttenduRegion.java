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
@Table(name = "resultat_attendu_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultatAttenduRegion.findAll", query = "SELECT r FROM ResultatAttenduRegion r"),
    @NamedQuery(name = "ResultatAttenduRegion.findByIdresultatAttenduRegion", query = "SELECT r FROM ResultatAttenduRegion r WHERE r.idresultatAttenduRegion = :idresultatAttenduRegion"),
    @NamedQuery(name = "ResultatAttenduRegion.findByResultat", query = "SELECT r FROM ResultatAttenduRegion r WHERE r.resultat = :resultat")})
public class ResultatAttenduRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idresultat_attendu_region")
    private Long idresultatAttenduRegion;
    @Size(max = 2147483647)
    private String resultat;
    @OneToMany(mappedBy = "idresultatattendu", fetch = FetchType.LAZY)
    private List<ActiviteRegion> activiteRegionList;
    @JoinColumn(name = "idindicateur", referencedColumnName = "idindicateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Indicateur idindicateur;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;

    public ResultatAttenduRegion() {
    }

    public ResultatAttenduRegion(Long idresultatAttenduRegion) {
        this.idresultatAttenduRegion = idresultatAttenduRegion;
    }

    public Long getIdresultatAttenduRegion() {
        return idresultatAttenduRegion;
    }

    public void setIdresultatAttenduRegion(Long idresultatAttenduRegion) {
        this.idresultatAttenduRegion = idresultatAttenduRegion;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    @XmlTransient
    public List<ActiviteRegion> getActiviteRegionList() {
        return activiteRegionList;
    }

    public void setActiviteRegionList(List<ActiviteRegion> activiteRegionList) {
        this.activiteRegionList = activiteRegionList;
    }

    public Indicateur getIdindicateur() {
        return idindicateur;
    }

    public void setIdindicateur(Indicateur idindicateur) {
        this.idindicateur = idindicateur;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresultatAttenduRegion != null ? idresultatAttenduRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResultatAttenduRegion)) {
            return false;
        }
        ResultatAttenduRegion other = (ResultatAttenduRegion) object;
        if ((this.idresultatAttenduRegion == null && other.idresultatAttenduRegion != null) || (this.idresultatAttenduRegion != null && !this.idresultatAttenduRegion.equals(other.idresultatAttenduRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ResultatAttenduRegion[ idresultatAttenduRegion=" + idresultatAttenduRegion + " ]";
    }
    
}

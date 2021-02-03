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
@Table(name = "ffom_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FfomRegion.findAll", query = "SELECT f FROM FfomRegion f"),
    @NamedQuery(name = "FfomRegion.findByIdffomRegion", query = "SELECT f FROM FfomRegion f WHERE f.idffomRegion = :idffomRegion"),
    @NamedQuery(name = "FfomRegion.findByForce", query = "SELECT f FROM FfomRegion f WHERE f.force = :force"),
    @NamedQuery(name = "FfomRegion.findByFaiblesse", query = "SELECT f FROM FfomRegion f WHERE f.faiblesse = :faiblesse"),
    @NamedQuery(name = "FfomRegion.findByOpportunite", query = "SELECT f FROM FfomRegion f WHERE f.opportunite = :opportunite"),
    @NamedQuery(name = "FfomRegion.findByMenace", query = "SELECT f FROM FfomRegion f WHERE f.menace = :menace")})
public class FfomRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idffom_region")
    private Long idffomRegion;
    @Size(max = 2147483647)
    private String force;
    @Size(max = 2147483647)
    private String faiblesse;
    @Size(max = 2147483647)
    private String opportunite;
    @Size(max = 2147483647)
    private String menace;
    @JoinColumn(name = "idpilier", referencedColumnName = "idpilier")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pilier idpilier;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @OneToMany(mappedBy = "idffomRegion", fetch = FetchType.LAZY)
    private List<FaiblesseRegion> faiblesseRegionList;
    @OneToMany(mappedBy = "idffomRegion", fetch = FetchType.LAZY)
    private List<ForceRegion> forceRegionList;
    @OneToMany(mappedBy = "idffomRegion", fetch = FetchType.LAZY)
    private List<MenaceRegion> menaceRegionList;
    @OneToMany(mappedBy = "idffomRegion", fetch = FetchType.LAZY)
    private List<OpportuniteRegion> opportuniteRegionList;

    public FfomRegion() {
    }

    public FfomRegion(Long idffomRegion) {
        this.idffomRegion = idffomRegion;
    }

    public Long getIdffomRegion() {
        return idffomRegion;
    }

    public void setIdffomRegion(Long idffomRegion) {
        this.idffomRegion = idffomRegion;
    }

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }

    public String getFaiblesse() {
        return faiblesse;
    }

    public void setFaiblesse(String faiblesse) {
        this.faiblesse = faiblesse;
    }

    public String getOpportunite() {
        return opportunite;
    }

    public void setOpportunite(String opportunite) {
        this.opportunite = opportunite;
    }

    public String getMenace() {
        return menace;
    }

    public void setMenace(String menace) {
        this.menace = menace;
    }

    public Pilier getIdpilier() {
        return idpilier;
    }

    public void setIdpilier(Pilier idpilier) {
        this.idpilier = idpilier;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    @XmlTransient
    public List<FaiblesseRegion> getFaiblesseRegionList() {
        return faiblesseRegionList;
    }

    public void setFaiblesseRegionList(List<FaiblesseRegion> faiblesseRegionList) {
        this.faiblesseRegionList = faiblesseRegionList;
    }

    @XmlTransient
    public List<ForceRegion> getForceRegionList() {
        return forceRegionList;
    }

    public void setForceRegionList(List<ForceRegion> forceRegionList) {
        this.forceRegionList = forceRegionList;
    }

    @XmlTransient
    public List<MenaceRegion> getMenaceRegionList() {
        return menaceRegionList;
    }

    public void setMenaceRegionList(List<MenaceRegion> menaceRegionList) {
        this.menaceRegionList = menaceRegionList;
    }

    @XmlTransient
    public List<OpportuniteRegion> getOpportuniteRegionList() {
        return opportuniteRegionList;
    }

    public void setOpportuniteRegionList(List<OpportuniteRegion> opportuniteRegionList) {
        this.opportuniteRegionList = opportuniteRegionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idffomRegion != null ? idffomRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FfomRegion)) {
            return false;
        }
        FfomRegion other = (FfomRegion) object;
        if ((this.idffomRegion == null && other.idffomRegion != null) || (this.idffomRegion != null && !this.idffomRegion.equals(other.idffomRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.FfomRegion[ idffomRegion=" + idffomRegion + " ]";
    }
    
}

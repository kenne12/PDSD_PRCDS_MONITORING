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
@Table(name = "probleme_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProblemeRegion.findAll", query = "SELECT p FROM ProblemeRegion p"),
    @NamedQuery(name = "ProblemeRegion.findByIdproblemeRegion", query = "SELECT p FROM ProblemeRegion p WHERE p.idproblemeRegion = :idproblemeRegion"),
    @NamedQuery(name = "ProblemeRegion.findByNom", query = "SELECT p FROM ProblemeRegion p WHERE p.nom = :nom"),
    @NamedQuery(name = "ProblemeRegion.findByCause", query = "SELECT p FROM ProblemeRegion p WHERE p.cause = :cause"),
    @NamedQuery(name = "ProblemeRegion.findByObjectif", query = "SELECT p FROM ProblemeRegion p WHERE p.objectif = :objectif"),
    @NamedQuery(name = "ProblemeRegion.findByFaible", query = "SELECT p FROM ProblemeRegion p WHERE p.faible = :faible"),
    @NamedQuery(name = "ProblemeRegion.findByTotalpoint", query = "SELECT p FROM ProblemeRegion p WHERE p.totalpoint = :totalpoint")})
public class ProblemeRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idprobleme_region")
    private Integer idproblemeRegion;
    @Size(max = 2147483647)
    private String nom;
    @Size(max = 2147483647)
    private String cause;
    @Size(max = 2147483647)
    private String objectif;
    private Boolean faible;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double totalpoint;
    @JoinColumn(name = "idindicateur_region", referencedColumnName = "idindicateur_region")
    @ManyToOne(fetch = FetchType.LAZY)
    private IndicateurRegion idindicateurRegion;
    @OneToMany(mappedBy = "idproblemeRegion", fetch = FetchType.LAZY)
    private List<Notationproblemeregion> notationproblemeregionList;
    @OneToMany(mappedBy = "idproblemeRegion", fetch = FetchType.LAZY)
    private List<ActiviteRegion> activiteRegionList;

    public ProblemeRegion() {
    }

    public ProblemeRegion(Integer idproblemeRegion) {
        this.idproblemeRegion = idproblemeRegion;
    }

    public Integer getIdproblemeRegion() {
        return idproblemeRegion;
    }

    public void setIdproblemeRegion(Integer idproblemeRegion) {
        this.idproblemeRegion = idproblemeRegion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public Boolean getFaible() {
        return faible;
    }

    public void setFaible(Boolean faible) {
        this.faible = faible;
    }

    public Double getTotalpoint() {
        return totalpoint;
    }

    public void setTotalpoint(Double totalpoint) {
        this.totalpoint = totalpoint;
    }

    public IndicateurRegion getIdindicateurRegion() {
        return idindicateurRegion;
    }

    public void setIdindicateurRegion(IndicateurRegion idindicateurRegion) {
        this.idindicateurRegion = idindicateurRegion;
    }

    @XmlTransient
    public List<Notationproblemeregion> getNotationproblemeregionList() {
        return notationproblemeregionList;
    }

    public void setNotationproblemeregionList(List<Notationproblemeregion> notationproblemeregionList) {
        this.notationproblemeregionList = notationproblemeregionList;
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
        hash += (idproblemeRegion != null ? idproblemeRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProblemeRegion)) {
            return false;
        }
        ProblemeRegion other = (ProblemeRegion) object;
        if ((this.idproblemeRegion == null && other.idproblemeRegion != null) || (this.idproblemeRegion != null && !this.idproblemeRegion.equals(other.idproblemeRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProblemeRegion[ idproblemeRegion=" + idproblemeRegion + " ]";
    }
    
}

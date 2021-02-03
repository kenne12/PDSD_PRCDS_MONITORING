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
@Table(name = "indicateur_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IndicateurRegion.findAll", query = "SELECT i FROM IndicateurRegion i"),
    @NamedQuery(name = "IndicateurRegion.findByIdindicateurRegion", query = "SELECT i FROM IndicateurRegion i WHERE i.idindicateurRegion = :idindicateurRegion"),
    @NamedQuery(name = "IndicateurRegion.findByValeur", query = "SELECT i FROM IndicateurRegion i WHERE i.valeur = :valeur"),
    @NamedQuery(name = "IndicateurRegion.findByAcquis", query = "SELECT i FROM IndicateurRegion i WHERE i.acquis = :acquis"),
    @NamedQuery(name = "IndicateurRegion.findByProbleme", query = "SELECT i FROM IndicateurRegion i WHERE i.probleme = :probleme"),
    @NamedQuery(name = "IndicateurRegion.findByObservation", query = "SELECT i FROM IndicateurRegion i WHERE i.observation = :observation"),
    @NamedQuery(name = "IndicateurRegion.findByObjectifOperationnel", query = "SELECT i FROM IndicateurRegion i WHERE i.objectifOperationnel = :objectifOperationnel"),
    @NamedQuery(name = "IndicateurRegion.findByCause", query = "SELECT i FROM IndicateurRegion i WHERE i.cause = :cause")})
public class IndicateurRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idindicateur_region")
    private Long idindicateurRegion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double valeur;
    @Size(max = 254)
    private String acquis;
    @Size(max = 254)
    private String probleme;
    @Size(max = 254)
    private String observation;
    @Size(max = 254)
    @Column(name = "objectif_operationnel")
    private String objectifOperationnel;
    @Size(max = 2147483647)
    private String cause;
    @OneToMany(mappedBy = "idindicateurRegion", fetch = FetchType.LAZY)
    private List<ProblemeRegion> problemeRegionList;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idindicateur", referencedColumnName = "idindicateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Indicateur idindicateur;
    @JoinColumn(name = "idobservation", referencedColumnName = "idobservation")
    @ManyToOne(fetch = FetchType.LAZY)
    private Observation idobservation;
    @JoinColumn(name = "idperiode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periode idperiode;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;

    public IndicateurRegion() {
    }

    public IndicateurRegion(Long idindicateurRegion) {
        this.idindicateurRegion = idindicateurRegion;
    }

    public Long getIdindicateurRegion() {
        return idindicateurRegion;
    }

    public void setIdindicateurRegion(Long idindicateurRegion) {
        this.idindicateurRegion = idindicateurRegion;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public String getAcquis() {
        return acquis;
    }

    public void setAcquis(String acquis) {
        this.acquis = acquis;
    }

    public String getProbleme() {
        return probleme;
    }

    public void setProbleme(String probleme) {
        this.probleme = probleme;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getObjectifOperationnel() {
        return objectifOperationnel;
    }

    public void setObjectifOperationnel(String objectifOperationnel) {
        this.objectifOperationnel = objectifOperationnel;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @XmlTransient
    public List<ProblemeRegion> getProblemeRegionList() {
        return problemeRegionList;
    }

    public void setProblemeRegionList(List<ProblemeRegion> problemeRegionList) {
        this.problemeRegionList = problemeRegionList;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Indicateur getIdindicateur() {
        return idindicateur;
    }

    public void setIdindicateur(Indicateur idindicateur) {
        this.idindicateur = idindicateur;
    }

    public Observation getIdobservation() {
        return idobservation;
    }

    public void setIdobservation(Observation idobservation) {
        this.idobservation = idobservation;
    }

    public Periode getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Periode idperiode) {
        this.idperiode = idperiode;
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
        hash += (idindicateurRegion != null ? idindicateurRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndicateurRegion)) {
            return false;
        }
        IndicateurRegion other = (IndicateurRegion) object;
        if ((this.idindicateurRegion == null && other.idindicateurRegion != null) || (this.idindicateurRegion != null && !this.idindicateurRegion.equals(other.idindicateurRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.IndicateurRegion[ idindicateurRegion=" + idindicateurRegion + " ]";
    }
    
}

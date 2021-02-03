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
@Table(name = "activite_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActiviteRegion.findAll", query = "SELECT a FROM ActiviteRegion a"),
    @NamedQuery(name = "ActiviteRegion.findByIdactiviteRegion", query = "SELECT a FROM ActiviteRegion a WHERE a.idactiviteRegion = :idactiviteRegion"),
    @NamedQuery(name = "ActiviteRegion.findByNom", query = "SELECT a FROM ActiviteRegion a WHERE a.nom = :nom"),
    @NamedQuery(name = "ActiviteRegion.findByCoutunitaire", query = "SELECT a FROM ActiviteRegion a WHERE a.coutunitaire = :coutunitaire"),
    @NamedQuery(name = "ActiviteRegion.findByCoutglobal", query = "SELECT a FROM ActiviteRegion a WHERE a.coutglobal = :coutglobal"),
    @NamedQuery(name = "ActiviteRegion.findByResponsable", query = "SELECT a FROM ActiviteRegion a WHERE a.responsable = :responsable"),
    @NamedQuery(name = "ActiviteRegion.findByActiviteAppui", query = "SELECT a FROM ActiviteRegion a WHERE a.activiteAppui = :activiteAppui"),
    @NamedQuery(name = "ActiviteRegion.findByIdregion", query = "SELECT a FROM ActiviteRegion a WHERE a.idregion = :idregion")})
public class ActiviteRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idactivite_region")
    private Long idactiviteRegion;
    @Size(max = 2147483647)
    private String nom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double coutunitaire;
    private Double coutglobal;
    @Size(max = 254)
    private String responsable;
    @Column(name = "activite_appui")
    private Boolean activiteAppui;
    private Integer idregion;
    @OneToMany(mappedBy = "idactiviteRegion", fetch = FetchType.LAZY)
    private List<ActiviteStructureRegion> activiteStructureRegionList;
    @JoinColumn(name = "idactivite_traceur", referencedColumnName = "idactivite_traceur")
    @ManyToOne(fetch = FetchType.LAZY)
    private ActiviteTraceur idactiviteTraceur;
    @JoinColumn(name = "idintervention", referencedColumnName = "idinterventionpnds")
    @ManyToOne(fetch = FetchType.LAZY)
    private Interventionpnds idintervention;
    @JoinColumn(name = "ididobjectif_opp", referencedColumnName = "idobjectif_opp_region")
    @ManyToOne(fetch = FetchType.LAZY)
    private ObjectifOppRegion ididobjectifOpp;
    @JoinColumn(name = "idprobleme_region", referencedColumnName = "idprobleme_region")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProblemeRegion idproblemeRegion;
    @JoinColumn(name = "idresultatattendu", referencedColumnName = "idresultat_attendu_region")
    @ManyToOne(fetch = FetchType.LAZY)
    private ResultatAttenduRegion idresultatattendu;
    @JoinColumn(name = "idsourcefi", referencedColumnName = "idsourcefi")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sourcefinancement idsourcefi;
    @JoinColumn(name = "idtypeactivite", referencedColumnName = "idtypeactivite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typeactivite idtypeactivite;
    @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typestructure idtypestructure;
    @OneToMany(mappedBy = "idactiviteRegion", fetch = FetchType.LAZY)
    private List<ChronogrammeRegion> chronogrammeRegionList;
    @OneToMany(mappedBy = "idactiviteRegion", fetch = FetchType.LAZY)
    private List<ActiviteRegionElementCout> activiteRegionElementCoutList;

    public ActiviteRegion() {
    }

    public ActiviteRegion(Long idactiviteRegion) {
        this.idactiviteRegion = idactiviteRegion;
    }

    public Long getIdactiviteRegion() {
        return idactiviteRegion;
    }

    public void setIdactiviteRegion(Long idactiviteRegion) {
        this.idactiviteRegion = idactiviteRegion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getCoutunitaire() {
        return coutunitaire;
    }

    public void setCoutunitaire(Double coutunitaire) {
        this.coutunitaire = coutunitaire;
    }

    public Double getCoutglobal() {
        return coutglobal;
    }

    public void setCoutglobal(Double coutglobal) {
        this.coutglobal = coutglobal;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Boolean getActiviteAppui() {
        return activiteAppui;
    }

    public void setActiviteAppui(Boolean activiteAppui) {
        this.activiteAppui = activiteAppui;
    }

    public Integer getIdregion() {
        return idregion;
    }

    public void setIdregion(Integer idregion) {
        this.idregion = idregion;
    }

    @XmlTransient
    public List<ActiviteStructureRegion> getActiviteStructureRegionList() {
        return activiteStructureRegionList;
    }

    public void setActiviteStructureRegionList(List<ActiviteStructureRegion> activiteStructureRegionList) {
        this.activiteStructureRegionList = activiteStructureRegionList;
    }

    public ActiviteTraceur getIdactiviteTraceur() {
        return idactiviteTraceur;
    }

    public void setIdactiviteTraceur(ActiviteTraceur idactiviteTraceur) {
        this.idactiviteTraceur = idactiviteTraceur;
    }

    public Interventionpnds getIdintervention() {
        return idintervention;
    }

    public void setIdintervention(Interventionpnds idintervention) {
        this.idintervention = idintervention;
    }

    public ObjectifOppRegion getIdidobjectifOpp() {
        return ididobjectifOpp;
    }

    public void setIdidobjectifOpp(ObjectifOppRegion ididobjectifOpp) {
        this.ididobjectifOpp = ididobjectifOpp;
    }

    public ProblemeRegion getIdproblemeRegion() {
        return idproblemeRegion;
    }

    public void setIdproblemeRegion(ProblemeRegion idproblemeRegion) {
        this.idproblemeRegion = idproblemeRegion;
    }

    public ResultatAttenduRegion getIdresultatattendu() {
        return idresultatattendu;
    }

    public void setIdresultatattendu(ResultatAttenduRegion idresultatattendu) {
        this.idresultatattendu = idresultatattendu;
    }

    public Sourcefinancement getIdsourcefi() {
        return idsourcefi;
    }

    public void setIdsourcefi(Sourcefinancement idsourcefi) {
        this.idsourcefi = idsourcefi;
    }

    public Typeactivite getIdtypeactivite() {
        return idtypeactivite;
    }

    public void setIdtypeactivite(Typeactivite idtypeactivite) {
        this.idtypeactivite = idtypeactivite;
    }

    public Typestructure getIdtypestructure() {
        return idtypestructure;
    }

    public void setIdtypestructure(Typestructure idtypestructure) {
        this.idtypestructure = idtypestructure;
    }

    @XmlTransient
    public List<ChronogrammeRegion> getChronogrammeRegionList() {
        return chronogrammeRegionList;
    }

    public void setChronogrammeRegionList(List<ChronogrammeRegion> chronogrammeRegionList) {
        this.chronogrammeRegionList = chronogrammeRegionList;
    }

    @XmlTransient
    public List<ActiviteRegionElementCout> getActiviteRegionElementCoutList() {
        return activiteRegionElementCoutList;
    }

    public void setActiviteRegionElementCoutList(List<ActiviteRegionElementCout> activiteRegionElementCoutList) {
        this.activiteRegionElementCoutList = activiteRegionElementCoutList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactiviteRegion != null ? idactiviteRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActiviteRegion)) {
            return false;
        }
        ActiviteRegion other = (ActiviteRegion) object;
        if ((this.idactiviteRegion == null && other.idactiviteRegion != null) || (this.idactiviteRegion != null && !this.idactiviteRegion.equals(other.idactiviteRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ActiviteRegion[ idactiviteRegion=" + idactiviteRegion + " ]";
    }
    
}

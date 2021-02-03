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
@Table(name = "cible_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CibleRegion.findAll", query = "SELECT c FROM CibleRegion c"),
    @NamedQuery(name = "CibleRegion.findByIdcibleRegion", query = "SELECT c FROM CibleRegion c WHERE c.idcibleRegion = :idcibleRegion"),
    @NamedQuery(name = "CibleRegion.findByValeur", query = "SELECT c FROM CibleRegion c WHERE c.valeur = :valeur"),
    @NamedQuery(name = "CibleRegion.findByValeurrealisee", query = "SELECT c FROM CibleRegion c WHERE c.valeurrealisee = :valeurrealisee"),
    @NamedQuery(name = "CibleRegion.findByCommentaire", query = "SELECT c FROM CibleRegion c WHERE c.commentaire = :commentaire"),
    @NamedQuery(name = "CibleRegion.findByRecommandation", query = "SELECT c FROM CibleRegion c WHERE c.recommandation = :recommandation"),
    @NamedQuery(name = "CibleRegion.findByEtat", query = "SELECT c FROM CibleRegion c WHERE c.etat = :etat")})
public class CibleRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcible_region")
    private Long idcibleRegion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double valeur;
    private Double valeurrealisee;
    @Size(max = 2147483647)
    private String commentaire;
    @Size(max = 2147483647)
    private String recommandation;
    private Boolean etat;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idindicateur", referencedColumnName = "idindicateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Indicateur idindicateur;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @OneToMany(mappedBy = "idcibleRegion", fetch = FetchType.LAZY)
    private List<CibleRegionValeur> cibleRegionValeurList;

    public CibleRegion() {
    }

    public CibleRegion(Long idcibleRegion) {
        this.idcibleRegion = idcibleRegion;
    }

    public Long getIdcibleRegion() {
        return idcibleRegion;
    }

    public void setIdcibleRegion(Long idcibleRegion) {
        this.idcibleRegion = idcibleRegion;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public Double getValeurrealisee() {
        return valeurrealisee;
    }

    public void setValeurrealisee(Double valeurrealisee) {
        this.valeurrealisee = valeurrealisee;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getRecommandation() {
        return recommandation;
    }

    public void setRecommandation(String recommandation) {
        this.recommandation = recommandation;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
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

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    @XmlTransient
    public List<CibleRegionValeur> getCibleRegionValeurList() {
        return cibleRegionValeurList;
    }

    public void setCibleRegionValeurList(List<CibleRegionValeur> cibleRegionValeurList) {
        this.cibleRegionValeurList = cibleRegionValeurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcibleRegion != null ? idcibleRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CibleRegion)) {
            return false;
        }
        CibleRegion other = (CibleRegion) object;
        if ((this.idcibleRegion == null && other.idcibleRegion != null) || (this.idcibleRegion != null && !this.idcibleRegion.equals(other.idcibleRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CibleRegion[ idcibleRegion=" + idcibleRegion + " ]";
    }
    
}

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
@Table(name = "cible_region_valeur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CibleRegionValeur.findAll", query = "SELECT c FROM CibleRegionValeur c"),
    @NamedQuery(name = "CibleRegionValeur.findByIdcibleRegionValeur", query = "SELECT c FROM CibleRegionValeur c WHERE c.idcibleRegionValeur = :idcibleRegionValeur"),
    @NamedQuery(name = "CibleRegionValeur.findByValeurcible", query = "SELECT c FROM CibleRegionValeur c WHERE c.valeurcible = :valeurcible"),
    @NamedQuery(name = "CibleRegionValeur.findByValeurrealisee", query = "SELECT c FROM CibleRegionValeur c WHERE c.valeurrealisee = :valeurrealisee"),
    @NamedQuery(name = "CibleRegionValeur.findByEcart", query = "SELECT c FROM CibleRegionValeur c WHERE c.ecart = :ecart"),
    @NamedQuery(name = "CibleRegionValeur.findByCommentaire", query = "SELECT c FROM CibleRegionValeur c WHERE c.commentaire = :commentaire"),
    @NamedQuery(name = "CibleRegionValeur.findByRecommandation", query = "SELECT c FROM CibleRegionValeur c WHERE c.recommandation = :recommandation"),
    @NamedQuery(name = "CibleRegionValeur.findByEvaluee", query = "SELECT c FROM CibleRegionValeur c WHERE c.evaluee = :evaluee")})
public class CibleRegionValeur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcible_region_valeur")
    private Long idcibleRegionValeur;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double valeurcible;
    private Double valeurrealisee;
    private Double ecart;
    @Size(max = 2147483647)
    private String commentaire;
    @Size(max = 2147483647)
    private String recommandation;
    private Boolean evaluee;
    private Boolean etat;
    @JoinColumn(name = "idcible_region", referencedColumnName = "idcible_region")
    @ManyToOne(fetch = FetchType.LAZY)
    private CibleRegion idcibleRegion;
    @JoinColumn(name = "idperioderattachement", referencedColumnName = "idperiodederattachement")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periodederattachement idperioderattachement;

    public CibleRegionValeur() {
    }

    public CibleRegionValeur(Long idcibleRegionValeur) {
        this.idcibleRegionValeur = idcibleRegionValeur;
    }

    public Long getIdcibleRegionValeur() {
        return idcibleRegionValeur;
    }

    public void setIdcibleRegionValeur(Long idcibleRegionValeur) {
        this.idcibleRegionValeur = idcibleRegionValeur;
    }

    public Double getValeurcible() {
        return valeurcible;
    }

    public void setValeurcible(Double valeurcible) {
        this.valeurcible = valeurcible;
    }

    public Double getValeurrealisee() {
        return valeurrealisee;
    }

    public void setValeurrealisee(Double valeurrealisee) {
        this.valeurrealisee = valeurrealisee;
    }

    public Double getEcart() {
        return ecart;
    }

    public void setEcart(Double ecart) {
        this.ecart = ecart;
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

    public Boolean getEvaluee() {
        return evaluee;
    }

    public void setEvaluee(Boolean evaluee) {
        this.evaluee = evaluee;
    }

    public CibleRegion getIdcibleRegion() {
        return idcibleRegion;
    }

    public void setIdcibleRegion(CibleRegion idcibleRegion) {
        this.idcibleRegion = idcibleRegion;
    }

    public Periodederattachement getIdperioderattachement() {
        return idperioderattachement;
    }

    public void setIdperioderattachement(Periodederattachement idperioderattachement) {
        this.idperioderattachement = idperioderattachement;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcibleRegionValeur != null ? idcibleRegionValeur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CibleRegionValeur)) {
            return false;
        }
        CibleRegionValeur other = (CibleRegionValeur) object;
        if ((this.idcibleRegionValeur == null && other.idcibleRegionValeur != null) || (this.idcibleRegionValeur != null && !this.idcibleRegionValeur.equals(other.idcibleRegionValeur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CibleRegionValeur[ idcibleRegionValeur=" + idcibleRegionValeur + " ]";
    }

}

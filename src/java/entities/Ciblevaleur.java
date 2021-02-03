/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciblevaleur.findAll", query = "SELECT c FROM Ciblevaleur c"),
    @NamedQuery(name = "Ciblevaleur.findByIdciblevaleur", query = "SELECT c FROM Ciblevaleur c WHERE c.idciblevaleur = :idciblevaleur"),
    @NamedQuery(name = "Ciblevaleur.findByValeurcible", query = "SELECT c FROM Ciblevaleur c WHERE c.valeurcible = :valeurcible"),
    @NamedQuery(name = "Ciblevaleur.findByValeurrealisee", query = "SELECT c FROM Ciblevaleur c WHERE c.valeurrealisee = :valeurrealisee"),
    @NamedQuery(name = "Ciblevaleur.findByCommentaire", query = "SELECT c FROM Ciblevaleur c WHERE c.commentaire = :commentaire"),
    @NamedQuery(name = "Ciblevaleur.findByRecommandation", query = "SELECT c FROM Ciblevaleur c WHERE c.recommandation = :recommandation"),
    @NamedQuery(name = "Ciblevaleur.findByEtat", query = "SELECT c FROM Ciblevaleur c WHERE c.etat = :etat"),
    @NamedQuery(name = "Ciblevaleur.findByEcart", query = "SELECT c FROM Ciblevaleur c WHERE c.ecart = :ecart"),
    @NamedQuery(name = "Ciblevaleur.findByEvaluee", query = "SELECT c FROM Ciblevaleur c WHERE c.evaluee = :evaluee")})
public class Ciblevaleur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idciblevaleur;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double valeurcible;
    private Double valeurrealisee;
    @Size(max = 2147483647)
    private String commentaire;
    @Size(max = 2147483647)
    private String recommandation;
    private Boolean etat;
    private Double ecart;
    private Boolean evaluee;
    @JoinColumn(name = "idcible", referencedColumnName = "idcible")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cible idcible;
    @JoinColumn(name = "idperioderattachement", referencedColumnName = "idperiodederattachement")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periodederattachement idperioderattachement;

    public Ciblevaleur() {
    }

    public Ciblevaleur(Long idciblevaleur) {
        this.idciblevaleur = idciblevaleur;
    }

    public Long getIdciblevaleur() {
        return idciblevaleur;
    }

    public void setIdciblevaleur(Long idciblevaleur) {
        this.idciblevaleur = idciblevaleur;
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

    public Double getEcart() {
        return ecart;
    }

    public void setEcart(Double ecart) {
        this.ecart = ecart;
    }

    public Boolean getEvaluee() {
        return evaluee;
    }

    public void setEvaluee(Boolean evaluee) {
        this.evaluee = evaluee;
    }

    public Cible getIdcible() {
        return idcible;
    }

    public void setIdcible(Cible idcible) {
        this.idcible = idcible;
    }

    public Periodederattachement getIdperioderattachement() {
        return idperioderattachement;
    }

    public void setIdperioderattachement(Periodederattachement idperioderattachement) {
        this.idperioderattachement = idperioderattachement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idciblevaleur != null ? idciblevaleur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciblevaleur)) {
            return false;
        }
        Ciblevaleur other = (Ciblevaleur) object;
        if ((this.idciblevaleur == null && other.idciblevaleur != null) || (this.idciblevaleur != null && !this.idciblevaleur.equals(other.idciblevaleur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ciblevaleur[ idciblevaleur=" + idciblevaleur + " ]";
    }
    
}

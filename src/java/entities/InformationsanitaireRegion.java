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
@Table(name = "informationsanitaire_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformationsanitaireRegion.findAll", query = "SELECT i FROM InformationsanitaireRegion i"),
    @NamedQuery(name = "InformationsanitaireRegion.findByIdinformationsanitaireRegion", query = "SELECT i FROM InformationsanitaireRegion i WHERE i.idinformationsanitaireRegion = :idinformationsanitaireRegion"),
    @NamedQuery(name = "InformationsanitaireRegion.findByValeur", query = "SELECT i FROM InformationsanitaireRegion i WHERE i.valeur = :valeur")})
public class InformationsanitaireRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idinformationsanitaire_region")
    private Long idinformationsanitaireRegion;
    @Size(max = 2147483647)
    private String valeur;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @JoinColumn(name = "idrubriqueinfosanitaire", referencedColumnName = "idrubriqueinfosanitaire")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriqueinfosanitaire idrubriqueinfosanitaire;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public InformationsanitaireRegion() {
    }

    public InformationsanitaireRegion(Long idinformationsanitaireRegion) {
        this.idinformationsanitaireRegion = idinformationsanitaireRegion;
    }

    public Long getIdinformationsanitaireRegion() {
        return idinformationsanitaireRegion;
    }

    public void setIdinformationsanitaireRegion(Long idinformationsanitaireRegion) {
        this.idinformationsanitaireRegion = idinformationsanitaireRegion;
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

    public Rubriqueinfosanitaire getIdrubriqueinfosanitaire() {
        return idrubriqueinfosanitaire;
    }

    public void setIdrubriqueinfosanitaire(Rubriqueinfosanitaire idrubriqueinfosanitaire) {
        this.idrubriqueinfosanitaire = idrubriqueinfosanitaire;
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
        hash += (idinformationsanitaireRegion != null ? idinformationsanitaireRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformationsanitaireRegion)) {
            return false;
        }
        InformationsanitaireRegion other = (InformationsanitaireRegion) object;
        if ((this.idinformationsanitaireRegion == null && other.idinformationsanitaireRegion != null) || (this.idinformationsanitaireRegion != null && !this.idinformationsanitaireRegion.equals(other.idinformationsanitaireRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.InformationsanitaireRegion[ idinformationsanitaireRegion=" + idinformationsanitaireRegion + " ]";
    }

}

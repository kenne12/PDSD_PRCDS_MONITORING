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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "utilisateur_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UtilisateurRegion.findAll", query = "SELECT u FROM UtilisateurRegion u"),
    @NamedQuery(name = "UtilisateurRegion.findByIdutilisateurRegion", query = "SELECT u FROM UtilisateurRegion u WHERE u.idutilisateurRegion = :idutilisateurRegion")})
public class UtilisateurRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idutilisateur_region")
    private Long idutilisateurRegion;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur idutilisateur;

    public UtilisateurRegion() {
    }

    public UtilisateurRegion(Long idutilisateurRegion) {
        this.idutilisateurRegion = idutilisateurRegion;
    }

    public Long getIdutilisateurRegion() {
        return idutilisateurRegion;
    }

    public void setIdutilisateurRegion(Long idutilisateurRegion) {
        this.idutilisateurRegion = idutilisateurRegion;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    public Utilisateur getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Utilisateur idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idutilisateurRegion != null ? idutilisateurRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UtilisateurRegion)) {
            return false;
        }
        UtilisateurRegion other = (UtilisateurRegion) object;
        if ((this.idutilisateurRegion == null && other.idutilisateurRegion != null) || (this.idutilisateurRegion != null && !this.idutilisateurRegion.equals(other.idutilisateurRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UtilisateurRegion[ idutilisateurRegion=" + idutilisateurRegion + " ]";
    }
    
}

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
@Table(name = "commentaire_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommentaireRegion.findAll", query = "SELECT c FROM CommentaireRegion c"),
    @NamedQuery(name = "CommentaireRegion.findByIdcommentaireRegion", query = "SELECT c FROM CommentaireRegion c WHERE c.idcommentaireRegion = :idcommentaireRegion"),
    @NamedQuery(name = "CommentaireRegion.findByCommentaire", query = "SELECT c FROM CommentaireRegion c WHERE c.commentaire = :commentaire"),
    @NamedQuery(name = "CommentaireRegion.findByNumerotab", query = "SELECT c FROM CommentaireRegion c WHERE c.numerotab = :numerotab")})
public class CommentaireRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcommentaire_region")
    private Long idcommentaireRegion;
    @Size(max = 2147483647)
    private String commentaire;
    private Integer numerotab;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;

    public CommentaireRegion() {
    }

    public CommentaireRegion(Long idcommentaireRegion) {
        this.idcommentaireRegion = idcommentaireRegion;
    }

    public Long getIdcommentaireRegion() {
        return idcommentaireRegion;
    }

    public void setIdcommentaireRegion(Long idcommentaireRegion) {
        this.idcommentaireRegion = idcommentaireRegion;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Integer getNumerotab() {
        return numerotab;
    }

    public void setNumerotab(Integer numerotab) {
        this.numerotab = numerotab;
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
        hash += (idcommentaireRegion != null ? idcommentaireRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommentaireRegion)) {
            return false;
        }
        CommentaireRegion other = (CommentaireRegion) object;
        if ((this.idcommentaireRegion == null && other.idcommentaireRegion != null) || (this.idcommentaireRegion != null && !this.idcommentaireRegion.equals(other.idcommentaireRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CommentaireRegion[ idcommentaireRegion=" + idcommentaireRegion + " ]";
    }
    
}

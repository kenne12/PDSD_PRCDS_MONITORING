/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "pathologie_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PathologieRegion.findAll", query = "SELECT p FROM PathologieRegion p"),
    @NamedQuery(name = "PathologieRegion.findByIdpathologieRegion", query = "SELECT p FROM PathologieRegion p WHERE p.idpathologieRegion = :idpathologieRegion"),
    @NamedQuery(name = "PathologieRegion.findByValeur", query = "SELECT p FROM PathologieRegion p WHERE p.valeur = :valeur")})
public class PathologieRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpathologie_region")
    private Long idpathologieRegion;
    private BigInteger valeur;
    @JoinColumn(name = "idpathologie", referencedColumnName = "idpathologie")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pathologie idpathologie;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;

    public PathologieRegion() {
    }

    public PathologieRegion(Long idpathologieRegion) {
        this.idpathologieRegion = idpathologieRegion;
    }

    public Long getIdpathologieRegion() {
        return idpathologieRegion;
    }

    public void setIdpathologieRegion(Long idpathologieRegion) {
        this.idpathologieRegion = idpathologieRegion;
    }

    public BigInteger getValeur() {
        return valeur;
    }

    public void setValeur(BigInteger valeur) {
        this.valeur = valeur;
    }

    public Pathologie getIdpathologie() {
        return idpathologie;
    }

    public void setIdpathologie(Pathologie idpathologie) {
        this.idpathologie = idpathologie;
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
        hash += (idpathologieRegion != null ? idpathologieRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PathologieRegion)) {
            return false;
        }
        PathologieRegion other = (PathologieRegion) object;
        if ((this.idpathologieRegion == null && other.idpathologieRegion != null) || (this.idpathologieRegion != null && !this.idpathologieRegion.equals(other.idpathologieRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PathologieRegion[ idpathologieRegion=" + idpathologieRegion + " ]";
    }
    
}

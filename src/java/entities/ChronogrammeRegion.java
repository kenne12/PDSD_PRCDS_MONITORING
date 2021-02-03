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
@Table(name = "chronogramme_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChronogrammeRegion.findAll", query = "SELECT c FROM ChronogrammeRegion c"),
    @NamedQuery(name = "ChronogrammeRegion.findByIdchronogrammeRegion", query = "SELECT c FROM ChronogrammeRegion c WHERE c.idchronogrammeRegion = :idchronogrammeRegion"),
    @NamedQuery(name = "ChronogrammeRegion.findByEtat", query = "SELECT c FROM ChronogrammeRegion c WHERE c.etat = :etat")})
public class ChronogrammeRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idchronogramme_region")
    private Long idchronogrammeRegion;
    private Boolean etat;
    @JoinColumn(name = "idactivite_region", referencedColumnName = "idactivite_region")
    @ManyToOne(fetch = FetchType.LAZY)
    private ActiviteRegion idactiviteRegion;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;

    public ChronogrammeRegion() {
    }

    public ChronogrammeRegion(Long idchronogrammeRegion) {
        this.idchronogrammeRegion = idchronogrammeRegion;
    }

    public Long getIdchronogrammeRegion() {
        return idchronogrammeRegion;
    }

    public void setIdchronogrammeRegion(Long idchronogrammeRegion) {
        this.idchronogrammeRegion = idchronogrammeRegion;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public ActiviteRegion getIdactiviteRegion() {
        return idactiviteRegion;
    }

    public void setIdactiviteRegion(ActiviteRegion idactiviteRegion) {
        this.idactiviteRegion = idactiviteRegion;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idchronogrammeRegion != null ? idchronogrammeRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChronogrammeRegion)) {
            return false;
        }
        ChronogrammeRegion other = (ChronogrammeRegion) object;
        if ((this.idchronogrammeRegion == null && other.idchronogrammeRegion != null) || (this.idchronogrammeRegion != null && !this.idchronogrammeRegion.equals(other.idchronogrammeRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ChronogrammeRegion[ idchronogrammeRegion=" + idchronogrammeRegion + " ]";
    }
    
}

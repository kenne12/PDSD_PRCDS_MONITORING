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
@Table(name = "chronogramme_tache_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChronogrammeTacheRegion.findAll", query = "SELECT c FROM ChronogrammeTacheRegion c"),
    @NamedQuery(name = "ChronogrammeTacheRegion.findByIdchronogrammeTacheRegion", query = "SELECT c FROM ChronogrammeTacheRegion c WHERE c.idchronogrammeTacheRegion = :idchronogrammeTacheRegion")})
public class ChronogrammeTacheRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idchronogramme_tache_region")
    private Long idchronogrammeTacheRegion;
    @JoinColumn(name = "idperiode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periode idperiode;
    @JoinColumn(name = "idtacheregion", referencedColumnName = "idtacheregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tacheregion idtacheregion;
    @JoinColumn(name = "idtacheregion_periode", referencedColumnName = "idtacheregion_periode")
    @ManyToOne(fetch = FetchType.LAZY)
    private TacheregionPeriode idtacheregionPeriode;

    public ChronogrammeTacheRegion() {
    }

    public ChronogrammeTacheRegion(Long idchronogrammeTacheRegion) {
        this.idchronogrammeTacheRegion = idchronogrammeTacheRegion;
    }

    public Long getIdchronogrammeTacheRegion() {
        return idchronogrammeTacheRegion;
    }

    public void setIdchronogrammeTacheRegion(Long idchronogrammeTacheRegion) {
        this.idchronogrammeTacheRegion = idchronogrammeTacheRegion;
    }

    public Periode getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Periode idperiode) {
        this.idperiode = idperiode;
    }

    public Tacheregion getIdtacheregion() {
        return idtacheregion;
    }

    public void setIdtacheregion(Tacheregion idtacheregion) {
        this.idtacheregion = idtacheregion;
    }

    public TacheregionPeriode getIdtacheregionPeriode() {
        return idtacheregionPeriode;
    }

    public void setIdtacheregionPeriode(TacheregionPeriode idtacheregionPeriode) {
        this.idtacheregionPeriode = idtacheregionPeriode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idchronogrammeTacheRegion != null ? idchronogrammeTacheRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChronogrammeTacheRegion)) {
            return false;
        }
        ChronogrammeTacheRegion other = (ChronogrammeTacheRegion) object;
        if ((this.idchronogrammeTacheRegion == null && other.idchronogrammeTacheRegion != null) || (this.idchronogrammeTacheRegion != null && !this.idchronogrammeTacheRegion.equals(other.idchronogrammeTacheRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ChronogrammeTacheRegion[ idchronogrammeTacheRegion=" + idchronogrammeTacheRegion + " ]";
    }
    
}

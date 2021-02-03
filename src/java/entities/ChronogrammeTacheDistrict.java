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
@Table(name = "chronogramme_tache_district")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChronogrammeTacheDistrict.findAll", query = "SELECT c FROM ChronogrammeTacheDistrict c"),
    @NamedQuery(name = "ChronogrammeTacheDistrict.findByIdchronogrammeTacheDistrict", query = "SELECT c FROM ChronogrammeTacheDistrict c WHERE c.idchronogrammeTacheDistrict = :idchronogrammeTacheDistrict")})
public class ChronogrammeTacheDistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idchronogramme_tache_district")
    private Long idchronogrammeTacheDistrict;
    @JoinColumn(name = "idperiode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periode idperiode;
    @JoinColumn(name = "idtachedistrict", referencedColumnName = "idtachedistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tachedistrict idtachedistrict;
    @JoinColumn(name = "idtachedistrict_periode", referencedColumnName = "idtachedistrict_periode")
    @ManyToOne(fetch = FetchType.LAZY)
    private TachedistrictPeriode idtachedistrictPeriode;

    public ChronogrammeTacheDistrict() {
    }

    public ChronogrammeTacheDistrict(Long idchronogrammeTacheDistrict) {
        this.idchronogrammeTacheDistrict = idchronogrammeTacheDistrict;
    }

    public Long getIdchronogrammeTacheDistrict() {
        return idchronogrammeTacheDistrict;
    }

    public void setIdchronogrammeTacheDistrict(Long idchronogrammeTacheDistrict) {
        this.idchronogrammeTacheDistrict = idchronogrammeTacheDistrict;
    }

    public Periode getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Periode idperiode) {
        this.idperiode = idperiode;
    }

    public Tachedistrict getIdtachedistrict() {
        return idtachedistrict;
    }

    public void setIdtachedistrict(Tachedistrict idtachedistrict) {
        this.idtachedistrict = idtachedistrict;
    }

    public TachedistrictPeriode getIdtachedistrictPeriode() {
        return idtachedistrictPeriode;
    }

    public void setIdtachedistrictPeriode(TachedistrictPeriode idtachedistrictPeriode) {
        this.idtachedistrictPeriode = idtachedistrictPeriode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idchronogrammeTacheDistrict != null ? idchronogrammeTacheDistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChronogrammeTacheDistrict)) {
            return false;
        }
        ChronogrammeTacheDistrict other = (ChronogrammeTacheDistrict) object;
        if ((this.idchronogrammeTacheDistrict == null && other.idchronogrammeTacheDistrict != null) || (this.idchronogrammeTacheDistrict != null && !this.idchronogrammeTacheDistrict.equals(other.idchronogrammeTacheDistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ChronogrammeTacheDistrict[ idchronogrammeTacheDistrict=" + idchronogrammeTacheDistrict + " ]";
    }
    
}

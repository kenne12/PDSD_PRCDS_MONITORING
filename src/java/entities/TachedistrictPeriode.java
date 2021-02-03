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
@Table(name = "tachedistrict_periode")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TachedistrictPeriode.findAll", query = "SELECT t FROM TachedistrictPeriode t"),
    @NamedQuery(name = "TachedistrictPeriode.findByIdtachedistrictPeriode", query = "SELECT t FROM TachedistrictPeriode t WHERE t.idtachedistrictPeriode = :idtachedistrictPeriode"),
    @NamedQuery(name = "TachedistrictPeriode.findByObservation", query = "SELECT t FROM TachedistrictPeriode t WHERE t.observation = :observation")})
    
public class TachedistrictPeriode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtachedistrict_periode")
    private Long idtachedistrictPeriode;
    @Size(max = 2147483647)
    private String observation;
    @JoinColumn(name = "idniveauactivite", referencedColumnName = "idniveauactivite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Niveauactivite idniveauactivite;
    @JoinColumn(name = "idperioderattachement", referencedColumnName = "idperiodederattachement")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periodederattachement idperioderattachement;
    @JoinColumn(name = "idtachedistrict", referencedColumnName = "idtachedistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tachedistrict idtachedistrict;
    @OneToMany(mappedBy = "idtachedistrictPeriode", fetch = FetchType.LAZY)
    private List<ChronogrammeTacheDistrict> chronogrammeTacheDistrictList;

    public TachedistrictPeriode() {
    }

    public TachedistrictPeriode(Long idtachedistrictPeriode) {
        this.idtachedistrictPeriode = idtachedistrictPeriode;
    }

    public Long getIdtachedistrictPeriode() {
        return idtachedistrictPeriode;
    }

    public void setIdtachedistrictPeriode(Long idtachedistrictPeriode) {
        this.idtachedistrictPeriode = idtachedistrictPeriode;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Niveauactivite getIdniveauactivite() {
        return idniveauactivite;
    }

    public void setIdniveauactivite(Niveauactivite idniveauactivite) {
        this.idniveauactivite = idniveauactivite;
    }

    public Periodederattachement getIdperioderattachement() {
        return idperioderattachement;
    }

    public void setIdperioderattachement(Periodederattachement idperioderattachement) {
        this.idperioderattachement = idperioderattachement;
    }

    public Tachedistrict getIdtachedistrict() {
        return idtachedistrict;
    }

    public void setIdtachedistrict(Tachedistrict idtachedistrict) {
        this.idtachedistrict = idtachedistrict;
    }

    @XmlTransient
    public List<ChronogrammeTacheDistrict> getChronogrammeTacheDistrictList() {
        return chronogrammeTacheDistrictList;
    }

    public void setChronogrammeTacheDistrictList(List<ChronogrammeTacheDistrict> chronogrammeTacheDistrictList) {
        this.chronogrammeTacheDistrictList = chronogrammeTacheDistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtachedistrictPeriode != null ? idtachedistrictPeriode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TachedistrictPeriode)) {
            return false;
        }
        TachedistrictPeriode other = (TachedistrictPeriode) object;
        if ((this.idtachedistrictPeriode == null && other.idtachedistrictPeriode != null) || (this.idtachedistrictPeriode != null && !this.idtachedistrictPeriode.equals(other.idtachedistrictPeriode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TachedistrictPeriode[ idtachedistrictPeriode=" + idtachedistrictPeriode + " ]";
    }
    
}

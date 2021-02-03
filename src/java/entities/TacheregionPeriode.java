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
@Table(name = "tacheregion_periode")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TacheregionPeriode.findAll", query = "SELECT t FROM TacheregionPeriode t"),
    @NamedQuery(name = "TacheregionPeriode.findByIdtacheregionPeriode", query = "SELECT t FROM TacheregionPeriode t WHERE t.idtacheregionPeriode = :idtacheregionPeriode")})
public class TacheregionPeriode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtacheregion_periode")
    private Long idtacheregionPeriode;
    @Size(max = 2147483647)
    private String observation;
    @JoinColumn(name = "idniveauactivite", referencedColumnName = "idniveauactivite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Niveauactivite idniveauactivite;
    @JoinColumn(name = "idperioderattachement", referencedColumnName = "idperiodederattachement")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periodederattachement idperioderattachement;
    @JoinColumn(name = "idtacheregion", referencedColumnName = "idtacheregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tacheregion idtacheregion;
    @OneToMany(mappedBy = "idtacheregionPeriode", fetch = FetchType.LAZY)
    private List<ChronogrammeTacheRegion> chronogrammeTacheRegionList;

    public TacheregionPeriode() {
    }

    public TacheregionPeriode(Long idtacheregionPeriode) {
        this.idtacheregionPeriode = idtacheregionPeriode;
    }

    public Long getIdtacheregionPeriode() {
        return idtacheregionPeriode;
    }

    public void setIdtacheregionPeriode(Long idtacheregionPeriode) {
        this.idtacheregionPeriode = idtacheregionPeriode;
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

    public Tacheregion getIdtacheregion() {
        return idtacheregion;
    }

    public void setIdtacheregion(Tacheregion idtacheregion) {
        this.idtacheregion = idtacheregion;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @XmlTransient
    public List<ChronogrammeTacheRegion> getChronogrammeTacheRegionList() {
        return chronogrammeTacheRegionList;
    }

    public void setChronogrammeTacheRegionList(List<ChronogrammeTacheRegion> chronogrammeTacheRegionList) {
        this.chronogrammeTacheRegionList = chronogrammeTacheRegionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtacheregionPeriode != null ? idtacheregionPeriode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TacheregionPeriode)) {
            return false;
        }
        TacheregionPeriode other = (TacheregionPeriode) object;
        if ((this.idtacheregionPeriode == null && other.idtacheregionPeriode != null) || (this.idtacheregionPeriode != null && !this.idtacheregionPeriode.equals(other.idtacheregionPeriode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TacheregionPeriode[ idtacheregionPeriode=" + idtacheregionPeriode + " ]";
    }

}

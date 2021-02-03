/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Niveauactivite.findAll", query = "SELECT n FROM Niveauactivite n"),
    @NamedQuery(name = "Niveauactivite.findByIdniveauactivite", query = "SELECT n FROM Niveauactivite n WHERE n.idniveauactivite = :idniveauactivite"),
    @NamedQuery(name = "Niveauactivite.findByNom", query = "SELECT n FROM Niveauactivite n WHERE n.nom = :nom"),
    @NamedQuery(name = "Niveauactivite.findByNumero", query = "SELECT n FROM Niveauactivite n WHERE n.numero = :numero")})
public class Niveauactivite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idniveauactivite;
    @Size(max = 2147483647)
    private String nom;
    private Integer numero;
    @OneToMany(mappedBy = "idniveauactivite", fetch = FetchType.LAZY)
    private List<TachedistrictPeriode> tachedistrictPeriodeList;
    @OneToMany(mappedBy = "idniveauactivite", fetch = FetchType.LAZY)
    private List<Tacheregion> tacheregionList;
    @OneToMany(mappedBy = "idniveauactivite", fetch = FetchType.LAZY)
    private List<TacheregionPeriode> tacheregionPeriodeList;
    @OneToMany(mappedBy = "idniveauactivite", fetch = FetchType.LAZY)
    private List<Tachedistrict> tachedistrictList;

    public Niveauactivite() {
    }

    public Niveauactivite(Integer idniveauactivite) {
        this.idniveauactivite = idniveauactivite;
    }

    public Integer getIdniveauactivite() {
        return idniveauactivite;
    }

    public void setIdniveauactivite(Integer idniveauactivite) {
        this.idniveauactivite = idniveauactivite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @XmlTransient
    public List<TachedistrictPeriode> getTachedistrictPeriodeList() {
        return tachedistrictPeriodeList;
    }

    public void setTachedistrictPeriodeList(List<TachedistrictPeriode> tachedistrictPeriodeList) {
        this.tachedistrictPeriodeList = tachedistrictPeriodeList;
    }

    @XmlTransient
    public List<Tacheregion> getTacheregionList() {
        return tacheregionList;
    }

    public void setTacheregionList(List<Tacheregion> tacheregionList) {
        this.tacheregionList = tacheregionList;
    }

    @XmlTransient
    public List<TacheregionPeriode> getTacheregionPeriodeList() {
        return tacheregionPeriodeList;
    }

    public void setTacheregionPeriodeList(List<TacheregionPeriode> tacheregionPeriodeList) {
        this.tacheregionPeriodeList = tacheregionPeriodeList;
    }

    @XmlTransient
    public List<Tachedistrict> getTachedistrictList() {
        return tachedistrictList;
    }

    public void setTachedistrictList(List<Tachedistrict> tachedistrictList) {
        this.tachedistrictList = tachedistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idniveauactivite != null ? idniveauactivite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Niveauactivite)) {
            return false;
        }
        Niveauactivite other = (Niveauactivite) object;
        if ((this.idniveauactivite == null && other.idniveauactivite != null) || (this.idniveauactivite != null && !this.idniveauactivite.equals(other.idniveauactivite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Niveauactivite[ idniveauactivite=" + idniveauactivite + " ]";
    }
    
}

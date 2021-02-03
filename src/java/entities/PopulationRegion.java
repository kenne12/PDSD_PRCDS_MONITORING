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
@Table(name = "population_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PopulationRegion.findAll", query = "SELECT p FROM PopulationRegion p"),
    @NamedQuery(name = "PopulationRegion.findByIdpopulationRegion", query = "SELECT p FROM PopulationRegion p WHERE p.idpopulationRegion = :idpopulationRegion"),
    @NamedQuery(name = "PopulationRegion.findByValeurpopulationregion", query = "SELECT p FROM PopulationRegion p WHERE p.valeurpopulationregion = :valeurpopulationregion"),
    @NamedQuery(name = "PopulationRegion.findByValeurpopulationrubrique", query = "SELECT p FROM PopulationRegion p WHERE p.valeurpopulationrubrique = :valeurpopulationrubrique")})
public class PopulationRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpopulation_region")
    private Long idpopulationRegion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double valeurpopulationregion;
    private Double valeurpopulationrubrique;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @JoinColumn(name = "idrubriquepopulation", referencedColumnName = "idrubriquepopulation")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriquepopulation idrubriquepopulation;

    public PopulationRegion() {
    }

    public PopulationRegion(Long idpopulationRegion) {
        this.idpopulationRegion = idpopulationRegion;
    }

    public Long getIdpopulationRegion() {
        return idpopulationRegion;
    }

    public void setIdpopulationRegion(Long idpopulationRegion) {
        this.idpopulationRegion = idpopulationRegion;
    }

    public Double getValeurpopulationregion() {
        return valeurpopulationregion;
    }

    public void setValeurpopulationregion(Double valeurpopulationregion) {
        this.valeurpopulationregion = valeurpopulationregion;
    }

    public Double getValeurpopulationrubrique() {
        return valeurpopulationrubrique;
    }

    public void setValeurpopulationrubrique(Double valeurpopulationrubrique) {
        this.valeurpopulationrubrique = valeurpopulationrubrique;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    public Rubriquepopulation getIdrubriquepopulation() {
        return idrubriquepopulation;
    }

    public void setIdrubriquepopulation(Rubriquepopulation idrubriquepopulation) {
        this.idrubriquepopulation = idrubriquepopulation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpopulationRegion != null ? idpopulationRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PopulationRegion)) {
            return false;
        }
        PopulationRegion other = (PopulationRegion) object;
        if ((this.idpopulationRegion == null && other.idpopulationRegion != null) || (this.idpopulationRegion != null && !this.idpopulationRegion.equals(other.idpopulationRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PopulationRegion[ idpopulationRegion=" + idpopulationRegion + " ]";
    }
    
}

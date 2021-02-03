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
@Table(name = "tablematieren1_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablematieren1Region.findAll", query = "SELECT t FROM Tablematieren1Region t"),
    @NamedQuery(name = "Tablematieren1Region.findByIdtablematieren1Region", query = "SELECT t FROM Tablematieren1Region t WHERE t.idtablematieren1Region = :idtablematieren1Region"),
    @NamedQuery(name = "Tablematieren1Region.findByNumeropage", query = "SELECT t FROM Tablematieren1Region t WHERE t.numeropage = :numeropage")})
public class Tablematieren1Region implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtablematieren1_region")
    private Long idtablematieren1Region;
    private Integer numeropage;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @JoinColumn(name = "idtablematiere_n1", referencedColumnName = "idtablematiere_n1")
    @ManyToOne(fetch = FetchType.LAZY)
    private TablematiereN1 idtablematiereN1;

    public Tablematieren1Region() {
    }

    public Tablematieren1Region(Long idtablematieren1Region) {
        this.idtablematieren1Region = idtablematieren1Region;
    }

    public Long getIdtablematieren1Region() {
        return idtablematieren1Region;
    }

    public void setIdtablematieren1Region(Long idtablematieren1Region) {
        this.idtablematieren1Region = idtablematieren1Region;
    }

    public Integer getNumeropage() {
        return numeropage;
    }

    public void setNumeropage(Integer numeropage) {
        this.numeropage = numeropage;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    public TablematiereN1 getIdtablematiereN1() {
        return idtablematiereN1;
    }

    public void setIdtablematiereN1(TablematiereN1 idtablematiereN1) {
        this.idtablematiereN1 = idtablematiereN1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtablematieren1Region != null ? idtablematieren1Region.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablematieren1Region)) {
            return false;
        }
        Tablematieren1Region other = (Tablematieren1Region) object;
        if ((this.idtablematieren1Region == null && other.idtablematieren1Region != null) || (this.idtablematieren1Region != null && !this.idtablematieren1Region.equals(other.idtablematieren1Region))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tablematieren1Region[ idtablematieren1Region=" + idtablematieren1Region + " ]";
    }
    
}

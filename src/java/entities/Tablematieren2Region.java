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
@Table(name = "tablematieren2_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablematieren2Region.findAll", query = "SELECT t FROM Tablematieren2Region t"),
    @NamedQuery(name = "Tablematieren2Region.findByIdtablematieren2Region", query = "SELECT t FROM Tablematieren2Region t WHERE t.idtablematieren2Region = :idtablematieren2Region"),
    @NamedQuery(name = "Tablematieren2Region.findByNumeropage", query = "SELECT t FROM Tablematieren2Region t WHERE t.numeropage = :numeropage")})
public class Tablematieren2Region implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtablematieren2_region")
    private Long idtablematieren2Region;
    private Integer numeropage;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @JoinColumn(name = "idtablematiere_n2", referencedColumnName = "idtablematiere_n2")
    @ManyToOne(fetch = FetchType.LAZY)
    private TablematiereN2 idtablematiereN2;

    public Tablematieren2Region() {
    }

    public Tablematieren2Region(Long idtablematieren2Region) {
        this.idtablematieren2Region = idtablematieren2Region;
    }

    public Long getIdtablematieren2Region() {
        return idtablematieren2Region;
    }

    public void setIdtablematieren2Region(Long idtablematieren2Region) {
        this.idtablematieren2Region = idtablematieren2Region;
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

    public TablematiereN2 getIdtablematiereN2() {
        return idtablematiereN2;
    }

    public void setIdtablematiereN2(TablematiereN2 idtablematiereN2) {
        this.idtablematiereN2 = idtablematiereN2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtablematieren2Region != null ? idtablematieren2Region.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablematieren2Region)) {
            return false;
        }
        Tablematieren2Region other = (Tablematieren2Region) object;
        if ((this.idtablematieren2Region == null && other.idtablematieren2Region != null) || (this.idtablematieren2Region != null && !this.idtablematieren2Region.equals(other.idtablematieren2Region))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tablematieren2Region[ idtablematieren2Region=" + idtablematieren2Region + " ]";
    }
    
}

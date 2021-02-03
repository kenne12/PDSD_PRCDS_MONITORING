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
@Table(name = "tablematieren3_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablematieren3Region.findAll", query = "SELECT t FROM Tablematieren3Region t"),
    @NamedQuery(name = "Tablematieren3Region.findByIdtablematieren3Region", query = "SELECT t FROM Tablematieren3Region t WHERE t.idtablematieren3Region = :idtablematieren3Region"),
    @NamedQuery(name = "Tablematieren3Region.findByNumeropage", query = "SELECT t FROM Tablematieren3Region t WHERE t.numeropage = :numeropage")})
public class Tablematieren3Region implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtablematieren3_region")
    private Long idtablematieren3Region;
    private Integer numeropage;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;
    @JoinColumn(name = "idtablematiere_n3", referencedColumnName = "idtablematiere_n3")
    @ManyToOne(fetch = FetchType.LAZY)
    private TablematiereN3 idtablematiereN3;

    public Tablematieren3Region() {
    }

    public Tablematieren3Region(Long idtablematieren3Region) {
        this.idtablematieren3Region = idtablematieren3Region;
    }

    public Long getIdtablematieren3Region() {
        return idtablematieren3Region;
    }

    public void setIdtablematieren3Region(Long idtablematieren3Region) {
        this.idtablematieren3Region = idtablematieren3Region;
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

    public TablematiereN3 getIdtablematiereN3() {
        return idtablematiereN3;
    }

    public void setIdtablematiereN3(TablematiereN3 idtablematiereN3) {
        this.idtablematiereN3 = idtablematiereN3;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtablematieren3Region != null ? idtablematieren3Region.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablematieren3Region)) {
            return false;
        }
        Tablematieren3Region other = (Tablematieren3Region) object;
        if ((this.idtablematieren3Region == null && other.idtablematieren3Region != null) || (this.idtablematieren3Region != null && !this.idtablematieren3Region.equals(other.idtablematieren3Region))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tablematieren3Region[ idtablematieren3Region=" + idtablematieren3Region + " ]";
    }
    
}

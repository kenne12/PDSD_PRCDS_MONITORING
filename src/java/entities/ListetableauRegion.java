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
@Table(name = "listetableau_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListetableauRegion.findAll", query = "SELECT l FROM ListetableauRegion l"),
    @NamedQuery(name = "ListetableauRegion.findByIdlistetableauRegion", query = "SELECT l FROM ListetableauRegion l WHERE l.idlistetableauRegion = :idlistetableauRegion"),
    @NamedQuery(name = "ListetableauRegion.findByNumpage", query = "SELECT l FROM ListetableauRegion l WHERE l.numpage = :numpage")})
public class ListetableauRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idlistetableau_region")
    private Long idlistetableauRegion;
    private Integer numpage;
    @JoinColumn(name = "idlistetableau", referencedColumnName = "idlisttableau")
    @ManyToOne(fetch = FetchType.LAZY)
    private Listetableau idlistetableau;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;

    public ListetableauRegion() {
    }

    public ListetableauRegion(Long idlistetableauRegion) {
        this.idlistetableauRegion = idlistetableauRegion;
    }

    public Long getIdlistetableauRegion() {
        return idlistetableauRegion;
    }

    public void setIdlistetableauRegion(Long idlistetableauRegion) {
        this.idlistetableauRegion = idlistetableauRegion;
    }

    public Integer getNumpage() {
        return numpage;
    }

    public void setNumpage(Integer numpage) {
        this.numpage = numpage;
    }

    public Listetableau getIdlistetableau() {
        return idlistetableau;
    }

    public void setIdlistetableau(Listetableau idlistetableau) {
        this.idlistetableau = idlistetableau;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
        this.idregion = idregion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlistetableauRegion != null ? idlistetableauRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListetableauRegion)) {
            return false;
        }
        ListetableauRegion other = (ListetableauRegion) object;
        if ((this.idlistetableauRegion == null && other.idlistetableauRegion != null) || (this.idlistetableauRegion != null && !this.idlistetableauRegion.equals(other.idlistetableauRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ListetableauRegion[ idlistetableauRegion=" + idlistetableauRegion + " ]";
    }
    
}

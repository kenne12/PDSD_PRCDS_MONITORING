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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "partiehaute_structure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PartiehauteStructure.findAll", query = "SELECT p FROM PartiehauteStructure p"),
    @NamedQuery(name = "PartiehauteStructure.findByIdpartiehauteStructure", query = "SELECT p FROM PartiehauteStructure p WHERE p.idpartiehauteStructure = :idpartiehauteStructure"),
    @NamedQuery(name = "PartiehauteStructure.findByIntrodution", query = "SELECT p FROM PartiehauteStructure p WHERE p.introdution = :introdution"),
    @NamedQuery(name = "PartiehauteStructure.findByDescriptionstructure", query = "SELECT p FROM PartiehauteStructure p WHERE p.descriptionstructure = :descriptionstructure")})
public class PartiehauteStructure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpartiehaute_structure")
    private Integer idpartiehauteStructure;
    @Size(max = 2147483647)
    private String introdution;
    @Size(max = 2147483647)
    private String descriptionstructure;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public PartiehauteStructure() {
    }

    public PartiehauteStructure(Integer idpartiehauteStructure) {
        this.idpartiehauteStructure = idpartiehauteStructure;
    }

    public Integer getIdpartiehauteStructure() {
        return idpartiehauteStructure;
    }

    public void setIdpartiehauteStructure(Integer idpartiehauteStructure) {
        this.idpartiehauteStructure = idpartiehauteStructure;
    }

    public String getIntrodution() {
        return introdution;
    }

    public void setIntrodution(String introdution) {
        this.introdution = introdution;
    }

    public String getDescriptionstructure() {
        return descriptionstructure;
    }

    public void setDescriptionstructure(String descriptionstructure) {
        this.descriptionstructure = descriptionstructure;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpartiehauteStructure != null ? idpartiehauteStructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartiehauteStructure)) {
            return false;
        }
        PartiehauteStructure other = (PartiehauteStructure) object;
        if ((this.idpartiehauteStructure == null && other.idpartiehauteStructure != null) || (this.idpartiehauteStructure != null && !this.idpartiehauteStructure.equals(other.idpartiehauteStructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PartiehauteStructure[ idpartiehauteStructure=" + idpartiehauteStructure + " ]";
    }
    
}

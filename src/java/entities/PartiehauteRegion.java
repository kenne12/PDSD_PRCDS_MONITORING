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
@Table(name = "partiehaute_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PartiehauteRegion.findAll", query = "SELECT p FROM PartiehauteRegion p"),
    @NamedQuery(name = "PartiehauteRegion.findByIdpartiehauteRegion", query = "SELECT p FROM PartiehauteRegion p WHERE p.idpartiehauteRegion = :idpartiehauteRegion"),
    @NamedQuery(name = "PartiehauteRegion.findByIntroduction", query = "SELECT p FROM PartiehauteRegion p WHERE p.introduction = :introduction"),
    @NamedQuery(name = "PartiehauteRegion.findByPresentationgenerale", query = "SELECT p FROM PartiehauteRegion p WHERE p.presentationgenerale = :presentationgenerale"),
    @NamedQuery(name = "PartiehauteRegion.findBySituationgeographique", query = "SELECT p FROM PartiehauteRegion p WHERE p.situationgeographique = :situationgeographique"),
    @NamedQuery(name = "PartiehauteRegion.findBySituationeconomique", query = "SELECT p FROM PartiehauteRegion p WHERE p.situationeconomique = :situationeconomique"),
    @NamedQuery(name = "PartiehauteRegion.findByCarte", query = "SELECT p FROM PartiehauteRegion p WHERE p.carte = :carte"),
    @NamedQuery(name = "PartiehauteRegion.findByDonneeAdmin", query = "SELECT p FROM PartiehauteRegion p WHERE p.donneeAdmin = :donneeAdmin"),
    @NamedQuery(name = "PartiehauteRegion.findByDonneeSocioCult", query = "SELECT p FROM PartiehauteRegion p WHERE p.donneeSocioCult = :donneeSocioCult"),
    @NamedQuery(name = "PartiehauteRegion.findByDonneeDemographique", query = "SELECT p FROM PartiehauteRegion p WHERE p.donneeDemographique = :donneeDemographique")})
public class PartiehauteRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpartiehaute_region")
    private Integer idpartiehauteRegion;
    @Size(max = 2147483647)
    private String introduction;
    @Size(max = 2147483647)
    private String presentationgenerale;
    @Size(max = 2147483647)
    private String situationgeographique;
    @Size(max = 2147483647)
    private String situationeconomique;
    @Size(max = 2147483647)
    private String carte;
    @Size(max = 2147483647)
    @Column(name = "donnee_admin")
    private String donneeAdmin;
    @Size(max = 2147483647)
    @Column(name = "donnee_socio_cult")
    private String donneeSocioCult;
    @Size(max = 2147483647)
    @Column(name = "donnee_demographique")
    private String donneeDemographique;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idregion;

    public PartiehauteRegion() {
    }

    public PartiehauteRegion(Integer idpartiehauteRegion) {
        this.idpartiehauteRegion = idpartiehauteRegion;
    }

    public Integer getIdpartiehauteRegion() {
        return idpartiehauteRegion;
    }

    public void setIdpartiehauteRegion(Integer idpartiehauteRegion) {
        this.idpartiehauteRegion = idpartiehauteRegion;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPresentationgenerale() {
        return presentationgenerale;
    }

    public void setPresentationgenerale(String presentationgenerale) {
        this.presentationgenerale = presentationgenerale;
    }

    public String getSituationgeographique() {
        return situationgeographique;
    }

    public void setSituationgeographique(String situationgeographique) {
        this.situationgeographique = situationgeographique;
    }

    public String getSituationeconomique() {
        return situationeconomique;
    }

    public void setSituationeconomique(String situationeconomique) {
        this.situationeconomique = situationeconomique;
    }

    public String getCarte() {
        return carte;
    }

    public void setCarte(String carte) {
        this.carte = carte;
    }

    public String getDonneeAdmin() {
        return donneeAdmin;
    }

    public void setDonneeAdmin(String donneeAdmin) {
        this.donneeAdmin = donneeAdmin;
    }

    public String getDonneeSocioCult() {
        return donneeSocioCult;
    }

    public void setDonneeSocioCult(String donneeSocioCult) {
        this.donneeSocioCult = donneeSocioCult;
    }

    public String getDonneeDemographique() {
        return donneeDemographique;
    }

    public void setDonneeDemographique(String donneeDemographique) {
        this.donneeDemographique = donneeDemographique;
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
        hash += (idpartiehauteRegion != null ? idpartiehauteRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartiehauteRegion)) {
            return false;
        }
        PartiehauteRegion other = (PartiehauteRegion) object;
        if ((this.idpartiehauteRegion == null && other.idpartiehauteRegion != null) || (this.idpartiehauteRegion != null && !this.idpartiehauteRegion.equals(other.idpartiehauteRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PartiehauteRegion[ idpartiehauteRegion=" + idpartiehauteRegion + " ]";
    }
    
}

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
    @NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r"),
    @NamedQuery(name = "Region.findByIdregion", query = "SELECT r FROM Region r WHERE r.idregion = :idregion"),
    @NamedQuery(name = "Region.findByNomFr", query = "SELECT r FROM Region r WHERE r.nomFr = :nomFr"),
    @NamedQuery(name = "Region.findByCoordonnegps", query = "SELECT r FROM Region r WHERE r.coordonnegps = :coordonnegps"),
    @NamedQuery(name = "Region.findByCoderegion", query = "SELECT r FROM Region r WHERE r.coderegion = :coderegion"),
    @NamedQuery(name = "Region.findByIdpays", query = "SELECT r FROM Region r WHERE r.idpays = :idpays"),
    @NamedQuery(name = "Region.findByNomEn", query = "SELECT r FROM Region r WHERE r.nomEn = :nomEn")})
public class Region implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idregion;
    @Size(max = 254)
    @Column(name = "nom_fr")
    private String nomFr;
    @Size(max = 254)
    private String coordonnegps;
    @Size(max = 254)
    private String coderegion;
    private Integer idpays;
    @Size(max = 2147483647)
    @Column(name = "nom_en")
    private String nomEn;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<Departement> departementList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<FfomRegion> ffomRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<ActeurRegion> acteurRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<PartiehauteRegion> partiehauteRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<Tablematieren3Region> tablematieren3RegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<Tablematieren1Region> tablematieren1RegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<Structurecommunautaire> structurecommunautaireList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<CommentaireRegion> commentaireRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<CibleRegion> cibleRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<UtilisateurRegion> utilisateurRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<GouvernanceRegion> gouvernanceRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<ListetableauRegion> listetableauRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<PopulationRegion> populationRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<ObjectifOppRegion> objectifOppRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<PrevalenceMaladie> prevalenceMaladieList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<InformationsanitaireRegion> informationsanitaireRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<PathologieRegion> pathologieRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<District> districtList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<Actualite> actualiteList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<MortaliteRegion> mortaliteRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<ResultatAttenduRegion> resultatAttenduRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<MapeRegion> mapeRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<IndicateurRegion> indicateurRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<Tablematieren2Region> tablematieren2RegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<MorbiditeRegion> morbiditeRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<FacteurRegion> facteurRegionList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<Structure> structureList;
    @OneToMany(mappedBy = "idregion", fetch = FetchType.LAZY)
    private List<HospitalisationRegion> hospitalisationRegionList;

    public Region() {
    }

    public Region(Integer idregion) {
        this.idregion = idregion;
    }

    public Integer getIdregion() {
        return idregion;
    }

    public void setIdregion(Integer idregion) {
        this.idregion = idregion;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getCoordonnegps() {
        return coordonnegps;
    }

    public void setCoordonnegps(String coordonnegps) {
        this.coordonnegps = coordonnegps;
    }

    public String getCoderegion() {
        return coderegion;
    }

    public void setCoderegion(String coderegion) {
        this.coderegion = coderegion;
    }

    public Integer getIdpays() {
        return idpays;
    }

    public void setIdpays(Integer idpays) {
        this.idpays = idpays;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    @XmlTransient
    public List<Departement> getDepartementList() {
        return departementList;
    }

    public void setDepartementList(List<Departement> departementList) {
        this.departementList = departementList;
    }

    @XmlTransient
    public List<FfomRegion> getFfomRegionList() {
        return ffomRegionList;
    }

    public void setFfomRegionList(List<FfomRegion> ffomRegionList) {
        this.ffomRegionList = ffomRegionList;
    }

    @XmlTransient
    public List<ActeurRegion> getActeurRegionList() {
        return acteurRegionList;
    }

    public void setActeurRegionList(List<ActeurRegion> acteurRegionList) {
        this.acteurRegionList = acteurRegionList;
    }

    @XmlTransient
    public List<PartiehauteRegion> getPartiehauteRegionList() {
        return partiehauteRegionList;
    }

    public void setPartiehauteRegionList(List<PartiehauteRegion> partiehauteRegionList) {
        this.partiehauteRegionList = partiehauteRegionList;
    }

    @XmlTransient
    public List<Tablematieren3Region> getTablematieren3RegionList() {
        return tablematieren3RegionList;
    }

    public void setTablematieren3RegionList(List<Tablematieren3Region> tablematieren3RegionList) {
        this.tablematieren3RegionList = tablematieren3RegionList;
    }

    @XmlTransient
    public List<Tablematieren1Region> getTablematieren1RegionList() {
        return tablematieren1RegionList;
    }

    public void setTablematieren1RegionList(List<Tablematieren1Region> tablematieren1RegionList) {
        this.tablematieren1RegionList = tablematieren1RegionList;
    }

    @XmlTransient
    public List<Structurecommunautaire> getStructurecommunautaireList() {
        return structurecommunautaireList;
    }

    public void setStructurecommunautaireList(List<Structurecommunautaire> structurecommunautaireList) {
        this.structurecommunautaireList = structurecommunautaireList;
    }

    @XmlTransient
    public List<CommentaireRegion> getCommentaireRegionList() {
        return commentaireRegionList;
    }

    public void setCommentaireRegionList(List<CommentaireRegion> commentaireRegionList) {
        this.commentaireRegionList = commentaireRegionList;
    }

    @XmlTransient
    public List<CibleRegion> getCibleRegionList() {
        return cibleRegionList;
    }

    public void setCibleRegionList(List<CibleRegion> cibleRegionList) {
        this.cibleRegionList = cibleRegionList;
    }

    @XmlTransient
    public List<UtilisateurRegion> getUtilisateurRegionList() {
        return utilisateurRegionList;
    }

    public void setUtilisateurRegionList(List<UtilisateurRegion> utilisateurRegionList) {
        this.utilisateurRegionList = utilisateurRegionList;
    }

    @XmlTransient
    public List<GouvernanceRegion> getGouvernanceRegionList() {
        return gouvernanceRegionList;
    }

    public void setGouvernanceRegionList(List<GouvernanceRegion> gouvernanceRegionList) {
        this.gouvernanceRegionList = gouvernanceRegionList;
    }

    @XmlTransient
    public List<ListetableauRegion> getListetableauRegionList() {
        return listetableauRegionList;
    }

    public void setListetableauRegionList(List<ListetableauRegion> listetableauRegionList) {
        this.listetableauRegionList = listetableauRegionList;
    }

    @XmlTransient
    public List<PopulationRegion> getPopulationRegionList() {
        return populationRegionList;
    }

    public void setPopulationRegionList(List<PopulationRegion> populationRegionList) {
        this.populationRegionList = populationRegionList;
    }

    @XmlTransient
    public List<ObjectifOppRegion> getObjectifOppRegionList() {
        return objectifOppRegionList;
    }

    public void setObjectifOppRegionList(List<ObjectifOppRegion> objectifOppRegionList) {
        this.objectifOppRegionList = objectifOppRegionList;
    }

    @XmlTransient
    public List<PrevalenceMaladie> getPrevalenceMaladieList() {
        return prevalenceMaladieList;
    }

    public void setPrevalenceMaladieList(List<PrevalenceMaladie> prevalenceMaladieList) {
        this.prevalenceMaladieList = prevalenceMaladieList;
    }

    @XmlTransient
    public List<InformationsanitaireRegion> getInformationsanitaireRegionList() {
        return informationsanitaireRegionList;
    }

    public void setInformationsanitaireRegionList(List<InformationsanitaireRegion> informationsanitaireRegionList) {
        this.informationsanitaireRegionList = informationsanitaireRegionList;
    }

    @XmlTransient
    public List<PathologieRegion> getPathologieRegionList() {
        return pathologieRegionList;
    }

    public void setPathologieRegionList(List<PathologieRegion> pathologieRegionList) {
        this.pathologieRegionList = pathologieRegionList;
    }

    @XmlTransient
    public List<District> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }

    @XmlTransient
    public List<Actualite> getActualiteList() {
        return actualiteList;
    }

    public void setActualiteList(List<Actualite> actualiteList) {
        this.actualiteList = actualiteList;
    }

    @XmlTransient
    public List<MortaliteRegion> getMortaliteRegionList() {
        return mortaliteRegionList;
    }

    public void setMortaliteRegionList(List<MortaliteRegion> mortaliteRegionList) {
        this.mortaliteRegionList = mortaliteRegionList;
    }

    @XmlTransient
    public List<ResultatAttenduRegion> getResultatAttenduRegionList() {
        return resultatAttenduRegionList;
    }

    public void setResultatAttenduRegionList(List<ResultatAttenduRegion> resultatAttenduRegionList) {
        this.resultatAttenduRegionList = resultatAttenduRegionList;
    }

    @XmlTransient
    public List<MapeRegion> getMapeRegionList() {
        return mapeRegionList;
    }

    public void setMapeRegionList(List<MapeRegion> mapeRegionList) {
        this.mapeRegionList = mapeRegionList;
    }

    @XmlTransient
    public List<IndicateurRegion> getIndicateurRegionList() {
        return indicateurRegionList;
    }

    public void setIndicateurRegionList(List<IndicateurRegion> indicateurRegionList) {
        this.indicateurRegionList = indicateurRegionList;
    }

    @XmlTransient
    public List<Tablematieren2Region> getTablematieren2RegionList() {
        return tablematieren2RegionList;
    }

    public void setTablematieren2RegionList(List<Tablematieren2Region> tablematieren2RegionList) {
        this.tablematieren2RegionList = tablematieren2RegionList;
    }

    @XmlTransient
    public List<MorbiditeRegion> getMorbiditeRegionList() {
        return morbiditeRegionList;
    }

    public void setMorbiditeRegionList(List<MorbiditeRegion> morbiditeRegionList) {
        this.morbiditeRegionList = morbiditeRegionList;
    }

    @XmlTransient
    public List<FacteurRegion> getFacteurRegionList() {
        return facteurRegionList;
    }

    public void setFacteurRegionList(List<FacteurRegion> facteurRegionList) {
        this.facteurRegionList = facteurRegionList;
    }

    @XmlTransient
    public List<Structure> getStructureList() {
        return structureList;
    }

    public void setStructureList(List<Structure> structureList) {
        this.structureList = structureList;
    }

    @XmlTransient
    public List<HospitalisationRegion> getHospitalisationRegionList() {
        return hospitalisationRegionList;
    }

    public void setHospitalisationRegionList(List<HospitalisationRegion> hospitalisationRegionList) {
        this.hospitalisationRegionList = hospitalisationRegionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idregion != null ? idregion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Region)) {
            return false;
        }
        Region other = (Region) object;
        if ((this.idregion == null && other.idregion != null) || (this.idregion != null && !this.idregion.equals(other.idregion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Region[ idregion=" + idregion + " ]";
    }
    
}

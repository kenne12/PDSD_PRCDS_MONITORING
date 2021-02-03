/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Annee;
import entities.Axe;
import entities.Cible;
import entities.CibleRegionValeur;
import entities.Ciblevaleur;
import entities.District;
import entities.Niveauactivite;
import entities.Periodederattachement;
import entities.Sousaxe;
import entities.Tachedistrict;
import entities.TachedistrictPeriode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.UserTransaction;
import org.primefaces.context.RequestContext;
import sessions.AnneeFacadeLocal;
import sessions.AxeFacadeLocal;
import sessions.CibleFacadeLocal;
import sessions.CiblevaleurFacadeLocal;
import sessions.NiveauactiviteFacadeLocal;
import sessions.PeriodederattachementFacadeLocal;
import sessions.SousaxeFacadeLocal;
import sessions.TachedistrictFacadeLocal;
import sessions.TachedistrictPeriodeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class JalondController implements Serializable {
    
    @Resource
    private UserTransaction ut;
    
    @EJB
    private CibleFacadeLocal cibleFacadeLocal;
    private Cible cible = new Cible();
    private List<Cible> cibles = new ArrayList<>();
    
    @EJB
    private AxeFacadeLocal axeFacadeLocal;
    private Axe axe = new Axe();
    private List<Axe> axes = new ArrayList<>();
    
    @EJB
    private SousaxeFacadeLocal sousaxeFacadeLocal;
    private Sousaxe sousaxe = new Sousaxe();
    private List<Sousaxe> sousaxes = new ArrayList<>();
    
    @EJB
    private AnneeFacadeLocal anneeFacadeLocal;
    private Annee annee = SessionMBean.getAnnee();
    private List<Annee> annees = new ArrayList<>();
    
    @EJB
    private TachedistrictFacadeLocal tachedistrictFacadeLocal;
    private Tachedistrict tachedistrict = new Tachedistrict();
    private List<Tachedistrict> tachedistricts = new ArrayList<>();
    
    @EJB
    private TachedistrictPeriodeFacadeLocal tachedistrictPeriodeFacadeLocal;
    private TachedistrictPeriode tachedistrictPeriode = new TachedistrictPeriode();
    private List<TachedistrictPeriode> tachedistrictPeriodes = new ArrayList<>();
    
    @EJB
    private NiveauactiviteFacadeLocal niveauactiviteFacadeLocal;
    private Niveauactivite niveauactivite = new Niveauactivite();
    private List<Niveauactivite> niveauactivites = new ArrayList<>();
    private List<Niveauactivite> niveauactivites1 = new ArrayList<>();
    
    @EJB
    private PeriodederattachementFacadeLocal periodederattachementFacadeLocal;
    private Periodederattachement periodederattachement = new Periodederattachement();
    private List<Periodederattachement> periodederattachements = new ArrayList<>();
    
    @EJB
    private CiblevaleurFacadeLocal ciblevaleurFacadeLocal;
    private Ciblevaleur ciblevaleur = new Ciblevaleur();
    List<Ciblevaleur> ciblevaleurs = new ArrayList<>();
    
    private District district = SessionMBean.getDistrict();
    
    private boolean detail = true;
    
    public JalondController() {
        
    }
    
    @PostConstruct
    private void init() {
        
        axes = axeFacadeLocal.findAllRangeByCode();
        try {
            
            if (!axes.isEmpty()) {
                
                axe = axes.get(0);
                sousaxes = sousaxeFacadeLocal.findByAxe(axe);
                
                if (!sousaxes.isEmpty()) {
                    sousaxe = sousaxes.get(0);
                    cibles = cibleFacadeLocal.findByDistrictSousaxe(district.getIddistrict(), sousaxe.getIdsousaxe(), annee.getIdannee());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void activeButton() {
        detail = false;
    }
    
    public void deactiveButton() {
        detail = true;
    }
    
    public void updateAll() {
        try {
            if (axe != null) {
                sousaxes = sousaxeFacadeLocal.findByAxe(axe);
                
                if (!sousaxes.isEmpty()) {
                    sousaxe = sousaxes.get(0);
                    cibles = cibleFacadeLocal.findByDistrictSousaxe(district.getIddistrict(), sousaxe.getIdsousaxe(), annee.getIdannee());
                } else {
                    sousaxe = new Sousaxe();
                    cibles.clear();
                }
            } else {
                sousaxe = new Sousaxe();
                cibles.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateSousaxe() {
        try {
            if (!sousaxes.isEmpty()) {
                if (sousaxe != null) {
                    cibles = cibleFacadeLocal.findByDistrictSousaxe(district.getIddistrict(), sousaxe.getIdsousaxe(), annee.getIdannee());
                }
            } else {
                sousaxe = new Sousaxe();
                cibles.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Ciblevaleur findResult(Cible cible, Periodederattachement p) {
        try {
            Ciblevaleur ciblevaleur = ciblevaleurFacadeLocal.findByIdcibleIdperiode(cible.getIdcible(), p.getIdperiodederattachement());
            if (ciblevaleur != null) {
                return ciblevaleur;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    
    public void prepareEditData(Cible item) {
        try {
            if (item != null) {
                cible = item;
                ciblevaleurs = ciblevaleurFacadeLocal.findByIdcible(cible.getIdcible());
                if (ciblevaleurs.isEmpty()) {
                    if (ciblevaleurs.isEmpty()) {
                        for (Periodederattachement p : periodederattachements) {
                            Ciblevaleur cv = new Ciblevaleur();
                            cv.setIdciblevaleur(0L);
                            cv.setIdcible(cible);
                            cv.setEtat(false);
                            cv.setEvaluee(false);
                            cv.setIdperioderattachement(p);
                            cv.setValeurcible(item.getValeur() / periodederattachements.size());
                            cv.setRecommandation(" ");
                            cv.setCommentaire(" ");
                            ciblevaleurs.add(cv);
                        }
                    }
                }
                RequestContext.getCurrentInstance().execute("PF('EvaluationEditDialog').show()");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Double sommeData(Cible item) {
        try {
            List<Ciblevaleur> list = ciblevaleurFacadeLocal.findByIdcible(item.getIdcible());
            if (!list.isEmpty()) {
                Double somme = 0d;
                for (Ciblevaleur cv : list) {
                    try {
                        if (cv.getValeurrealisee() != null) {
                            somme += cv.getValeurrealisee();
                        }
                    } catch (Exception e) {
                    }
                }
                return somme;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void editData() {
        try {
            if (!ciblevaleurs.isEmpty()) {
                for (Ciblevaleur c : ciblevaleurs) {
                    if (c.getIdciblevaleur() == 0l) {
                        c.setIdciblevaleur(ciblevaleurFacadeLocal.nextId());
                        ciblevaleurFacadeLocal.create(c);
                    } else {
                        ciblevaleurFacadeLocal.edit(c);
                    }
                }
            }
            RequestContext.getCurrentInstance().execute("PF('EvaluationEditDialog').hide()");
            JsfUtil.addSuccessMessage("Opération réussie");
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Echec de l'opération ; vérifier le formulaire");
        }
    }
    
    public void nextAxe() {
        try {
            if (!axes.isEmpty()) {
                if (axes.size() > 1) {
                    int i = 0;
                    for (Axe a : axes) {
                        if (a.equals(axe)) {
                            if (i <= axes.size()) {
                                
                                if (i + 1 == axes.size()) {
                                    break;
                                }
                                
                                axe = axes.get(i + 1);
                                sousaxes = sousaxeFacadeLocal.findByAxe(axe);
                                
                                if (!sousaxes.isEmpty()) {
                                    sousaxe = sousaxes.get(0);
                                    cibles = cibleFacadeLocal.findByDistrictSousaxe(district.getIddistrict(), sousaxe.getIdsousaxe(), annee.getIdannee());
                                    break;
                                } else {
                                    sousaxe = new Sousaxe();
                                    sousaxes.clear();
                                    cibles.clear();
                                    break;
                                }
                            }
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void precAxe() {
        try {
            if (!axes.isEmpty()) {
                if (axes.size() > 1) {
                    int i = 0;
                    for (Axe a : axes) {
                        if (a.equals(axe)) {
                            if (i == 0) {
                                break;
                            } else {
                                axe = axes.get(i - 1);
                                sousaxes = sousaxeFacadeLocal.findByAxe(axe);
                                if (!sousaxes.isEmpty()) {
                                    sousaxe = sousaxes.get(0);
                                    cibles = cibleFacadeLocal.findByDistrictSousaxe(district.getIddistrict(), sousaxe.getIdsousaxe(), annee.getIdannee());
                                    break;
                                } else {
                                    cibles.clear();
                                    break;
                                }
                            }
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void nextSousaxe() {
        try {
            if (!sousaxes.isEmpty()) {
                if (sousaxes.size() > 1) {
                    int i = 0;
                    for (Sousaxe s : sousaxes) {
                        if (s.equals(sousaxe)) {
                            if (i <= axes.size()) {
                                
                                if (i + 1 == sousaxes.size()) {
                                    return;
                                }
                                sousaxe = sousaxes.get(i + 1);
                                this.updateSousaxe();
                                break;
                            }
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void precSousAxe() {
        try {
            if (!sousaxes.isEmpty()) {
                if (sousaxes.size() > 1) {
                    int i = 0;
                    for (Sousaxe s : sousaxes) {
                        if (s.equals(sousaxe)) {
                            if (i == 0) {
                                break;
                            } else {
                                sousaxe = sousaxes.get(i - 1);
                                this.updateSousaxe();
                                break;
                            }
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void generateData() {
        try {
            List<Cible> cibles = cibleFacadeLocal.find(district.getIddistrict(), annee.getIdannee());
            if (!cibles.isEmpty()) {
                for (Cible c : cibles) {
                    List<Ciblevaleur> list = ciblevaleurFacadeLocal.findByIdcible(c.getIdcible());
                    if (list.isEmpty()) {
                        List<Periodederattachement> listP = periodederattachementFacadeLocal.findAllRange();
                        for (Periodederattachement p : listP) {
                            Ciblevaleur cv = new Ciblevaleur();
                            cv.setIdciblevaleur(ciblevaleurFacadeLocal.nextId());
                            cv.setIdcible(c);
                            cv.setValeurcible((c.getValeur() / periodederattachements.size()));
                            cv.setIdperioderattachement(p);
                            cv.setEtat(false);
                            cv.setEvaluee(false);
                            cv.setCommentaire(" ");
                            cv.setRecommandation(" ");
                            ciblevaleurFacadeLocal.create(cv);
                        }
                    }
                }
            }
            RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            JsfUtil.addSuccessMessage("Opération réussie");
        } catch (Exception e) {
            e.printStackTrace();
            RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            JsfUtil.addErrorMessage("Echec de l'operation");
        }
    }
    
    public boolean isDetail() {
        return detail;
    }
    
    public void setDetail(boolean detail) {
        this.detail = detail;
    }
    
    public List<Annee> getAnnees() {
        annees = anneeFacadeLocal.findByEtatCibles(true);
        return annees;
    }
    
    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }
    
    public Axe getAxe() {
        return axe;
    }
    
    public void setAxe(Axe axe) {
        this.axe = axe;
    }
    
    public List<Axe> getAxes() {
        return axes;
    }
    
    public void setAxes(List<Axe> axes) {
        this.axes = axes;
    }
    
    public Sousaxe getSousaxe() {
        return sousaxe;
    }
    
    public void setSousaxe(Sousaxe sousaxe) {
        this.sousaxe = sousaxe;
    }
    
    public List<Sousaxe> getSousaxes() {
        return sousaxes;
    }
    
    public void setSousaxes(List<Sousaxe> sousaxes) {
        this.sousaxes = sousaxes;
    }
    
    public Cible getCible() {
        return cible;
    }
    
    public void setCible(Cible cible) {
        this.cible = cible;
    }
    
    public List<Cible> getCibles() {
        return cibles;
    }
    
    public void setCibles(List<Cible> cibles) {
        this.cibles = cibles;
    }
    
    public District getDistrict() {
        return district;
    }
    
    public void setDistrict(District district) {
        this.district = district;
    }
    
    public Annee getAnnee() {
        return annee;
    }
    
    public Tachedistrict getTachedistrict() {
        return tachedistrict;
    }
    
    public void setTachedistrict(Tachedistrict tachedistrict) {
        this.tachedistrict = tachedistrict;
    }
    
    public List<Tachedistrict> getTachedistricts() {
        return tachedistricts;
    }
    
    public void setTachedistricts(List<Tachedistrict> tachedistricts) {
        this.tachedistricts = tachedistricts;
    }
    
    public Niveauactivite getNiveauactivite() {
        return niveauactivite;
    }
    
    public void setNiveauactivite(Niveauactivite niveauactivite) {
        this.niveauactivite = niveauactivite;
    }
    
    public List<Niveauactivite> getNiveauactivites() {
        try {
            niveauactivites = niveauactiviteFacadeLocal.findAllRange();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return niveauactivites;
    }
    
    public void setNiveauactivites(List<Niveauactivite> niveauactivites) {
        this.niveauactivites = niveauactivites;
    }
    
    public List<Niveauactivite> getNiveauactivites1() {
        try {
            niveauactivites1 = niveauactiviteFacadeLocal.findAllRangeExclusion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return niveauactivites1;
    }
    
    public void setNiveauactivites1(List<Niveauactivite> niveauactivites1) {
        this.niveauactivites1 = niveauactivites1;
    }
    
    public Ciblevaleur getCiblevaleur() {
        return ciblevaleur;
    }
    
    public void setCiblevaleur(Ciblevaleur ciblevaleur) {
        this.ciblevaleur = ciblevaleur;
    }
    
    public TachedistrictPeriode getTachedistrictPeriode() {
        return tachedistrictPeriode;
    }
    
    public void setTachedistrictPeriode(TachedistrictPeriode tachedistrictPeriode) {
        this.tachedistrictPeriode = tachedistrictPeriode;
    }
    
    public List<TachedistrictPeriode> getTachedistrictPeriodes() {
        return tachedistrictPeriodes;
    }
    
    public void setTachedistrictPeriodes(List<TachedistrictPeriode> tachedistrictPeriodes) {
        this.tachedistrictPeriodes = tachedistrictPeriodes;
    }
    
    public Periodederattachement getPeriodederattachement() {
        try {
            if (SessionMBean.getPeriodeRat() != null) {
                periodederattachement = SessionMBean.getPeriodeRat();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return periodederattachement;
    }
    
    public void setPeriodederattachement(Periodederattachement periodederattachement) {
        this.periodederattachement = periodederattachement;
    }
    
    public List<Periodederattachement> getPeriodederattachements() {
        periodederattachements = periodederattachementFacadeLocal.findAllRange();
        return periodederattachements;
    }
    
    public List<Ciblevaleur> getCiblevaleurs() {
        return ciblevaleurs;
    }
    
}

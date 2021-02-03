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
import entities.CibleRegion;
import entities.CibleRegionValeur;
import entities.District;
import entities.Niveauactivite;
import entities.Periodederattachement;
import entities.Sousaxe;
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
import sessions.CibleRegionFacadeLocal;
import sessions.CibleRegionValeurFacadeLocal;
import sessions.NiveauactiviteFacadeLocal;
import sessions.PeriodederattachementFacadeLocal;
import sessions.SousaxeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class JalonRController implements Serializable {

    @Resource
    private UserTransaction ut;

    @EJB
    private CibleRegionFacadeLocal cibleRegionFacadeLocal;
    private CibleRegion cibleRegion = new CibleRegion();
    private List<CibleRegion> cibleRegions = new ArrayList<>();

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
    private NiveauactiviteFacadeLocal niveauactiviteFacadeLocal;
    private Niveauactivite niveauactivite = new Niveauactivite();
    private List<Niveauactivite> niveauactivites = new ArrayList<>();
    private List<Niveauactivite> niveauactivites1 = new ArrayList<>();

    @EJB
    private PeriodederattachementFacadeLocal periodederattachementFacadeLocal;
    private Periodederattachement periodederattachement = new Periodederattachement();
    List<Periodederattachement> periodederattachements = new ArrayList<>();

    @EJB
    private CibleRegionValeurFacadeLocal cibleRegionValeurFacadeLocal;
    private CibleRegionValeur cibleRegionValeur = new CibleRegionValeur();
    List<CibleRegionValeur> cibleRegionValeurs = new ArrayList<>();

    private District district = SessionMBean.getDistrict();

    private boolean detail = true;

    /**
     * Creates a new instance of SousaxeController
     */
    public JalonRController() {
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
                    cibleRegions = cibleRegionFacadeLocal.findByRegionSousaxe(SessionMBean.getRegion().getIdregion(), sousaxe.getIdsousaxe(), SessionMBean.getAnnee().getIdannee());
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
                    cibleRegions = cibleRegionFacadeLocal.findByRegionSousaxe(SessionMBean.getRegion().getIdpays(), sousaxe.getIdsousaxe(), SessionMBean.getAnnee().getIdannee());
                } else {
                    sousaxe = new Sousaxe();
                    cibleRegions.clear();
                }
            } else {
                sousaxe = new Sousaxe();
                cibleRegions.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSousaxe() {
        try {
            if (!sousaxes.isEmpty()) {
                if (sousaxe != null) {
                    cibleRegions = cibleRegionFacadeLocal.findByRegionSousaxe(SessionMBean.getRegion().getIdregion(), sousaxe.getIdsousaxe(), SessionMBean.getAnnee().getIdannee());
                }
            } else {
                sousaxe = new Sousaxe();
                cibleRegions.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CibleRegionValeur findResult(CibleRegion cible, Periodederattachement p) {
        try {
            CibleRegionValeur crv = cibleRegionValeurFacadeLocal.findByIdcibleIdperiode(cible.getIdcibleRegion(), p.getIdperiodederattachement());
            if (crv != null) {
                return crv;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public CibleRegionValeur findResult(CibleRegion cibleRegion) {
        try {
            if (SessionMBean.getPeriodeRat() != null) {
                CibleRegionValeur crv = cibleRegionValeurFacadeLocal.findByIdcibleIdperiode(cibleRegion.getIdcibleRegion(), SessionMBean.getPeriodeRat().getIdperiodederattachement());
                if (crv != null) {
                    return crv;
                }
                return new CibleRegionValeur();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CibleRegionValeur();
        }
        return new CibleRegionValeur();
    }

    public void prepareEditData(CibleRegion item) {
        try {
            if (item != null) {
                cibleRegion = item;
                cibleRegionValeurs = cibleRegionValeurFacadeLocal.findByIdcible(item.getIdcibleRegion());
                if (cibleRegionValeurs.isEmpty()) {
                    for (Periodederattachement p : periodederattachements) {
                        CibleRegionValeur crv = new CibleRegionValeur();
                        crv.setIdcibleRegionValeur(0L);
                        crv.setIdcibleRegion(cibleRegion);
                        crv.setEtat(false);
                        crv.setEvaluee(false);
                        crv.setIdperioderattachement(p);
                        crv.setValeurcible(item.getValeur() / periodederattachements.size());
                        crv.setRecommandation(" ");
                        crv.setCommentaire(" ");
                        cibleRegionValeurs.add(crv);
                    }
                }
                RequestContext.getCurrentInstance().execute("PF('EvaluationEditDialog').show()");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Exception");
        }
    }

    public void generateData() {
        try {
            List<CibleRegion> cibles = cibleRegionFacadeLocal.findByRegionIdannee(SessionMBean.getRegion().getIdregion(), annee.getIdannee());
            if (!cibles.isEmpty()) {
                for (CibleRegion c : cibles) {
                    List<CibleRegionValeur> list = cibleRegionValeurFacadeLocal.findByIdcible(c.getIdcibleRegion());
                    if (list.isEmpty()) {
                        List<Periodederattachement> listP = periodederattachementFacadeLocal.findAllRange();
                        for (Periodederattachement p : listP) {
                            CibleRegionValeur crv = new CibleRegionValeur();
                            crv.setIdcibleRegionValeur(cibleRegionValeurFacadeLocal.nextId());
                            crv.setIdcibleRegion(c);
                            crv.setValeurcible((c.getValeur() / periodederattachements.size()));
                            crv.setIdperioderattachement(p);
                            crv.setEtat(false);
                            crv.setEvaluee(false);
                            crv.setCommentaire(" ");
                            crv.setRecommandation(" ");
                            cibleRegionValeurFacadeLocal.create(crv);
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

    public void editData() {
        try {
            if (!cibleRegionValeurs.isEmpty()) {
                for (CibleRegionValeur crv : cibleRegionValeurs) {
                    if (crv.getIdcibleRegionValeur() == 0L) {
                        crv.setIdcibleRegionValeur(cibleRegionValeurFacadeLocal.nextId());
                        cibleRegionValeurFacadeLocal.create(crv);
                    } else {
                        cibleRegionValeurFacadeLocal.edit(crv);
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

    public Double sommeData(CibleRegion item) {
        try {
            List<CibleRegionValeur> list = cibleRegionValeurFacadeLocal.findByIdcible(item.getIdcibleRegion());
            if (!list.isEmpty()) {
                Double somme = 0d;
                for (CibleRegionValeur cv : list) {
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
                                    cibleRegions = cibleRegionFacadeLocal.findByRegionSousaxe(SessionMBean.getRegion().getIdregion(), sousaxe.getIdsousaxe(), SessionMBean.getAnnee().getIdannee());
                                    break;
                                } else {
                                    sousaxe = new Sousaxe();
                                    sousaxes.clear();
                                    cibleRegions.clear();
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
                                    cibleRegions = cibleRegionFacadeLocal.findByRegionSousaxe(SessionMBean.getRegion().getIdregion(), sousaxe.getIdsousaxe(), SessionMBean.getAnnee().getIdannee());
                                    break;
                                } else {
                                    cibleRegions.clear();
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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Annee getAnnee() {
        return annee;
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

    public CibleRegion getCibleRegion() {
        return cibleRegion;
    }

    public void setCibleRegion(CibleRegion cibleRegion) {
        this.cibleRegion = cibleRegion;
    }

    public List<CibleRegion> getCibleRegions() {
        return cibleRegions;
    }

    public CibleRegionValeur getCibleRegionValeur() {
        return cibleRegionValeur;
    }

    public void setCibleRegionValeur(CibleRegionValeur cibleRegionValeur) {
        this.cibleRegionValeur = cibleRegionValeur;
    }

    public List<Periodederattachement> getPeriodederattachements() {
        periodederattachements = periodederattachementFacadeLocal.findAllRange();
        return periodederattachements;
    }

    public List<CibleRegionValeur> getCibleRegionValeurs() {
        return cibleRegionValeurs;
    }

}

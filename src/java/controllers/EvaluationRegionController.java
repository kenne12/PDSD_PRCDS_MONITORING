/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.ActiviteRegion;
import entities.Annee;
import entities.Axe;
import entities.CibleRegion;
import entities.CibleRegionValeur;
import entities.District;
import entities.IndicateurRegion;
import entities.Niveauactivite;
import entities.Periodederattachement;
import entities.Sousaxe;
import entities.Tacheregion;
import entities.TacheregionPeriode;
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
import sessions.ActiviteRegionFacadeLocal;
import sessions.AnneeFacadeLocal;
import sessions.AxeFacadeLocal;
import sessions.CibleRegionFacadeLocal;
import sessions.CibleRegionValeurFacadeLocal;
import sessions.NiveauactiviteFacadeLocal;
import sessions.PeriodederattachementFacadeLocal;
import sessions.SousaxeFacadeLocal;
import sessions.TacheregionFacadeLocal;
import sessions.TacheregionPeriodeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class EvaluationRegionController implements Serializable {

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
    private ActiviteRegionFacadeLocal activiteRegionFacadeLocal;
    private ActiviteRegion activiteRegion = new ActiviteRegion();

    @EJB
    private TacheregionFacadeLocal tacheregionFacadeLocal;
    private Tacheregion tacheregion = new Tacheregion();
    private List<Tacheregion> tacheregions = new ArrayList<>();

    @EJB
    private TacheregionPeriodeFacadeLocal tacheregionPeriodeFacadeLocal;
    private TacheregionPeriode tacheregionPeriode = new TacheregionPeriode();
    private List<TacheregionPeriode> tacheregionPeriodes = new ArrayList<>();

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

    private District district = SessionMBean.getDistrict();

    private boolean detail = true;

    /**
     * Creates a new instance of SousaxeController
     */
    public EvaluationRegionController() {
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
                    cibleRegions = cibleRegionFacadeLocal.findByRegionSousaxe(SessionMBean.getRegion().getIdregion(), sousaxe.getIdsousaxe(), SessionMBean.getAnnee().getIdannee());
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
                    cibleRegions = cibleRegionFacadeLocal.findByRegionSousaxe(SessionMBean.getRegion().getIdpays(), sousaxe.getIdsousaxe(), SessionMBean.getAnnee().getIdannee());
                }
            } else {
                sousaxe = new Sousaxe();
                cibleRegions.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CibleRegion findResult(IndicateurRegion indicateurRegion, Annee annee) {
        CibleRegion cibleRegion = new CibleRegion();
        try {
            List<CibleRegion> results = cibleRegionFacadeLocal.findByRegionSousaxe(indicateurRegion.getIdindicateur().getIdindicateur(), SessionMBean.getRegion().getIdregion(), SessionMBean.getAnnee().getIdannee());
            if (!results.isEmpty()) {
                cibleRegion = results.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cibleRegion;
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

    public void viewDetail(CibleRegion item) {
        try {
            if (item != null) {
                cibleRegion = item;
                cibleRegionValeur = new CibleRegionValeur();
                if (SessionMBean.getPeriodeRat() != null) {
                    CibleRegionValeur crv = cibleRegionValeurFacadeLocal.findByIdcibleIdperiode(cibleRegion.getIdcibleRegion(), SessionMBean.getPeriodeRat().getIdperiodederattachement());

                    if (crv != null) {
                        cibleRegionValeur = crv;
                        tacheregionPeriodes = tacheregionPeriodeFacadeLocal.findByIdindicateur(cibleRegion.getIdindicateur().getIdindicateur(), SessionMBean.getRegion().getIdregion(), SessionMBean.getAnnee().getIdannee(), SessionMBean.getPeriodeRat().getIdperiodederattachement());
                        RequestContext.getCurrentInstance().execute("PF('EvaluationEditDialog').show()");
                        return;
                    } else {
                        JsfUtil.addWarningMessage("Valeur de l'indicateur non saisie");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            cibleRegionValeur.setEcart(cibleRegionValeur.getValeurcible() - cibleRegionValeur.getValeurrealisee());
            cibleRegionValeur.setEvaluee(true);

            if (cibleRegion.getIdindicateur().getInverse()) {
                if (cibleRegionValeur.getValeurrealisee() <= cibleRegion.getValeur()) {
                    cibleRegionValeur.setEtat(true);
                } else {
                    cibleRegionValeur.setEtat(false);
                }
            } else {
                if (cibleRegionValeur.getValeurrealisee() >= cibleRegion.getValeur()) {
                    cibleRegionValeur.setEtat(true);
                } else {
                    cibleRegionValeur.setEtat(false);
                }
            }

            ut.begin();

            if (cibleRegionValeur.getIdcibleRegionValeur() == 0L) {
                cibleRegionValeur.setIdcibleRegionValeur(cibleRegionValeurFacadeLocal.nextId());
                cibleRegionValeurFacadeLocal.create(cibleRegionValeur);
            } else {
                cibleRegionValeurFacadeLocal.edit(cibleRegionValeur);
            }

            if (!tacheregionPeriodes.isEmpty()) {
                for (TacheregionPeriode t : tacheregionPeriodes) {
                    tacheregionPeriodeFacadeLocal.edit(t);
                }
            }

            ut.commit();
            RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').hide()");
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

    public String evaluate(Niveauactivite item) {
        try {
            if (!this.tacheregionPeriodes.isEmpty()) {
                Integer i = search(item, tacheregionPeriodes);
                Double percent = (i.doubleValue() / tacheregionPeriodes.size()) * 100D;
                return "" + percent + " %";
            }
            return " %";
        } catch (Exception e) {
            return " %";
        }
    }

    public int search(Niveauactivite n, List<TacheregionPeriode> tacheregionPeriodes) {
        int i = 0;
        if (!tacheregionPeriodes.isEmpty()) {
            for (TacheregionPeriode t : tacheregionPeriodes) {
                if (t.getIdniveauactivite().equals(n)) {
                    i = i + 1;
                }
            }
        }
        return i;
    }

    public void uptadeTable() {
        try {
            tacheregionPeriodes.clear();
            if (cibleRegion != null) {
                CibleRegionValeur crv = cibleRegionValeurFacadeLocal.findByIdcibleIdperiode(cibleRegion.getIdcibleRegion(), SessionMBean.getPeriodeRat().getIdperiodederattachement());
                if (crv == null) {
                    cibleRegionValeur = new CibleRegionValeur();
                    cibleRegionValeur.setIdcibleRegionValeur(0L);
                    cibleRegionValeur.setIdcibleRegion(cibleRegion);
                    cibleRegionValeur.setEtat(false);
                    cibleRegionValeur.setCommentaire("-");
                    cibleRegionValeur.setRecommandation("-");
                    cibleRegionValeur.setValeurcible(cibleRegion.getValeur());
                    cibleRegionValeur.setIdperioderattachement(SessionMBean.getPeriodeRat());
                } else {
                    cibleRegionValeur = crv;
                }
                tacheregionPeriodes = tacheregionPeriodeFacadeLocal.findByIdindicateur(cibleRegion.getIdindicateur().getIdindicateur(), SessionMBean.getRegion().getIdregion(), SessionMBean.getAnnee().getIdannee(), SessionMBean.getPeriodeRat().getIdperiodederattachement());
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

    public void setCibleRegions(List<CibleRegion> cibleRegions) {
        this.cibleRegions = cibleRegions;
    }

    public List<TacheregionPeriode> getTacheregionPeriodes() {
        return tacheregionPeriodes;
    }

    public void setTacheregionPeriodes(List<TacheregionPeriode> tacheregionPeriodes) {
        this.tacheregionPeriodes = tacheregionPeriodes;
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

}

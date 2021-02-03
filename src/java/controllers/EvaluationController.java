/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Activite;
import entities.Annee;
import entities.Axe;
import entities.Cible;
import entities.Ciblevaleur;
import entities.District;
import entities.IndicateurDistrict;
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
import sessions.ActiviteFacadeLocal;
import sessions.AnneeFacadeLocal;
import sessions.AxeFacadeLocal;
import sessions.CibleFacadeLocal;
import sessions.CiblevaleurFacadeLocal;
import sessions.NiveauactiviteFacadeLocal;
import sessions.SousaxeFacadeLocal;
import sessions.TachedistrictFacadeLocal;
import sessions.TachedistrictPeriodeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class EvaluationController implements Serializable {

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
    private ActiviteFacadeLocal activiteFacadeLocal;
    private Activite activite = new Activite();

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

    private Periodederattachement periodederattachement = new Periodederattachement();

    @EJB
    private CiblevaleurFacadeLocal ciblevaleurFacadeLocal;
    private Ciblevaleur ciblevaleur = new Ciblevaleur();

    private District district = SessionMBean.getDistrict();

    private boolean detail = true;

    /**
     * Creates a new instance of SousaxeController
     */
    public EvaluationController() {
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

    public Cible findResult(IndicateurDistrict indicateurDistrict, Annee annee) {
        Cible cible = new Cible();
        try {
            List<Cible> results = cibleFacadeLocal.find(indicateurDistrict, annee);
            if (!results.isEmpty()) {
                cible = results.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cible;
    }

    public Ciblevaleur findResult(Cible cible) {
        Ciblevaleur ciblevaleur = new Ciblevaleur();
        try {
            if (SessionMBean.getPeriodeRat() != null) {
                Ciblevaleur cv = ciblevaleurFacadeLocal.findByIdcibleIdperiode(cible.getIdcible(), SessionMBean.getPeriodeRat().getIdperiodederattachement());
                if (cv != null) {
                    return cv;
                    
                }
                return new Ciblevaleur();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ciblevaleur;
    }

    public void viewDetail(Cible item) {
        try {
            if (item != null) {
                cible = item;
                ciblevaleur = new Ciblevaleur();
                if (SessionMBean.getPeriodeRat() != null) {
                    Ciblevaleur cv = ciblevaleurFacadeLocal.findByIdcibleIdperiode(cible.getIdcible(), SessionMBean.getPeriodeRat().getIdperiodederattachement());
                    if (cv != null) {
                        ciblevaleur = cv;
                        tachedistrictPeriodes = tachedistrictPeriodeFacadeLocal.findByIdindicateur(cible.getIdindicateur().getIdindicateur(), district.getIddistrict(), annee.getIdannee(), SessionMBean.getPeriodeRat().getIdperiodederattachement());
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

            ciblevaleur.setEcart(ciblevaleur.getValeurcible() - ciblevaleur.getValeurrealisee());
            ciblevaleur.setEvaluee(true);

            if (cible.getIdindicateur().getInverse()) {
                if (ciblevaleur.getValeurrealisee() <= cible.getValeur()) {
                    ciblevaleur.setEtat(true);
                } else {
                    ciblevaleur.setEtat(false);
                }
            } else {
                if (ciblevaleur.getValeurrealisee() >= cible.getValeur()) {
                    ciblevaleur.setEtat(true);
                } else {
                    ciblevaleur.setEtat(false);
                }
            }

            ut.begin();

            if (ciblevaleur.getIdciblevaleur() == 0L) {
                ciblevaleur.setIdciblevaleur(ciblevaleurFacadeLocal.nextId());
                ciblevaleurFacadeLocal.create(ciblevaleur);
            } else {
                ciblevaleurFacadeLocal.edit(ciblevaleur);
            }

            if (!tachedistrictPeriodes.isEmpty()) {
                for (TachedistrictPeriode t : tachedistrictPeriodes) {
                    tachedistrictPeriodeFacadeLocal.edit(t);
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

    public String evaluate(Niveauactivite item) {
        try {
            if (!this.tachedistrictPeriodes.isEmpty()) {
                Integer i = search(item, tachedistrictPeriodes);
                Double percent = (i.doubleValue() / tachedistrictPeriodes.size()) * 100D;
                return "" + percent + " %";
            }
            return " %";
        } catch (Exception e) {
            return " %";
        }
    }

    public int search(Niveauactivite n, List<TachedistrictPeriode> tachedistrictPeriodes) {
        int i = 0;
        if (!tachedistrictPeriodes.isEmpty()) {
            for (TachedistrictPeriode t : tachedistrictPeriodes) {
                if (t.getIdniveauactivite().equals(n)) {
                    i = i + 1;
                }
            }
        }
        return i;
    }

    public void uptadeTable() {
        try {
            tachedistrictPeriodes.clear();
            if (cible != null) {

                Ciblevaleur cv = ciblevaleurFacadeLocal.findByIdcibleIdperiode(cible.getIdcible(), SessionMBean.getPeriodeRat().getIdperiodederattachement());
                if (cv == null) {
                    ciblevaleur = new Ciblevaleur();
                    ciblevaleur.setIdciblevaleur(0L);
                    ciblevaleur.setIdcible(cible);
                    ciblevaleur.setEtat(false);
                    ciblevaleur.setCommentaire("-");
                    ciblevaleur.setRecommandation("-");
                    ciblevaleur.setValeurcible(cible.getValeur());
                    ciblevaleur.setIdperioderattachement(SessionMBean.getPeriodeRat());
                } else {
                    ciblevaleur = cv;
                }
                tachedistrictPeriodes = tachedistrictPeriodeFacadeLocal.findByIdindicateur(cible.getIdindicateur().getIdindicateur(), district.getIddistrict(), annee.getIdannee(), SessionMBean.getPeriodeRat().getIdperiodederattachement());
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

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
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

}

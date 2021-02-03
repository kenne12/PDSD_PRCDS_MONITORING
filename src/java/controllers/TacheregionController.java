/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.ActiviteRegion;
import entities.ActiviteStructureRegion;
import entities.Annee;
import entities.Axe;
import entities.ChronogrammeRegion;
import entities.Niveauactivite;
import entities.Periodederattachement;
import entities.Region;
import entities.Sousaxe;
import entities.Structure;
import entities.Tacheregion;
import entities.TacheregionPeriode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import sessions.ActiviteRegionFacadeLocal;
import sessions.ActiviteStructureRegionFacadeLocal;
import sessions.AnneeFacadeLocal;
import sessions.AxeFacadeLocal;
import sessions.ChronogrammeRegionFacadeLocal;
import sessions.IndicateurRegionFacadeLocal;
import sessions.PeriodederattachementFacadeLocal;
import sessions.SousaxeFacadeLocal;
import sessions.StructureFacadeLocal;

import sessions.TacheregionFacadeLocal;
import sessions.TacheregionPeriodeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class TacheregionController implements Serializable {

    @EJB
    private ActiviteRegionFacadeLocal activiteRegionFacadeLocal;
    private ActiviteRegion activiteRegion = new ActiviteRegion();
    private List<ActiviteRegion> activiteRegions = new ArrayList<>();

    @EJB
    private ActiviteStructureRegionFacadeLocal activiteStructureRegionFacadeLocal;
    private ActiviteStructureRegion activiteStructureRegion = new ActiviteStructureRegion();
    private List<ActiviteStructureRegion> activiteStructureRegions = new ArrayList<>();

    @EJB
    private TacheregionFacadeLocal tacheregionFacadeLocal;
    private Tacheregion tacheregion = new Tacheregion();
    private List<Tacheregion> tacheregions = new ArrayList<>();

    @EJB
    private StructureFacadeLocal structureFacadeLocal;
    private Structure structure = SessionMBean.getStructure();

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

    @EJB
    private ChronogrammeRegionFacadeLocal chronogrammeRegionFacadeLocal;

    @EJB
    private IndicateurRegionFacadeLocal indicateurRegionFacadeLocal;

    @EJB
    private PeriodederattachementFacadeLocal periodederattachementFacadeLocal;
    private List<Periodederattachement> periodederattachements = new ArrayList<>();
    private List<Periodederattachement> selectedP = new ArrayList<>();

    @EJB
    private TacheregionPeriodeFacadeLocal tacheregionPeriodeFacadeLocal;

    Double montant_programme = 0d;

    private Region region = SessionMBean.getRegion();

    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of SousaxeController
     */
    public TacheregionController() {
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
                    activiteRegions.clear();
                    activiteStructureRegions.clear();

                    List<ActiviteRegion> activiteRegions = activiteRegionFacadeLocal.findByIdregionIdsousaxe(SessionMBean.getRegion().getIdregion(), sousaxe.getIdsousaxe());

                    for (ActiviteRegion a : activiteRegions) {
                        ChronogrammeRegion chronogramme = chronogrammeRegionFacadeLocal.findByIdactiviteIdannee(a.getIdactiviteRegion(), SessionMBean.getAnnee().getIdannee());
                        if (chronogramme != null) {
                            this.activiteRegions.add(a);
                        }
                    }

                    if (!this.activiteRegions.isEmpty()) {
                        for (ActiviteRegion a : this.activiteRegions) {
                            ActiviteStructureRegion activiteStructureRegion = activiteStructureRegionFacadeLocal.findByIdactiviteIdstructure(a.getIdactiviteRegion(), structure.getIdstructure());
                            if (activiteStructureRegion != null) {
                                this.activiteStructureRegions.add(activiteStructureRegion);
                            }
                        }
                    }
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

    public void updateSelect() {

    }

    public void updateAll() {
        try {
            if (axe != null) {
                sousaxes = sousaxeFacadeLocal.findByAxe(axe);

                if (!sousaxes.isEmpty()) {
                    sousaxe = sousaxes.get(0);
                    activiteRegions = activiteRegionFacadeLocal.findByIdregionIdsousaxe(SessionMBean.getRegion().getIdregion(), sousaxe.getIdsousaxe());
                } else {
                    sousaxe = new Sousaxe();
                    activiteRegions.clear();
                }
            } else {
                sousaxe = new Sousaxe();
                activiteRegions.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSousaxe() {
        try {
            if (!sousaxes.isEmpty()) {

                if (sousaxe != null) {

                    this.activiteRegions.clear();
                    this.activiteStructureRegions.clear();
                    List<ActiviteRegion> activiteRegions = activiteRegionFacadeLocal.findByIdregionIdsousaxe(SessionMBean.getRegion().getIdregion(), sousaxe.getIdsousaxe());

                    for (ActiviteRegion a : activiteRegions) {
                        ChronogrammeRegion chronogrammeRegion = chronogrammeRegionFacadeLocal.findByIdactiviteIdannee(a.getIdactiviteRegion(), SessionMBean.getAnnee().getIdannee());
                        if (chronogrammeRegion != null) {
                            this.activiteRegions.add(a);
                        }
                    }

                    if (!this.activiteRegions.isEmpty()) {
                        for (ActiviteRegion a : this.activiteRegions) {
                            ActiviteStructureRegion activiteStructureRegion = activiteStructureRegionFacadeLocal.findByIdactiviteIdstructure(a.getIdactiviteRegion(), structure.getIdstructure());
                            if (activiteStructureRegion == null) {
                                this.activiteRegions.remove(a);
                            } else {
                                this.activiteStructureRegions.add(activiteStructureRegion);
                            }
                        }
                    }

                }
            } else {
                sousaxe = new Sousaxe();
                activiteRegions.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            if ("Create".equals(mode)) {
                if (tacheregion.getIdtacheregion() == 0L) {
                    tacheregion.setIdtacheregion(tacheregionFacadeLocal.nextId());
                    tacheregion.setIdniveauactivite(new Niveauactivite(1));
                    tacheregionFacadeLocal.create(tacheregion);

                    this.activiteStructureRegion = tacheregion.getIdactivitestructure();
                    this.tacheregions = tacheregionFacadeLocal.findByIdactivite(activiteStructureRegion.getIdactiviteStructureRegion(), annee.getIdannee());

                    for (Periodederattachement p : selectedP) {
                        TacheregionPeriode trp = new TacheregionPeriode();
                        trp.setIdtacheregionPeriode(tacheregionPeriodeFacadeLocal.nextId());
                        trp.setIdniveauactivite(new Niveauactivite(1));
                        trp.setIdperioderattachement(p);
                        trp.setIdtacheregion(tacheregion);
                        trp.setObservation("-");
                        tacheregionPeriodeFacadeLocal.create(trp);
                    }

                    JsfUtil.addSuccessMessage("Operation réussie");
                    RequestContext.getCurrentInstance().execute("PF('AddTacheCreateDialog').hide()");
                }
            } else {
                if (tacheregion.getIdtacheregion() != 0L) {
                    tacheregionFacadeLocal.edit(tacheregion);

                    activiteStructureRegion = SessionMBean.getActiviteRegionStructure();
                    this.tacheregions = tacheregionFacadeLocal.findByIdactivite(activiteStructureRegion.getIdactiviteStructureRegion(), annee.getIdannee());

                    List<TacheregionPeriode> trps = tacheregionPeriodeFacadeLocal.findByIdtache(this.tacheregion.getIdtacheregion());
                    List<Periodederattachement> selectedP1 = new ArrayList<>();
                    if (!trps.isEmpty()) {
                        for (TacheregionPeriode t : trps) {
                            selectedP1.add(t.getIdperioderattachement());
                        }
                    }

                    for (Periodederattachement p : selectedP) {
                        if (!selectedP1.contains(p)) {
                            TacheregionPeriode trp = new TacheregionPeriode();
                            trp.setIdtacheregionPeriode(tacheregionPeriodeFacadeLocal.nextId());
                            trp.setIdniveauactivite(new Niveauactivite(1));
                            trp.setIdperioderattachement(p);
                            trp.setIdtacheregion(tacheregion);
                            trp.setObservation("-");
                            tacheregionPeriodeFacadeLocal.create(trp);
                        } else {
                            if (!trps.isEmpty()) {

                                for (Periodederattachement p1 : selectedP1) {
                                    if (!selectedP.contains(p1)) {

                                        for (TacheregionPeriode t : trps) {
                                            if (t.getIdperioderattachement().equals(p1)) {
                                                tacheregionPeriodeFacadeLocal.remove(t);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    JsfUtil.addSuccessMessage("Operation réussie");
                    RequestContext.getCurrentInstance().execute("PF('AddTacheCreateDialog').hide()");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Echec de l'opération");
        }
    }

    public int countTache(ActiviteStructureRegion item) {
        try {
            return tacheregionFacadeLocal.findByIdactivite(item.getIdactiviteStructureRegion(), annee.getIdannee()).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void openTacheDialog(ActiviteStructureRegion item) {
        try {
            this.activiteStructureRegion = item;
            this.tacheregions = tacheregionFacadeLocal.findByIdactivite(item.getIdactiviteStructureRegion(), annee.getIdannee());

            HttpSession session = SessionMBean.getSession();
            montant_programme = 0d;
            if (!tacheregions.isEmpty()) {
                for (Tacheregion t : tacheregions) {
                    montant_programme += t.getCout();
                }
            }

            session.setAttribute("activite_sr", item);

            RequestContext.getCurrentInstance().execute("PF('TacheCreateDialog').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareAddTache() {
        try {
            mode = "Create";

            tacheregion = new Tacheregion();
            tacheregion.setIdtacheregion(0L);
            tacheregion.setIdannee(annee);
            tacheregion.setIdactivitestructure(SessionMBean.getActiviteRegionStructure());
            tacheregion.setCout(0D);
            tacheregion.setResponsable("-");
            tacheregion.setObservation("-");

            tacheregion.setM1(false);
            tacheregion.setM2(false);
            tacheregion.setM3(false);
            tacheregion.setM4(false);
            tacheregion.setM5(false);
            tacheregion.setM6(false);
            tacheregion.setM7(false);
            tacheregion.setM8(false);
            tacheregion.setM9(false);
            tacheregion.setM10(false);
            tacheregion.setM11(false);
            tacheregion.setM12(false);

            periodederattachements = periodederattachementFacadeLocal.findAllRange();
            selectedP = periodederattachements;

            RequestContext.getCurrentInstance().execute("PF('AddTacheCreateDialog').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEditTache(Tacheregion item) {
        try {
            mode = "Edit";
            this.tacheregion = item;

            this.periodederattachements = periodederattachementFacadeLocal.findAllRange();
            this.selectedP.clear();
            List<TacheregionPeriode> trps = tacheregionPeriodeFacadeLocal.findByIdtache(this.tacheregion.getIdtacheregion());
            if (!trps.isEmpty()) {
                for (TacheregionPeriode t : trps) {
                    selectedP.add(t.getIdperioderattachement());
                }
            }

            RequestContext.getCurrentInstance().execute("PF('AddTacheCreateDialog').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTache(Tacheregion item) {
        try {
            mode = "Edit";
            this.tacheregionFacadeLocal.remove(item);

            this.tacheregions = tacheregionFacadeLocal.findByIdactivite(item.getIdactivitestructure().getIdactiviteStructureRegion(), annee.getIdannee());
            JsfUtil.addSuccessMessage("Opération réussie");
        } catch (Exception e) {
            e.printStackTrace();
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

                                    this.activiteRegions.clear();
                                    this.activiteStructureRegions.clear();
                                    List<ActiviteRegion> activiteRegions = activiteRegionFacadeLocal.findByIdregionIdsousaxe(SessionMBean.getRegion().getIdregion(), sousaxe.getIdsousaxe());

                                    for (ActiviteRegion act : activiteRegions) {
                                        ChronogrammeRegion chronogramme = chronogrammeRegionFacadeLocal.findByIdactiviteIdannee(act.getIdactiviteRegion(), SessionMBean.getAnnee().getIdannee());
                                        if (chronogramme != null) {
                                            this.activiteRegions.add(act);
                                        }
                                    }

                                    if (!this.activiteRegions.isEmpty()) {
                                        for (ActiviteRegion act : this.activiteRegions) {
                                            ActiviteStructureRegion activiteStructureRegion = activiteStructureRegionFacadeLocal.findByIdactiviteIdstructure(act.getIdactiviteRegion(), structure.getIdstructure());
                                            if (activiteStructureRegion != null) {
                                                this.activiteStructureRegions.add(activiteStructureRegion);
                                            }
                                        }
                                    }

                                    break;
                                } else {
                                    sousaxe = new Sousaxe();
                                    sousaxes.clear();
                                    activiteRegions.clear();
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

                                    this.activiteRegions.clear();
                                    this.activiteStructureRegions.clear();
                                    List<ActiviteRegion> activiteRegions = activiteRegionFacadeLocal.findByIdregionIdsousaxe(SessionMBean.getRegion().getIdregion(), sousaxe.getIdsousaxe());

                                    for (ActiviteRegion act : activiteRegions) {
                                        ChronogrammeRegion chronogramme = chronogrammeRegionFacadeLocal.findByIdactiviteIdannee(act.getIdactiviteRegion(), SessionMBean.getAnnee().getIdannee());
                                        if (chronogramme != null) {
                                            this.activiteRegions.add(act);
                                        }
                                    }

                                    if (!this.activiteRegions.isEmpty()) {
                                        for (ActiviteRegion act : this.activiteRegions) {
                                            ActiviteStructureRegion activiteStructureRegion = activiteStructureRegionFacadeLocal.findByIdactiviteIdstructure(act.getIdactiviteRegion(), structure.getIdstructure());
                                            if (activiteStructureRegion != null) {
                                                this.activiteStructureRegions.add(activiteStructureRegion);
                                            }
                                        }
                                    }
                                    break;
                                } else {
                                    activiteRegions.clear();
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

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
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

    public List<ActiviteRegion> getActiviteRegions() {
        return activiteRegions;
    }

    public void setActiviteRegions(List<ActiviteRegion> activiteRegions) {
        this.activiteRegions = activiteRegions;
    }

    public List<Periodederattachement> getPeriodederattachements() {
        return periodederattachements;
    }

    public void setPeriodederattachements(List<Periodederattachement> periodederattachements) {
        this.periodederattachements = periodederattachements;
    }

    public List<Periodederattachement> getSelectedP() {
        return selectedP;
    }

    public void setSelectedP(List<Periodederattachement> selectedP) {
        this.selectedP = selectedP;
    }

    public ActiviteStructureRegion getActiviteStructureRegion() {
        return activiteStructureRegion;
    }

    public void setActiviteStructureRegion(ActiviteStructureRegion activiteStructureRegion) {
        this.activiteStructureRegion = activiteStructureRegion;
    }

    public List<ActiviteStructureRegion> getActiviteStructureRegions() {
        return activiteStructureRegions;
    }

    public void setActiviteStructureRegions(List<ActiviteStructureRegion> activiteStructureRegions) {
        this.activiteStructureRegions = activiteStructureRegions;
    }

    public Tacheregion getTacheregion() {
        return tacheregion;
    }

    public void setTacheregion(Tacheregion tacheregion) {
        this.tacheregion = tacheregion;
    }

    public List<Tacheregion> getTacheregions() {
        return tacheregions;
    }

    public void setTacheregions(List<Tacheregion> tacheregions) {
        this.tacheregions = tacheregions;
    }

    public Double getMontant_programme() {
        return montant_programme;
    }

}

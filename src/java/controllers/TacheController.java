/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Activite;
import entities.ActiviteStructure;
import entities.Annee;
import entities.Axe;
import entities.Chronogramme;
import entities.District;
import entities.Indicateur;
import entities.Niveauactivite;
import entities.Periodederattachement;
import entities.Sourcefinancement;
import entities.Sousaxe;
import entities.Structure;
import entities.Tachedistrict;
import entities.TachedistrictPeriode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import sessions.ActiviteFacadeLocal;
import sessions.ActiviteStructureFacadeLocal;
import sessions.AnneeFacadeLocal;
import sessions.AxeFacadeLocal;
import sessions.ChronogrammeFacadeLocal;
import sessions.IndicateurDistrictFacadeLocal;
import sessions.IndicateurFacadeLocal;
import sessions.PeriodederattachementFacadeLocal;
import sessions.SourcefinancementFacadeLocal;
import sessions.SousaxeFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.TachedistrictFacadeLocal;
import sessions.TachedistrictPeriodeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class TacheController implements Serializable {

    @EJB
    private ActiviteFacadeLocal activiteFacadeLocal;
    private Activite activite = new Activite();
    private List<Activite> activites = new ArrayList<>();

    @EJB
    private IndicateurFacadeLocal indicateurFacadeLocal;
    private Indicateur indicateur = new Indicateur();
    private List<Indicateur> indicateurs = new ArrayList<>();

    @EJB
    private ActiviteStructureFacadeLocal activiteStructureFacadeLocal;
    private ActiviteStructure activiteStructure = new ActiviteStructure();
    private List<ActiviteStructure> activiteStructures = new ArrayList<>();

    @EJB
    private TachedistrictFacadeLocal tachedistrictFacadeLocal;
    private Tachedistrict tachedistrict = new Tachedistrict();
    private List<Tachedistrict> tachedistricts = new ArrayList<>();

    @EJB
    private StructureFacadeLocal structureFacadeLocal;
    private Structure structure = SessionMBean.getStructure();

    @EJB
    private SourcefinancementFacadeLocal sourcefinancementFacadeLocal;
    private Sourcefinancement sourcefinancement = new Sourcefinancement();
    private List<Sourcefinancement> sourcefinancements = new ArrayList<>();

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
    private ChronogrammeFacadeLocal chronogrammeFacadeLocal;

    private Double total = 0d;
    private Double montant_programme = 0d;

    @EJB
    private IndicateurDistrictFacadeLocal indicateurDistrictFacadeLocal;

    @EJB
    private PeriodederattachementFacadeLocal periodederattachementFacadeLocal;
    private List<Periodederattachement> periodederattachements = new ArrayList<>();
    private List<Periodederattachement> selectedP = new ArrayList<>();

    @EJB
    private TachedistrictPeriodeFacadeLocal tachedistrictPeriodeFacadeLocal;

    private District district = SessionMBean.getDistrict();

    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of SousaxeController
     */
    public TacheController() {
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
                    activites.clear();
                    activiteStructures.clear();
                    List<Activite> activites = activiteFacadeLocal.find(SessionMBean.getDistrict(), sousaxe);

                    for (Activite a : activites) {
                        List<Chronogramme> chronogrammes = chronogrammeFacadeLocal.findByActivite(a, SessionMBean.getAnnee());
                        if (!chronogrammes.isEmpty()) {
                            this.activites.add(a);
                        }
                    }

                    if (!this.activites.isEmpty()) {
                        for (Activite a : this.activites) {
                            List<ActiviteStructure> activiteStructures = activiteStructureFacadeLocal.find(structure, a);
                            if (!activiteStructures.isEmpty()) {
                                this.activiteStructures.add(activiteStructures.get(0));
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
                    activites = activiteFacadeLocal.find(SessionMBean.getDistrict(), sousaxe);
                } else {
                    sousaxe = new Sousaxe();
                    activites.clear();
                }
            } else {
                sousaxe = new Sousaxe();
                activites.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSousaxe() {
        try {
            if (!sousaxes.isEmpty()) {

                if (sousaxe != null) {

                    this.activites.clear();
                    this.activiteStructures.clear();
                    List<Activite> activites = activiteFacadeLocal.find(SessionMBean.getDistrict(), sousaxe);

                    for (Activite a : activites) {
                        List<Chronogramme> chronogrammes = chronogrammeFacadeLocal.findByActivite(a, SessionMBean.getAnnee());
                        if (!chronogrammes.isEmpty()) {
                            this.activites.add(a);
                        }
                    }

                    if (!this.activites.isEmpty()) {
                        for (Activite a : this.activites) {
                            List<ActiviteStructure> activiteStructures = activiteStructureFacadeLocal.find(structure, a);
                            if (activiteStructures.isEmpty()) {
                                this.activites.remove(a);
                            } else {
                                this.activiteStructures.add(activiteStructures.get(0));
                            }
                        }
                    }

                }
            } else {
                sousaxe = new Sousaxe();
                activites.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            if ("Create".equals(mode)) {
                if (tachedistrict.getIdtachedistrict() == 0L) {
                    tachedistrict.setIdtachedistrict(tachedistrictFacadeLocal.nextId());
                    tachedistrict.setIdniveauactivite(new Niveauactivite(1));
                    tachedistrictFacadeLocal.create(tachedistrict);

                    this.activiteStructure = tachedistrict.getIdactivitestructure();
                    this.tachedistricts = tachedistrictFacadeLocal.findByIdactivite(activiteStructure.getIdactiviteStructure(), annee.getIdannee());

                    for (Periodederattachement p : selectedP) {
                        TachedistrictPeriode tdp = new TachedistrictPeriode();
                        tdp.setIdtachedistrictPeriode(tachedistrictPeriodeFacadeLocal.nextId());
                        tdp.setIdniveauactivite(new Niveauactivite(1));
                        tdp.setIdperioderattachement(p);
                        tdp.setIdtachedistrict(tachedistrict);
                        tdp.setObservation("-");
                        tachedistrictPeriodeFacadeLocal.create(tdp);
                    }

                    JsfUtil.addSuccessMessage("Operation réussie");
                    RequestContext.getCurrentInstance().execute("PF('AddTacheCreateDialog').hide()");
                }
            } else {
                if (tachedistrict.getIdtachedistrict() != 0L) {
                    tachedistrictFacadeLocal.edit(tachedistrict);

                    activiteStructure = SessionMBean.getActiviteStructure();
                    this.tachedistricts = tachedistrictFacadeLocal.findByIdactivite(activiteStructure.getIdactiviteStructure(), annee.getIdannee());

                    List<TachedistrictPeriode> tdps = tachedistrictPeriodeFacadeLocal.findByIdtache(this.tachedistrict.getIdtachedistrict());
                    List<Periodederattachement> selectedP1 = new ArrayList<>();
                    if (!tdps.isEmpty()) {
                        for (TachedistrictPeriode t : tdps) {
                            selectedP1.add(t.getIdperioderattachement());
                        }
                    }

                    for (Periodederattachement p : selectedP) {
                        if (!selectedP1.contains(p)) {
                            TachedistrictPeriode tdp = new TachedistrictPeriode();
                            tdp.setIdtachedistrictPeriode(tachedistrictPeriodeFacadeLocal.nextId());
                            tdp.setIdniveauactivite(new Niveauactivite(1));
                            tdp.setIdperioderattachement(p);
                            tdp.setIdtachedistrict(tachedistrict);
                            tdp.setObservation("-");
                            tachedistrictPeriodeFacadeLocal.create(tdp);
                        } else {
                            if (!tdps.isEmpty()) {

                                for (Periodederattachement p1 : selectedP1) {
                                    if (!selectedP.contains(p1)) {

                                        for (TachedistrictPeriode t : tdps) {
                                            if (t.getIdperioderattachement().equals(p1)) {
                                                tachedistrictPeriodeFacadeLocal.remove(t);
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

    public int countTache(ActiviteStructure item) {
        try {
            return tachedistrictFacadeLocal.findByIdactivite(item.getIdactiviteStructure(), annee.getIdannee()).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void openTacheDialog(ActiviteStructure item) {
        try {
            this.activiteStructure = item;
            this.tachedistricts = tachedistrictFacadeLocal.findByIdactivite(item.getIdactiviteStructure(), annee.getIdannee());

            HttpSession session = SessionMBean.getSession();

            session.setAttribute("activite_s", item);
            montant_programme = 0d;

            if (!tachedistricts.isEmpty()) {

                for (Tachedistrict t : tachedistricts) {
                    montant_programme += t.getCout();
                }
            }

            RequestContext.getCurrentInstance().execute("PF('TacheCreateDialog').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareAddTache() {
        try {
            mode = "Create";

            tachedistrict = new Tachedistrict();
            tachedistrict.setIdtachedistrict(0L);
            tachedistrict.setIdannee(annee);
            tachedistrict.setIdactivitestructure(SessionMBean.getActiviteStructure());
            tachedistrict.setCout(0D);
            tachedistrict.setResponsable("-");
            tachedistrict.setObservation("-");

            tachedistrict.setM1(false);
            tachedistrict.setM2(false);
            tachedistrict.setM3(false);
            tachedistrict.setM4(false);
            tachedistrict.setM5(false);
            tachedistrict.setM6(false);
            tachedistrict.setM7(false);
            tachedistrict.setM8(false);
            tachedistrict.setM9(false);
            tachedistrict.setM10(false);
            tachedistrict.setM11(false);
            tachedistrict.setM12(false);

            periodederattachements = periodederattachementFacadeLocal.findAllRange();
            selectedP = periodederattachements;

            RequestContext.getCurrentInstance().execute("PF('AddTacheCreateDialog').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEditTache(Tachedistrict item) {
        try {
            mode = "Edit";
            this.tachedistrict = item;

            this.periodederattachements = periodederattachementFacadeLocal.findAllRange();
            this.selectedP.clear();
            List<TachedistrictPeriode> tdps = tachedistrictPeriodeFacadeLocal.findByIdtache(this.tachedistrict.getIdtachedistrict());
            if (!tdps.isEmpty()) {
                for (TachedistrictPeriode t : tdps) {
                    selectedP.add(t.getIdperioderattachement());
                }
            }

            RequestContext.getCurrentInstance().execute("PF('AddTacheCreateDialog').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTache(Tachedistrict item) {
        try {
            mode = "Edit";
            this.tachedistrictFacadeLocal.remove(item);

            this.tachedistricts = tachedistrictFacadeLocal.findByIdactivite(item.getIdactivitestructure().getIdactiviteStructure(), annee.getIdannee());
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

                                    this.activites.clear();
                                    this.activiteStructures.clear();
                                    List<Activite> activites = activiteFacadeLocal.find(SessionMBean.getDistrict(), sousaxe);

                                    for (Activite act : activites) {
                                        List<Chronogramme> chronogrammes = chronogrammeFacadeLocal.findByActivite(act, SessionMBean.getAnnee());
                                        if (!chronogrammes.isEmpty()) {
                                            this.activites.add(act);
                                        }
                                    }

                                    if (!this.activites.isEmpty()) {
                                        for (Activite act : this.activites) {
                                            List<ActiviteStructure> activiteStructures = activiteStructureFacadeLocal.find(structure, act);
                                            if (!activiteStructures.isEmpty()) {
                                                this.activiteStructures.add(activiteStructures.get(0));
                                            }
                                        }
                                    }

                                    break;
                                } else {
                                    sousaxe = new Sousaxe();
                                    sousaxes.clear();
                                    activites.clear();
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

                                    this.activites.clear();
                                    this.activiteStructures.clear();
                                    List<Activite> activites = activiteFacadeLocal.find(SessionMBean.getDistrict(), sousaxe);

                                    for (Activite act : activites) {
                                        List<Chronogramme> chronogrammes = chronogrammeFacadeLocal.findByActivite(act, SessionMBean.getAnnee());
                                        if (!chronogrammes.isEmpty()) {
                                            this.activites.add(act);
                                        }
                                    }

                                    if (!this.activites.isEmpty()) {
                                        for (Activite act : this.activites) {
                                            List<ActiviteStructure> activiteStructures = activiteStructureFacadeLocal.find(structure, act);
                                            if (!activiteStructures.isEmpty()) {
                                                this.activiteStructures.add(activiteStructures.get(0));
                                            }
                                        }
                                    }
                                    break;
                                } else {
                                    activites.clear();
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

    public List<Activite> getActivites() {
        return activites;
    }

    public void setActivites(List<Activite> activites) {
        this.activites = activites;
    }

    public Sourcefinancement getSourcefinancement() {
        return sourcefinancement;
    }

    public void setSourcefinancement(Sourcefinancement sourcefinancement) {
        this.sourcefinancement = sourcefinancement;
    }

    public List<Sourcefinancement> getSourcefinancements() {
        sourcefinancements = sourcefinancementFacadeLocal.findAll();
        return sourcefinancements;
    }

    public void setSourcefinancements(List<Sourcefinancement> sourcefinancements) {
        this.sourcefinancements = sourcefinancements;
    }

    public Indicateur getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(Indicateur indicateur) {
        this.indicateur = indicateur;
    }

    public List<Indicateur> getIndicateurs() {
        return indicateurs;
    }

    public void setIndicateurs(List<Indicateur> indicateurs) {
        this.indicateurs = indicateurs;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ActiviteStructure getActiviteStructure() {
        return activiteStructure;
    }

    public void setActiviteStructure(ActiviteStructure activiteStructure) {
        this.activiteStructure = activiteStructure;
    }

    public List<ActiviteStructure> getActiviteStructures() {
        return activiteStructures;
    }

    public void setActiviteStructures(List<ActiviteStructure> activiteStructures) {
        this.activiteStructures = activiteStructures;
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

    public Double getMontant_programme() {
        return montant_programme;
    }

    public void setMontant_programme(Double montant_programme) {
        this.montant_programme = montant_programme;
    }

}

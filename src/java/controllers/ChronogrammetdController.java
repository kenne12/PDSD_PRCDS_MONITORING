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
import entities.ChronogrammeTacheDistrict;
import entities.District;
import entities.Periode;
import entities.Periodederattachement;
import entities.Sousaxe;
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
import sessions.AxeFacadeLocal;
import sessions.ChronogrammeTacheDistrictFacadeLocal;
import sessions.PeriodeFacadeLocal;
import sessions.PeriodederattachementFacadeLocal;
import sessions.SousaxeFacadeLocal;
import sessions.TachedistrictPeriodeFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class ChronogrammetdController implements Serializable {

    @EJB
    private TachedistrictPeriodeFacadeLocal tachedistrictPeriodeFacadeLocal;
    private TachedistrictPeriode tachedistrictPeriode = new TachedistrictPeriode();
    private List<TachedistrictPeriode> tachedistrictPeriodes = new ArrayList<>();

    @EJB
    private ChronogrammeTacheDistrictFacadeLocal chronogrammeTacheDistrictFacadeLocal;
    private List<ChronogrammeTacheDistrict> chronogrammeTacheDistricts = new ArrayList<>();

    @EJB
    private AxeFacadeLocal axeFacadeLocal;
    private Axe axe = new Axe();
    private List<Axe> axes = new ArrayList<>();

    @EJB
    private SousaxeFacadeLocal sousaxeFacadeLocal;
    private Sousaxe sousaxe = new Sousaxe();
    private List<Sousaxe> sousaxes = new ArrayList<>();

    private Annee annee = SessionMBean.getAnnee();

    @EJB
    private PeriodederattachementFacadeLocal periodederattachementFacadeLocal;
    private Periodederattachement periodederattachement = new Periodederattachement();
    private List<Periodederattachement> periodederattachements = new ArrayList<>();

    @EJB
    private PeriodeFacadeLocal periodeFacadeLocal;
    private List<Periode> periodes = new ArrayList<>();
    private List<Periode> periodes_1 = new ArrayList<>();
    private List<Periode> periodes_2 = new ArrayList<>();
    private List<Periode> selectedP = new ArrayList<>();

    private District district = SessionMBean.getDistrict();

    private boolean detail = true;

    private String mode = "";

    /**
     * Creates a new instance of SousaxeController
     */
    public ChronogrammetdController() {
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
                    tachedistrictPeriodes.clear();
                    tachedistrictPeriodes.clear();
                    if (SessionMBean.getPeriodeRat() != null) {
                        this.tachedistrictPeriodes = tachedistrictPeriodeFacadeLocal.findByIdsousaxe(sousaxe.getIdsousaxe(), SessionMBean.getStructure().getIdstructure(), SessionMBean.getAnnee().getIdannee(), SessionMBean.getPeriodeRat().getIdperiodederattachement());
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

    public void updateAll() {
        try {
            if (axe != null) {
                sousaxes = sousaxeFacadeLocal.findByAxe(axe);

                if (!sousaxes.isEmpty()) {
                    sousaxe = sousaxes.get(0);
                    tachedistrictPeriodes.clear();
                    if (SessionMBean.getPeriodeRat() != null) {
                        this.tachedistrictPeriodes = tachedistrictPeriodeFacadeLocal.findByIdsousaxe(sousaxe.getIdsousaxe(), SessionMBean.getStructure().getIdstructure(), SessionMBean.getAnnee().getIdannee(), SessionMBean.getPeriodeRat().getIdperiodederattachement());
                    }
                } else {
                    sousaxe = new Sousaxe();
                    tachedistrictPeriodes.clear();
                }
            } else {
                sousaxe = new Sousaxe();
                tachedistrictPeriodes.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSousaxe() {
        try {
            if (!sousaxes.isEmpty()) {

                if (sousaxe != null) {

                    tachedistrictPeriodes.clear();
                    if (SessionMBean.getPeriodeRat() != null) {
                        this.tachedistrictPeriodes = tachedistrictPeriodeFacadeLocal.findByIdsousaxe(sousaxe.getIdsousaxe(), SessionMBean.getStructure().getIdstructure(), SessionMBean.getAnnee().getIdannee(), SessionMBean.getPeriodeRat().getIdperiodederattachement());
                    }
                }
            } else {
                sousaxe = new Sousaxe();
                tachedistrictPeriodes.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openChronogrammeDialog(TachedistrictPeriode item) {
        try {
            this.tachedistrictPeriode = item;
            HttpSession session = SessionMBean.getSession();
            session.setAttribute("tache_district_p", item);

            periodes_1 = periodeFacadeLocal.findByIdperiodeRat(SessionMBean.getPeriodeRat().getIdperiodederattachement());
            selectedP.clear();
            chronogrammeTacheDistricts = chronogrammeTacheDistrictFacadeLocal.findByIdtache(item.getIdtachedistrictPeriode());
            if (!chronogrammeTacheDistricts.isEmpty()) {
                for (ChronogrammeTacheDistrict c : chronogrammeTacheDistricts) {
                    selectedP.add(c.getIdperiode());
                }
            }

            RequestContext.getCurrentInstance().execute("PF('ChronogrammeCreateDialog').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveChronogramme() {
        try {
            List<ChronogrammeTacheDistrict> chs = chronogrammeTacheDistrictFacadeLocal.findByIdtache(SessionMBean.getTacheDistrictP().getIdtachedistrictPeriode());

            List<Periode> selectedP1 = new ArrayList<>();
            if (!chs.isEmpty()) {
                for (ChronogrammeTacheDistrict c : chs) {
                    selectedP1.add(c.getIdperiode());
                }
            }

            for (Periode p : selectedP) {
                if (!selectedP1.contains(p)) {
                    ChronogrammeTacheDistrict chdp = new ChronogrammeTacheDistrict();
                    chdp.setIdchronogrammeTacheDistrict(chronogrammeTacheDistrictFacadeLocal.nextId());
                    chdp.setIdperiode(p);
                    chdp.setIdtachedistrict(SessionMBean.getTacheDistrictP().getIdtachedistrict());
                    chdp.setIdtachedistrictPeriode(SessionMBean.getTacheDistrictP());
                    chronogrammeTacheDistrictFacadeLocal.create(chdp);
                } else {
                    if (!chs.isEmpty()) {

                        for (Periode p1 : selectedP1) {
                            if (!selectedP.contains(p1)) {

                                for (ChronogrammeTacheDistrict c : chs) {
                                    if (c.getIdperiode().equals(p1)) {
                                        chronogrammeTacheDistrictFacadeLocal.remove(c);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            JsfUtil.addSuccessMessage("Opération réussie");
            RequestContext.getCurrentInstance().execute("PF('ChronogrammeCreateDialog').hide()");

        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Opération échouée");
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

                                    tachedistrictPeriodes.clear();
                                    if (SessionMBean.getPeriodeRat() != null) {
                                        this.tachedistrictPeriodes = tachedistrictPeriodeFacadeLocal.findByIdsousaxe(sousaxe.getIdsousaxe(), SessionMBean.getStructure().getIdstructure(), SessionMBean.getAnnee().getIdannee(), SessionMBean.getPeriodeRat().getIdperiodederattachement());
                                    }

                                    break;
                                } else {
                                    sousaxe = new Sousaxe();
                                    sousaxes.clear();
                                    tachedistrictPeriodes.clear();
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

                                    tachedistrictPeriodes.clear();
                                    if (SessionMBean.getPeriodeRat() != null) {
                                        this.tachedistrictPeriodes = tachedistrictPeriodeFacadeLocal.findByIdsousaxe(sousaxe.getIdsousaxe(), SessionMBean.getStructure().getIdstructure(), SessionMBean.getAnnee().getIdannee(), SessionMBean.getPeriodeRat().getIdperiodederattachement());
                                    }

                                    break;
                                } else {
                                    tachedistrictPeriodes.clear();
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

    public boolean findChronogramme(TachedistrictPeriode item, Periode var) {
        try {
            ChronogrammeTacheDistrict c = chronogrammeTacheDistrictFacadeLocal.findByIdtache(item.getIdtachedistrictPeriode(), var.getIdperiode());
            if (c != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

    public List<Periodederattachement> getPeriodederattachements() {
        try {
            this.periodederattachements.clear();
            this.periodederattachements = periodederattachementFacadeLocal.findAllRange();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return periodederattachements;
    }

    public void setPeriodederattachements(List<Periodederattachement> periodederattachements) {
        this.periodederattachements = periodederattachements;
    }

    public List<TachedistrictPeriode> getTachedistrictPeriodes() {
        return tachedistrictPeriodes;
    }

    public void setTachedistrictPeriodes(List<TachedistrictPeriode> tachedistrictPeriodes) {
        this.tachedistrictPeriodes = tachedistrictPeriodes;
    }

    public TachedistrictPeriode getTachedistrictPeriode() {
        return tachedistrictPeriode;
    }

    public void setTachedistrictPeriode(TachedistrictPeriode tachedistrictPeriode) {
        this.tachedistrictPeriode = tachedistrictPeriode;
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

    public List<Periode> getPeriodes() {
        try {
            periodes.clear();
            if (SessionMBean.getPeriodeRat() != null) {
                periodes = periodeFacadeLocal.findByIdperiodeRat(SessionMBean.getPeriodeRat().getIdperiodederattachement());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return periodes;
    }

    public void setPeriodes(List<Periode> periodes) {
        this.periodes = periodes;
    }

    public List<Periode> getPeriodes_1() {
        return periodes_1;
    }

    public void setPeriodes_1(List<Periode> periodes_1) {
        this.periodes_1 = periodes_1;
    }

    public List<Periode> getSelectedP() {
        return selectedP;
    }

    public void setSelectedP(List<Periode> selectedP) {
        this.selectedP = selectedP;
    }

    public List<Periode> getPeriodes_2() {
        try {
            periodes_2.clear();
            if (SessionMBean.getPeriodeRat() != null) {
                periodes_2 = periodeFacadeLocal.findByIdperiodeRat(SessionMBean.getPeriodeRat().getIdperiodederattachement());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return periodes_2;
    }

    public void setPeriodes_2(List<Periode> periodes_2) {
        this.periodes_2 = periodes_2;
    }

}

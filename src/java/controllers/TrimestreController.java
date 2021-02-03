/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Mouchard;
import entities.Periodederattachement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;
import sessions.PeriodederattachementFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class TrimestreController implements Serializable {

    /**
     * Creates a new instance of PeriodeController
     */
    @EJB
    private PeriodederattachementFacadeLocal periodederattachementFacadeLocal;
    private Periodederattachement periodederattachement = new Periodederattachement();
    private Periodederattachement selected = new Periodederattachement();
    private List<Periodederattachement> periodederattachements = new ArrayList<>();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard;

    private boolean detail = true;

    private String mode = "";

    public TrimestreController() {

    }

    @PostConstruct
    private void init() {
        periodederattachement = new Periodederattachement();
        selected = new Periodederattachement();
        mouchard = new Mouchard();
    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        mode = "Create";
        periodederattachement = new Periodederattachement();
    }

    public void prepareEdit() {
        try {
            if (selected != null) {
                mode = "Edit";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {

                periodederattachement.setIdperiodederattachement(periodederattachementFacadeLocal.nextId());
                periodederattachementFacadeLocal.create(periodederattachement);

                detail = true;

                mouchard.setIdoperation(mouchardFacadeLocal.nextId());
                mouchard.setAction("Enregistrement du trimestre -> " + periodederattachement.getNom());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
                periodederattachement = new Periodederattachement();
                JsfUtil.addSuccessMessage("Enregistrement effectué avec succès");

            } else {
                if (selected != null) {

                    periodederattachementFacadeLocal.edit(selected);
                    detail = true;
                    selected = new Periodederattachement();
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne selectionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            if (selected != null) {

                periodederattachementFacadeLocal.remove(selected);
                mouchard.setAction("Suppression du trimestre -> " + selected.getNom());
                mouchard.setIdutilisateur(SessionMBean.getUser());
                mouchard.setDateaction(new Date());
                mouchardFacadeLocal.create(mouchard);
                detail = true;
                JsfUtil.addSuccessMessage("Suppression effectuée avec succès");

            } else {
                JsfUtil.addErrorMessage("Aucune période selectionnée !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.getPeriodederattachements();
        }
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public Periodederattachement getPeriodederattachement() {
        return periodederattachement;
    }

    public void setPeriodederattachement(Periodederattachement periodederattachement) {
        this.periodederattachement = periodederattachement;
    }

    public List<Periodederattachement> getPeriodederattachements() {
        periodederattachements = periodederattachementFacadeLocal.findAllRange();
        return periodederattachements;
    }

    public void setPeriodederattachements(List<Periodederattachement> periodederattachements) {
        this.periodederattachements = periodederattachements;
    }

    public Mouchard getMouchard() {
        return mouchard;
    }

    public void setMouchard(Mouchard mouchard) {
        this.mouchard = mouchard;
    }

    public Periodederattachement getSelected() {
        return selected;
    }

    public void setSelected(Periodederattachement selected) {
        this.selected = selected;
    }

}

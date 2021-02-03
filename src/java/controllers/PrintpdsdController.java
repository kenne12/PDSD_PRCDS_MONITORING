/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.SessionMBean;
import entities.District;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.DistrictFacadeLocal;

import utilitaire.Printer;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class PrintpdsdController {

    /**
     * Creates a new instance of PlanoperationnelController
     */
    private Printer printer = new Printer();

    @EJB
    DistrictFacadeLocal districtFacadeLocal;
    private District district = new District();
    private List<District> districts = new ArrayList<>();

    public PrintpdsdController() {

    }

    @PostConstruct
    private void init() {
        printer = new Printer();
    }

    public void printMicroPlanD() {
        try {
            Map parameter = new HashMap();
            parameter.put("idstructure", SessionMBean.getStructure().getIdstructure());
            parameter.put("idannee", SessionMBean.getAnnee().getIdannee());
            if ("fr".equals(SessionMBean.getLangue())) {
                printer.print("/report/pta_pdsd/couverturemicroplan.jasper", parameter);
            } else {
                printer.print("/report/pta_pdsd/couverturemicroplan.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printMicroPlanDWord() {
        try {
            Map parameter = new HashMap();
            parameter.put("idstructure", SessionMBean.getStructure().getIdstructure());
            parameter.put("idannee", SessionMBean.getAnnee().getIdannee());
            if ("fr".equals(SessionMBean.getLangue())) {
                printer.DOCX("/report/pta_pdsd/couverturemicroplan.jasper", parameter);
            } else {
                printer.DOCX("/report/pta_pdsd/couverturemicroplan.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printMicroPlanRExcell() {
        try {
            Map parameter = new HashMap();
            parameter.put("idstructure", SessionMBean.getStructure().getIdstructure());
            parameter.put("idannee", SessionMBean.getAnnee().getIdannee());
            if ("fr".equals(SessionMBean.getLangue())) {
                printer.XLSX("/report/pta_pdsd/couverturemicroplan.jasper", parameter);
            } else {
                printer.XLSX("/report/pta_pdsd/couverturemicroplan.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printMicroPlanR() {
        try {
            Map parameter = new HashMap();
            parameter.put("idstructure", SessionMBean.getStructure().getIdstructure());
            parameter.put("idannee", SessionMBean.getAnnee().getIdannee());
            if ("fr".equals(SessionMBean.getLangue())) {
                printer.print("/report/pta_prcds/couverturemicroplan.jasper", parameter);
            } else {
                printer.print("/report/pta_prcds/couverturemicroplan.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printMicroPlanRWord() {
        try {
            Map parameter = new HashMap();
            parameter.put("idstructure", SessionMBean.getStructure().getIdstructure());
            parameter.put("idannee", SessionMBean.getAnnee().getIdannee());
            if ("fr".equals(SessionMBean.getLangue())) {
                printer.DOCX("/report/pta_pdsd/couverturemicroplan.jasper", parameter);
            } else {
                printer.DOCX("/report/pta_pdsd/couverturemicroplan.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printMicroPlanDExcell() {
        try {
            Map parameter = new HashMap();
            parameter.put("idstructure", SessionMBean.getStructure().getIdstructure());
            parameter.put("idannee", SessionMBean.getAnnee().getIdannee());
            if ("fr".equals(SessionMBean.getLangue())) {
                printer.XLSX("/report/pta_pdsd/couverturemicroplan.jasper", parameter);
            } else {
                printer.XLSX("/report/pta_pdsd/couverturemicroplan.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void printSuiviD() {
        try {
            Map parameter = new HashMap();
            parameter.put("iddistrict", SessionMBean.getDistrict().getIddistrict());
            parameter.put("idannee", SessionMBean.getAnnee().getIdannee());
            if ("fr".equals(SessionMBean.getLangue())) {
                printer.print("/report/pta_pdsd/etat_suivi_indicateur.jasper", parameter);
            } else {
                printer.print("/report/pta_pdsd/etat_suivi_indicateur.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void printSuiviR() {
        try {
            Map parameter = new HashMap();
            parameter.put("idregion", SessionMBean.getRegion().getIdregion());
            parameter.put("idannee", SessionMBean.getAnnee().getIdannee());
            if ("fr".equals(SessionMBean.getLangue())) {
                printer.print("/report/pta_prcds/etat_suivi_indicateur.jasper", parameter);
            } else {
                printer.print("/report/pta_prcds/etat_suivi_indicateur.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Printer getPrinter() {
        return printer;
    }
    
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<District> getDistricts() {
        districts = districtFacadeLocal.findAll();
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

}

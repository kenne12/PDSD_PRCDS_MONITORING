/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.util;

import entities.ActiviteStructure;
import entities.ActiviteStructureRegion;
import entities.Annee;
import entities.District;
import entities.Periodederattachement;
import entities.Region;
import entities.Structure;
import entities.TachedistrictPeriode;
import entities.TacheregionPeriode;
import entities.Utilisateur;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionMBean {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("login").toString();
    }

    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("utilisateurid");
        } else {
            return null;
        }
    }

    public static Utilisateur getUser() {
        HttpSession session = getSession();
        if (session != null) {
            return (Utilisateur) session.getAttribute("user");
        } else {
            return null;
        }
    }

    public static Structure getStructure() {
        HttpSession session = getSession();
        if (session != null) {
            return (Structure) session.getAttribute("structure");
        } else {
            return null;
        }
    }

    public static ActiviteStructure getActiviteStructure() {
        HttpSession session = getSession();
        if (session != null) {
            return (ActiviteStructure) session.getAttribute("activite_s");
        } else {
            return null;
        }
    }

    public static Annee getAnnee() {
        HttpSession session = getSession();
        if (session != null) {
            return (Annee) session.getAttribute("annee");
        } else {
            return null;
        }
    }

    public static District getDistrict() {
        HttpSession session = getSession();
        if (session != null) {
            return (District) session.getAttribute("district");
        } else {
            return null;
        }
    }

    public static Region getRegion() {
        HttpSession session = getSession();
        if (session != null) {
            return (Region) session.getAttribute("region");
        } else {
            return null;
        }
    }

    public static String getLangue() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("langue");
        } else {
            return null;
        }
    }

    public static Boolean getSession1() {
        HttpSession session = getSession();
        if (session != null) {
            if (session.getAttribute("session") != null) {
                return (Boolean) session.getAttribute("session");
            }
            return false;
        } else {
            return false;
        }
    }

    public static Periodederattachement getPeriodeRat() {
        HttpSession session = getSession();
        if (session != null) {
            return (Periodederattachement) session.getAttribute("trimestre");
        } else {
            return null;
        }
    }

    public static TachedistrictPeriode getTacheDistrictP() {
        HttpSession session = getSession();
        if (session != null) {
            return (TachedistrictPeriode) session.getAttribute("tache_district_p");
        } else {
            return null;
        }
    }

    public static TacheregionPeriode getTacheRegionP() {
        HttpSession session = getSession();
        if (session != null) {
            return (TacheregionPeriode) session.getAttribute("tache_region_p");
        } else {
            return null;
        }
    }

    public static ActiviteStructureRegion getActiviteRegionStructure() {
        HttpSession session = getSession();
        if (session != null) {
            return (ActiviteStructureRegion) session.getAttribute("activite_sr");
        } else {
            return null;
        }
    }

}

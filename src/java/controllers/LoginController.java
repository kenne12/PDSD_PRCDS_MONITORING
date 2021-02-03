/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.util.JsfUtil;
import controllers.util.SessionMBean;
import entities.Annee;
import entities.Compteutilisateur;
import entities.District;
import entities.Menu;
import entities.Mouchard;
import entities.PartiehauteStructure;
import entities.Periodederattachement;
import entities.Privilege;
import entities.Structure;
import entities.Utilisateur;
import entities.UtilisateurRegion;
import entities.Utilisateurdistrict;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sessions.AnneeFacadeLocal;
import sessions.CompteutilisateurFacadeLocal;
import sessions.MenuFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PartiehauteFacadeLocal;
import sessions.PartiehauteStructureFacadeLocal;
import sessions.PeriodederattachementFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.UtilisateurFacadeLocal;
import sessions.UtilisateurRegionFacadeLocal;
import sessions.UtilisateurdistrictFacadeLocal;
import utilitaire.Utilitaires;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    private String mode = "";

    private District district = new District();

    @EJB
    private PartiehauteStructureFacadeLocal partiehauteStructureFacadeLocal;

    @EJB
    private PartiehauteFacadeLocal partiehauteFacadeLocal;

    private String cartedistrict = "MINSANTE.jpg";

    @EJB
    private CompteutilisateurFacadeLocal compteutilisateurFacadeLocal;
    private Compteutilisateur compteutilisateur = new Compteutilisateur();

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    private Utilisateur utilisateur = new Utilisateur();
    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private Mouchard mouchard = new Mouchard();

    @EJB
    private PrivilegeFacadeLocal privilegeFacadeLocal;
    private Privilege privilege = new Privilege();
    public static List<String> privilegeUser = new ArrayList<>();
    public static List<String> privilegeTotal = new ArrayList<>();
    private static List<Privilege> privileges = new ArrayList<>();

    @EJB
    private MenuFacadeLocal menuFacadeLocal;
    private static MenuFacadeLocal menuFacadeLocal1;
    private Menu menu;
    public static Menu menu1 = new Menu();

    private String language = "fr";

    @EJB
    private UtilisateurdistrictFacadeLocal utilisateurdistrictFacadeLocal;
    private Utilisateurdistrict utilisateurdistrict = new Utilisateurdistrict();
    private List<Utilisateurdistrict> utilisateurdistricts = new ArrayList<>();

    @EJB
    private UtilisateurRegionFacadeLocal utilisateurRegionFacadeLocal;
    private UtilisateurRegion utilisateurRegion = new UtilisateurRegion();
    private List<UtilisateurRegion> utilisateurRegions = new ArrayList<>();

    @EJB
    private StructureFacadeLocal structureFacadeLocal;
    private Structure structure = new Structure();
    private Structure structure_1 = new Structure();
    private List<Structure> structures = new ArrayList<>();

    @EJB
    private AnneeFacadeLocal anneeFacadeLocal;
    private Annee annee = new Annee();
    private List<Annee> annees = new ArrayList<>();

    @EJB
    private PeriodederattachementFacadeLocal periodederattachementFacadeLocal;
    private Periodederattachement periodederattachement = new Periodederattachement();
    private List<Periodederattachement> periodederattachements = new ArrayList<>();

    private boolean viewSession = true;

    protected Boolean session = true;

    public LoginController() {

    }

    @PostConstruct
    private void init() {
        menu1 = new Menu();
    }

    public void validateUsernamePassword() {
        try {

            String password = "";

            password = org.apache.commons.codec.digest.DigestUtils.md5Hex(compteutilisateur.getPassword());

            Compteutilisateur usr = compteutilisateurFacadeLocal.login(compteutilisateur.getLogin(), password);
            if (usr != null) {

                System.err.println("user trouvée");

                if (usr.getEtat()) {
                    utilisateur = usr.getIdutilisateur();
                    HttpSession session = SessionMBean.getSession();

                    session.setAttribute("login", utilisateur.getNom());
                    session.setAttribute("user", utilisateur);
                    session.setAttribute("langue", language);

                    if (mode.equals("district")) {
                        utilisateurdistricts = utilisateurdistrictFacadeLocal.findByUtilisateur(utilisateur.getIdutilisateur());
                    } else {
                        utilisateurRegions = utilisateurRegionFacadeLocal.findByUtilisateur(utilisateur.getIdutilisateur());
                    }
                    Utilitaires.saveOperation("connexion ", utilisateur, mouchardFacadeLocal);

                    setPrivilegeAll();
                    setPrivilegeUser();
                    FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/faces/index.xhtml");
                } else {
                    Utilitaires.saveOperation("tentative de connection avec un compte bloqué", usr.getIdutilisateur(), mouchardFacadeLocal);
                    JsfUtil.addErrorMessage("Votre compte est bloqué");
                }
            } else {
                System.err.println("echec d'authentification");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login et mot de passe incorrets", "Please enter correct username and Password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStructure() {
        try {
            structures.clear();
            if (mode.endsWith("district")) {
                if (utilisateurdistrict.getId() != null) {
                    utilisateurdistrict = utilisateurdistrictFacadeLocal.find(utilisateurdistrict.getId());
                    structures = structureFacadeLocal.findByDistrict(utilisateurdistrict.getIddistrict().getIddistrict());
                }
            } else {
                if (utilisateurRegion.getIdutilisateurRegion() != null) {
                    utilisateurRegion = utilisateurRegionFacadeLocal.find(utilisateurRegion.getIdutilisateurRegion());
                    structures = structureFacadeLocal.findByIdregion(utilisateurRegion.getIdregion().getIdregion());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPrivilegeAll() {
        List<Menu> menus = menuFacadeLocal.findAll();
        for (Menu m : menus) {
            privilegeTotal.add(m.getRessource());
        }
    }

    public void setPrivilegeUser() {
        if (SessionMBean.getUser() != null) {
            privileges = privilegeFacadeLocal.findByGroupeUser(SessionMBean.getUser().getIdgroupeutilisateur().getIdgroupeutilisateur(), true, true);
            if (privileges.isEmpty()) {
                privilegeUser = new ArrayList<>();
            } else {
                privilegeUser.clear();
                for (Privilege p : privileges) {
                    privilegeUser.add(p.getIdmenu().getRessource());
                }
            }
        } else {
            privilegeUser = new ArrayList<>();
        }
    }

    public void logout() {
        HttpSession session = SessionMBean.getSession();
        Utilisateur usr = SessionMBean.getUser();
        session.invalidate();

        String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

        try {
            Utilitaires.saveOperation("Déconnexion", usr, mouchardFacadeLocal);
            FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/faces/acceuil.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void watcher() {
        try {
            if (SessionMBean.getUser() == null) {
                String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/faces/acceuil.xhtml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findMenu(String ressource) {
        try {
            menu1 = menuFacadeLocal1.findByRessource(ressource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSession() {
        try {

            HttpSession session = SessionMBean.getSession();

            structure = structureFacadeLocal.find(structure.getIdstructure());
            annee = anneeFacadeLocal.find(annee.getIdannee());
            session.setAttribute("structure", structure);
            session.setAttribute("annee", annee);

            System.err.println("Id structure : " + structure.getIdstructure());
            System.err.println("Id idannee : " + annee.getIdannee());

            PartiehauteStructure p = partiehauteStructureFacadeLocal.findByIdstructure(structure.getIdstructure());
            if (p == null) {
                p = new PartiehauteStructure();
                p.setIdpartiehauteStructure(partiehauteStructureFacadeLocal.nextId());
                p.setIdstructure(structure);
                partiehauteStructureFacadeLocal.create(p);
            }

            if (mode.equals("district")) {
                utilisateurdistrict = utilisateurdistrictFacadeLocal.find(utilisateurdistrict.getId());
                session.setAttribute("district", utilisateurdistrict.getIddistrict());
            } else {
                utilisateurRegion = utilisateurRegionFacadeLocal.find(utilisateurRegion.getIdutilisateurRegion());
                session.setAttribute("region", utilisateurRegion.getIdregion());
            }
            cartedistrict = "MINSANTE.jpg";
            viewSession = false;
        } catch (Exception e) {
            e.printStackTrace();
            viewSession = true;
        }
    }

    public void checkSession() {
        try {
            if (!periodederattachement.equals(null)) {
                periodederattachement = periodederattachementFacadeLocal.find(periodederattachement.getIdperiodederattachement());
                HttpSession session = SessionMBean.getSession();
                session.setAttribute("session", true);
                session.setAttribute("trimestre", periodederattachement);
                this.session = false;
                FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/faces" + FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetSession() {
        session = true;
        SessionMBean.getSession().removeAttribute("session");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/faces" + FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean getSession() {
        try {
            if (SessionMBean.getSession1()) {
                session = false;

            } else {
                session = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }

    public void setSession(Boolean session) {
        this.session = session;
    }

    public String switchFr() {
        language = "fr";
        return null;
    }

    public String switchEn() {
        language = "en";
        return null;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Compteutilisateur getCompteutilisateur() {
        return compteutilisateur;
    }

    public void setCompteutilisateur(Compteutilisateur compteutilisateur) {
        this.compteutilisateur = compteutilisateur;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Utilisateurdistrict> getUtilisateurdistricts() {
        return utilisateurdistricts;
    }

    public void setUtilisateurdistricts(List<Utilisateurdistrict> utilisateurdistricts) {
        this.utilisateurdistricts = utilisateurdistricts;
    }

    public boolean isViewSession() {
        return viewSession;
    }

    public void setViewSession(boolean viewSession) {
        this.viewSession = viewSession;
    }

    public Utilisateurdistrict getUtilisateurdistrict() {
        return utilisateurdistrict;
    }

    public void setUtilisateurdistrict(Utilisateurdistrict utilisateurdistrict) {
        this.utilisateurdistrict = utilisateurdistrict;
    }

    public String getCartedistrict() {
        return cartedistrict;
    }

    public void setCartedistrict(String cartedistrict) {
        this.cartedistrict = cartedistrict;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public List<Structure> getStructures() {
        return structures;
    }

    public void setStructures(List<Structure> structures) {
        this.structures = structures;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            annees = anneeFacadeLocal.findByEtatChronogramme(true);
        } catch (Exception e) {
        }
        return annees;
    }

    public void initDistric() {
        try {
            mode = "district";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initRegion() {
        try {
            mode = "region";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
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

    public Structure getStructure_1() {
        return structure_1;
    }

    public void setStructure_1(Structure structure_1) {
        this.structure_1 = structure_1;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public UtilisateurRegion getUtilisateurRegion() {
        return utilisateurRegion;
    }

    public void setUtilisateurRegion(UtilisateurRegion utilisateurRegion) {
        this.utilisateurRegion = utilisateurRegion;
    }

    public List<UtilisateurRegion> getUtilisateurRegions() {
        return utilisateurRegions;
    }

    public void setUtilisateurRegions(List<UtilisateurRegion> utilisateurRegions) {
        this.utilisateurRegions = utilisateurRegions;
    }

}

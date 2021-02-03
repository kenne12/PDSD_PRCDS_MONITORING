/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.Cible;
import entities.District;
import entities.IndicateurDistrict;
import entities.Probleme;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne
 */
@Stateless
public class CibleFacade extends AbstractFacade<Cible> implements CibleFacadeLocal {

    @PersistenceContext(unitName = "PDSD_MONITORINGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CibleFacade() {
        super(Cible.class);
    }

    @Override
    public Integer nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(c.idcible) FROM Cible c");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Cible> findByProbleme(Probleme probleme) throws Exception {
        List<Cible> cibles = null;
        Query query = em.createQuery("SELECT c FROM Cible c WHERE c.idprobleme.idprobleme=:probleme");
        query.setParameter("probleme", probleme.getIdprobleme());
        cibles = query.getResultList();
        return cibles;
    }

    @Override
    public List<Cible> findByProbleme(Probleme probleme, Annee annee) throws Exception {
        List<Cible> cibles = null;
        Query query = em.createQuery("SELECT c FROM Cible c WHERE c.idprobleme.idprobleme=:probleme AND c.idannee.idannee=:annee");
        query.setParameter("probleme", probleme.getIdprobleme()).setParameter("annee", annee.getIdannee());
        cibles = query.getResultList();
        return cibles;
    }

    @Override
    public List<Cible> find(IndicateurDistrict indicateurDistrict, Annee annee) throws Exception {
        Query query = em.createQuery("SELECT c FROM Cible c WHERE c.idindicateur.idindicateur=:indicateur AND c.iddistrict.iddistrict=:district AND c.idannee.idannee=:annee");
        query.setParameter("indicateur", indicateurDistrict.getIdindicateur().getIdindicateur());
        query.setParameter("district", indicateurDistrict.getIddistrict().getIddistrict()).setParameter("annee", annee.getIdannee());
        return query.getResultList();
    }

    @Override
    public List<Cible> find(IndicateurDistrict indicateurDistrict) throws Exception {
        Query query = em.createQuery("SELECT c FROM Cible c WHERE c.idindicateur.idindicateur=:indicateur AND c.iddistrict.iddistrict=:district");
        query.setParameter("indicateur", indicateurDistrict.getIdindicateur().getIdindicateur());
        query.setParameter("district", indicateurDistrict.getIddistrict().getIddistrict());
        return query.getResultList();
    }

    @Override
    public List<Cible> find(District district) throws Exception {
        Query query = em.createQuery("SELECT c FROM Cible c WHERE c.iddistrict.iddistrict=:district");
        query.setParameter("district", district.getIddistrict());
        return query.getResultList();
    }

    @Override
    public List<Cible> findByDistrictSousaxe(int iddistrict, int idsousaxe) {
        Query query = em.createQuery("SELECT c FROM Cible c WHERE c.idindicateur.idinterventionpnds.idcategorieintervention.idsousaxe.idsousaxe=:idsousaxe AND c.iddistrict.iddistrict=:iddistrict");
        query.setParameter("idsousaxe", idsousaxe).setParameter("iddistrict", iddistrict);
        return query.getResultList();
    }

    @Override
    public List<Cible> findByDistrictSousaxe(int iddistrict, int idsousaxe, int idannee) {
        Query query = em.createQuery("SELECT c FROM Cible c WHERE c.idindicateur.idinterventionpnds.idcategorieintervention.idsousaxe.idsousaxe=:idsousaxe AND c.iddistrict.iddistrict=:iddistrict AND c.idannee.idannee=:idannee ORDER BY c.idindicateur.idinterventionpnds.code");
        query.setParameter("idsousaxe", idsousaxe).setParameter("iddistrict", iddistrict).setParameter("idannee", idannee);
        return query.getResultList();
    }

    @Override
    public List<Cible> find(int iddistrict, int idannee) throws Exception {
        Query query = em.createQuery("SELECT c FROM Cible c WHERE c.iddistrict.iddistrict=:district AND c.idannee.idannee=:idannee");
        query.setParameter("district", iddistrict).setParameter("idannee", idannee);
        return query.getResultList();
    }
}

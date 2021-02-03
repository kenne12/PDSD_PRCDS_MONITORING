/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TachedistrictPeriode;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
@Stateless
public class TachedistrictPeriodeFacade extends AbstractFacade<TachedistrictPeriode> implements TachedistrictPeriodeFacadeLocal {

    @PersistenceContext(unitName = "PDSD_MONITORINGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TachedistrictPeriodeFacade() {
        super(TachedistrictPeriode.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(t.idtachedistrictPeriode) FROM TachedistrictPeriode t");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<TachedistrictPeriode> findByIdtache(long idtache) throws Exception {
        Query query = em.createQuery("SELECT t FROM TachedistrictPeriode t WHERE t.idtachedistrict.idtachedistrict=:idtache");
        query.setParameter("idtache", idtache);
        return query.getResultList();
    }

    @Override
    public List<TachedistrictPeriode> findByIdindicateur(int idindicateur, int iddistrict, int idannee, int idperiode) throws Exception {
        Query query = em.createQuery("SELECT t FROM TachedistrictPeriode t WHERE t.idtachedistrict.idactivitestructure.idactivite.idprobleme.idindicateurDistrict.idindicateur.idindicateur=:idindicateur AND t.idtachedistrict.idactivitestructure.idactivite.idprobleme.idindicateurDistrict.iddistrict.iddistrict=:iddistrict AND t.idtachedistrict.idannee.idannee=:idannee  AND t.idperioderattachement.idperiodederattachement=:idperiode ORDER BY t.idtachedistrict.idactivitestructure.idactivite.idresultatattendu.idindicateur.idinterventionpnds.code");
        query.setParameter("idindicateur", idindicateur).setParameter("iddistrict", iddistrict).setParameter("idannee", idannee).setParameter("idperiode", idperiode);
        return query.getResultList();
    }

    @Override
    public List<TachedistrictPeriode> findByIdsousaxe(int idsousaxe, int idstructure, int idannee, int idperiode) throws Exception {
        Query query = em.createQuery("SELECT t FROM TachedistrictPeriode t WHERE t.idtachedistrict.idactivitestructure.idactivite.idresultatattendu.idindicateur.idinterventionpnds.idcategorieintervention.idsousaxe.idsousaxe=:idsousaxe AND t.idtachedistrict.idactivitestructure.idstructure.idstructure=:idstructure AND t.idtachedistrict.idannee.idannee=:idannee AND t.idperioderattachement.idperiodederattachement=:idperiode ORDER BY t.idtachedistrict.idactivitestructure.idactivite.idresultatattendu.idindicateur.idinterventionpnds.code");
        query.setParameter("idsousaxe", idsousaxe).setParameter("idstructure", idstructure).setParameter("idannee", idannee).setParameter("idperiode", idperiode);
        return query.getResultList();
    }

}

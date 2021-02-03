/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TacheregionPeriode;
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
public class TacheregionPeriodeFacade extends AbstractFacade<TacheregionPeriode> implements TacheregionPeriodeFacadeLocal {

    @PersistenceContext(unitName = "PDSD_MONITORINGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TacheregionPeriodeFacade() {
        super(TacheregionPeriode.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(t.idtacheregionPeriode) FROM TacheregionPeriode t");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<TacheregionPeriode> findByIdtache(long idtacheregion) throws Exception {
        Query query = em.createQuery("SELECT t FROM TacheregionPeriode t WHERE t.idtacheregion.idtacheregion=:idtache");
        query.setParameter("idtache", idtacheregion);
        return query.getResultList();
    }

    @Override
    public List<TacheregionPeriode> findByIdindicateur(int idindicateur, int idregion, int idannee, int idperiodeRat) throws Exception {
        Query query = em.createQuery("SELECT t FROM TacheregionPeriode t WHERE t.idtacheregion.idactivitestructure.idactiviteRegion.idproblemeRegion.idindicateurRegion.idindicateur.idindicateur=:idindicateur AND t.idtacheregion.idactivitestructure.idactiviteRegion.idproblemeRegion.idindicateurRegion.idregion.idregion=:idregion AND t.idtacheregion.idannee.idannee=:idannee AND t.idperioderattachement.idperiodederattachement=:idperiodeRat");
        query.setParameter("idindicateur", idindicateur).setParameter("idregion", idregion).setParameter("idannee", idannee).setParameter("idperiodeRat", idperiodeRat);
        return query.getResultList();
    }

    /*@Override
    public List<TacheregionPeriode> findByIdsousaxe(int idsousaxe, int idstructure, int idannee, int idperiodeRat) throws Exception {
        Query query = em.createQuery("SELECT t FROM TacheregionPeriode t WHERE t.idtacheregion.idactivitestructure.idactiviteRegion.ididobjectifOpp.idintervention.idcategorieintervention.idsousaxe.idsousaxe=:idsousaxe AND t.idtacheregion.idactivitestructure.idstructure.idstructure=:idstructure AND t.idtacheregion.idannee.idannee=:idannee AND t.idperioderattachement.idperiodederattachement=:idperiodeRat");
        query.setParameter("idsousaxe", idstructure).setParameter("idstructure", idsousaxe).setParameter("idannee", idannee).setParameter("idperiodeRat", idperiodeRat);
        return query.getResultList();
    }*/

    @Override
    public List<TacheregionPeriode> findByIdsousaxe(int idsousaxe, int idstructure, int idannee, int idperiodeRat) throws Exception {
        Query query = em.createQuery("SELECT t FROM TacheregionPeriode t WHERE t.idperioderattachement.idperiodederattachement=:idperiodeRat AND t.idtacheregion.idannee.idannee=:idannee AND t.idtacheregion.idactivitestructure.idstructure.idstructure=:idstructure AND t.idtacheregion.idactivitestructure.idactiviteRegion.ididobjectifOpp.idintervention.idcategorieintervention.idsousaxe.idsousaxe=:idsousaxe");
        query.setParameter("idperiodeRat", idperiodeRat).setParameter("idannee", idannee).setParameter("idstructure", idstructure).setParameter("idsousaxe", idsousaxe);
        return query.getResultList();
    }

}

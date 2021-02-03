/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.CibleRegion;
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
public class CibleRegionFacade extends AbstractFacade<CibleRegion> implements CibleRegionFacadeLocal {

    @PersistenceContext(unitName = "PDSD_MONITORINGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CibleRegionFacade() {
        super(CibleRegion.class);
    }

    @Override
    public List<CibleRegion> findByRegionSousaxe(int idregion, int idsousaxe, int idannee) throws Exception {
        Query query = em.createQuery("SELECT c FROM CibleRegion c WHERE c.idregion.idregion=:idregion AND c.idindicateur.idinterventionpnds.idcategorieintervention.idsousaxe.idsousaxe=:idsousaxe AND c.idannee.idannee=:idannee ORDER BY c.idindicateur.idinterventionpnds.code");
        query.setParameter("idregion", idregion).setParameter("idsousaxe", idsousaxe).setParameter("idannee", idannee);
        return query.getResultList();
    }
    
    @Override
    public List<CibleRegion> findByRegionIdannee(int idregion, int idannee) throws Exception{
        Query query = em.createQuery("SELECT c FROM CibleRegion c WHERE c.idregion.idregion=:idregion AND c.idannee.idannee=:idannee ORDER BY c.idindicateur.idinterventionpnds.code");
        query.setParameter("idregion", idregion).setParameter("idannee", idannee);
        return query.getResultList();
    }

    @Override
    public CibleRegion find(int idindicateur, int idregion, int idannee) throws Exception {
        Query query = em.createQuery("SELECT c FROM CibleRegion c WHERE c.idindicateur.idindicateur=:idindicateur AND c.idregion.idregion=:idregion AND c.idannee.idannee=:idannee");
        query.setParameter("idindicateur", idindicateur).setParameter("idregion", idregion).setParameter("idannee", idannee);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (CibleRegion) list.get(0);
        }
        return null;
    }

}

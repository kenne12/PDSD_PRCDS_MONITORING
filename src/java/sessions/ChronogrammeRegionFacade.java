/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ActiviteRegion;
import entities.ChronogrammeRegion;
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
public class ChronogrammeRegionFacade extends AbstractFacade<ChronogrammeRegion> implements ChronogrammeRegionFacadeLocal {
    @PersistenceContext(unitName = "PDSD_MONITORINGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChronogrammeRegionFacade() {
        super(ChronogrammeRegion.class);
    }
    
    
    @Override
    public ChronogrammeRegion findByIdactiviteIdannee(long idactiviteregion, int idannee) throws Exception {
        Query query = em.createQuery("SELECT c FROM ChronogrammeRegion c WHERE c.idactiviteRegion.idactiviteRegion=:idactivite AND c.idannee.idannee=:idannee");
        query.setParameter("idactivite", idactiviteregion).setParameter("idannee", idannee);
        
        List list = query.getResultList();
        if(!list.isEmpty()){
            return (ChronogrammeRegion) list.get(0);
        }
        return null;
    }
    
}

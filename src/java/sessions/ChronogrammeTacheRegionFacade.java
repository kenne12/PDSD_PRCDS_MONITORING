/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ChronogrammeTacheRegion;
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
public class ChronogrammeTacheRegionFacade extends AbstractFacade<ChronogrammeTacheRegion> implements ChronogrammeTacheRegionFacadeLocal {

    @PersistenceContext(unitName = "PDSD_MONITORINGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChronogrammeTacheRegionFacade() {
        super(ChronogrammeTacheRegion.class);
    }
    
    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(c.idchronogrammeTacheRegion) FROM ChronogrammeTacheRegion c");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<ChronogrammeTacheRegion> findByIdtache(long idtacheregion) throws Exception {
        Query query = em.createQuery("SELECT c FROM ChronogrammeTacheRegion c WHERE c.idtacheregionPeriode.idtacheregionPeriode=:idtacheregion ORDER BY c.idtacheregionPeriode.idtacheregion.idactivitestructure.idactiviteRegion.idresultatattendu.idindicateur.idinterventionpnds.code");
        query.setParameter("idtacheregion", idtacheregion);
        return query.getResultList();
    }
    
    @Override
    public ChronogrammeTacheRegion findByIdtache(long idtacheregion , int idperiode) throws Exception {
        Query query = em.createQuery("SELECT c FROM ChronogrammeTacheRegion c WHERE c.idtacheregionPeriode.idtacheregionPeriode=:idtacheregion AND c.idperiode.idperiode=:idperiode ORDER BY c.idtacheregionPeriode.idtacheregion.idactivitestructure.idactiviteRegion.idresultatattendu.idindicateur.idinterventionpnds.code");
        query.setParameter("idtacheregion", idtacheregion).setParameter("idperiode", idperiode);
        List list = query.getResultList();
        if(!list.isEmpty()){
            return (ChronogrammeTacheRegion) list.get(0);
        }
        return null;
    }

}

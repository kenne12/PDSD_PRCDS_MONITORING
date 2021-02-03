/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ChronogrammeTacheDistrict;
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
public class ChronogrammeTacheDistrictFacade extends AbstractFacade<ChronogrammeTacheDistrict> implements ChronogrammeTacheDistrictFacadeLocal {

    @PersistenceContext(unitName = "PDSD_MONITORINGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChronogrammeTacheDistrictFacade() {
        super(ChronogrammeTacheDistrict.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(c.idchronogrammeTacheDistrict) FROM ChronogrammeTacheDistrict c");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public ChronogrammeTacheDistrict findByIdtache(long idtache, int idperiode) throws Exception {
        Query query = em.createQuery("SELECT c FROM ChronogrammeTacheDistrict c WHERE c.idtachedistrictPeriode.idtachedistrictPeriode=:idtache AND c.idperiode.idperiode=:idperiode");
        query.setParameter("idtache", idtache).setParameter("idperiode", idperiode);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (ChronogrammeTacheDistrict) list.get(0);
        }
        return null;
    }

    @Override
    public List<ChronogrammeTacheDistrict> findByIdtache(long idtache) throws Exception {
        Query query = em.createQuery("SELECT c FROM ChronogrammeTacheDistrict c WHERE c.idtachedistrictPeriode.idtachedistrictPeriode=:idtache");
        query.setParameter("idtache", idtache);
        return query.getResultList();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.CibleRegionValeur;
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
public class CibleRegionValeurFacade extends AbstractFacade<CibleRegionValeur> implements CibleRegionValeurFacadeLocal {

    @PersistenceContext(unitName = "PDSD_MONITORINGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CibleRegionValeurFacade() {
        super(CibleRegionValeur.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(c.idcibleRegionValeur) FROM CibleRegionValeur c");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public CibleRegionValeur findByIdcibleIdperiode(long idcibleregion, int idpreriodeRat) throws Exception {
        Query query = em.createQuery("SELECT c FROM CibleRegionValeur c WHERE c.idcibleRegion.idcibleRegion=:idcible AND c.idperioderattachement.idperiodederattachement=:idperiodeRat");
        query.setParameter("idcible", idcibleregion).setParameter("idperiodeRat", idpreriodeRat);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (CibleRegionValeur) list.get(0);
        }
        return null;
    }

    @Override
    public List<CibleRegionValeur> findByIdcible(long idcibleregion) throws Exception {
        Query query = em.createQuery("SELECT c FROM CibleRegionValeur c WHERE c.idcibleRegion.idcibleRegion=:idcible");
        query.setParameter("idcible", idcibleregion);
        return query.getResultList();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Ciblevaleur;
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
public class CiblevaleurFacade extends AbstractFacade<Ciblevaleur> implements CiblevaleurFacadeLocal {

    @PersistenceContext(unitName = "PDSD_MONITORINGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiblevaleurFacade() {
        super(Ciblevaleur.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(c.idciblevaleur) FROM Ciblevaleur c");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public Ciblevaleur findByIdcibleIdperiode(long idcible, int idperiode) throws Exception {
        Query query = em.createQuery("SELECT c FROM Ciblevaleur c WHERE c.idcible.idcible=:idcible AND c.idperioderattachement.idperiodederattachement=:idperiode");
        query.setParameter("idcible", idcible).setParameter("idperiode", idperiode);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (Ciblevaleur) list.get(0);
        }
        return null;
    }

    @Override
    public List<Ciblevaleur> findByIdcible(long idcible) throws Exception {
        Query query = em.createQuery("SELECT c FROM Ciblevaleur c WHERE c.idcible.idcible=:idcible ORDER BY c.idperioderattachement.code");
        query.setParameter("idcible", idcible);
        return query.getResultList();
    }

}

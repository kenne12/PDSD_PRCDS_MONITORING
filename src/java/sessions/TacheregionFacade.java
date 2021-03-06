/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Tacheregion;
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
public class TacheregionFacade extends AbstractFacade<Tacheregion> implements TacheregionFacadeLocal {

    @PersistenceContext(unitName = "PDSD_MONITORINGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TacheregionFacade() {
        super(Tacheregion.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(t.idtacheregion) FROM Tacheregion t");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Tacheregion> findByIdactivite(long idactivite, int idannee) throws Exception {
        Query query = em.createQuery("SELECT t FROM Tacheregion t WHERE t.idactivitestructure.idactiviteStructureRegion=:idactivite AND t.idannee.idannee=:idannee");
        query.setParameter("idactivite", idactivite).setParameter("idannee", idannee);
        return query.getResultList();
    }

}

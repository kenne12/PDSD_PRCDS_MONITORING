/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Tachedistrict;
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
public class TachedistrictFacade extends AbstractFacade<Tachedistrict> implements TachedistrictFacadeLocal {

    @PersistenceContext(unitName = "PDSD_MONITORINGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TachedistrictFacade() {
        super(Tachedistrict.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(t.idtachedistrict) FROM Tachedistrict t");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<Tachedistrict> findByIdactivite(long idactivitestructure) throws Exception {
        Query query = em.createQuery("SELECT t FROM Tachedistrict t WHERE t.idactivitestructure.idactiviteStructure=:activite");
        query.setParameter("activite", idactivitestructure);
        return query.getResultList();
    }

    @Override
    public List<Tachedistrict> findByIdactivite(long idactivitestructure, int idannee) throws Exception {
        Query query = em.createQuery("SELECT t FROM Tachedistrict t WHERE t.idactivitestructure.idactiviteStructure=:activite AND t.idannee.idannee=:idannee");
        query.setParameter("activite", idactivitestructure).setParameter("idannee", idannee);
        return query.getResultList();
    }

    @Override
    public List<Tachedistrict> findByIdindicateur(int idindicateur, int idannee) throws Exception {
        Query query = em.createQuery("SELECT t FROM Tachedistrict t WHERE t.idactivitestructure.idactivite.idprobleme.idindicateurDistrict.idindicateur.idindicateur=:idindicateur AND t.idannee.idannee=:idannee");
        query.setParameter("idindicateur", idindicateur).setParameter("idannee", idannee);
        return query.getResultList();
    }

}

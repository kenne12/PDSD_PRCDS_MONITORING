/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.UtilisateurRegion;
import java.util.ArrayList;
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
public class UtilisateurRegionFacade extends AbstractFacade<UtilisateurRegion> implements UtilisateurRegionFacadeLocal {

    @PersistenceContext(unitName = "PDSD_MONITORINGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurRegionFacade() {
        super(UtilisateurRegion.class);
    }

    @Override
    public Long nextId() {
        Query query = em.createQuery("SELECT MAX(u.idutilisateurRegion) FROM UtilisateurRegion u");
        Long resultat = (Long) query.getSingleResult();
        if (resultat != null) {
            return resultat + 1;
        } else {
            return 1L;
        }
    }

    @Override
    public List<UtilisateurRegion> findByUtilisateur(int utilisateur) {
        try {
            Query query = em.createQuery("SELECT u FROM UtilisateurRegion u WHERE u.idutilisateur.idutilisateur=:utilisateur ORDER BY u.idregion.nomFr");
            query.setParameter("utilisateur", utilisateur);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}

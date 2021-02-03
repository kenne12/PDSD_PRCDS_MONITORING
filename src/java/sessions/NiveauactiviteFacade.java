/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Niveauactivite;
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
public class NiveauactiviteFacade extends AbstractFacade<Niveauactivite> implements NiveauactiviteFacadeLocal {

    @PersistenceContext(unitName = "PDSD_MONITORINGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NiveauactiviteFacade() {
        super(Niveauactivite.class);
    }

    @Override
    public List<Niveauactivite> findAllRange() throws Exception{
        Query query = em.createQuery("SELECT n FROM Niveauactivite n ORDER BY n.numero");
        return query.getResultList();
    }
    
    @Override
    public List<Niveauactivite> findAllRangeExclusion() throws Exception{
        Query query = em.createQuery("SELECT n FROM Niveauactivite n WHERE n.numero!=0 ORDER BY n.numero");
        return query.getResultList();
    }

}

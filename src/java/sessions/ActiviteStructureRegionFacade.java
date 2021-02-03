/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ActiviteStructureRegion;
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
public class ActiviteStructureRegionFacade extends AbstractFacade<ActiviteStructureRegion> implements ActiviteStructureRegionFacadeLocal {

    @PersistenceContext(unitName = "PDSD_MONITORINGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActiviteStructureRegionFacade() {
        super(ActiviteStructureRegion.class);
    }

    @Override
    public ActiviteStructureRegion findByIdactiviteIdstructure(long idactiviteregion, int idstructure) throws Exception {
        Query query = em.createQuery("SELECT a FROM ActiviteStructureRegion a WHERE a.idactiviteRegion.idactiviteRegion=:idactivite AND a.idstructure.idstructure=:idstructure");
        query.setParameter("idactivite", idactiviteregion).setParameter("idstructure", idstructure);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (ActiviteStructureRegion) list.get(0);
        }
        return null;
    }

}

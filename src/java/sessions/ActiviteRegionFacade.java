/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ActiviteRegion;
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
public class ActiviteRegionFacade extends AbstractFacade<ActiviteRegion> implements ActiviteRegionFacadeLocal {

    @PersistenceContext(unitName = "PDSD_MONITORINGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActiviteRegionFacade() {
        super(ActiviteRegion.class);
    }

    @Override
    public Long nextId() throws Exception {
        Query query = em.createQuery("SELECT MAX(a.idacteurRegion) FROM ActeurRegion a");
        Long resultat = (Long) query.getSingleResult();
        if (resultat == null) {
            return 1L;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<ActiviteRegion> findByIdregionIdsousaxe(int idregion, int idsousaxe) throws Exception {
        Query query = em.createQuery("SELECT a FROM ActiviteRegion a WHERE a.idproblemeRegion.idindicateurRegion.idregion.idregion=:idregion AND a.idproblemeRegion.idindicateurRegion.idindicateur.idinterventionpnds.idcategorieintervention.idsousaxe.idsousaxe=:idsousaxe ORDER BY a.idproblemeRegion.idindicateurRegion.idindicateur.idinterventionpnds.code");
        query.setParameter("idregion", idregion).setParameter("idsousaxe", idsousaxe);
        return query.getResultList();
    }

}

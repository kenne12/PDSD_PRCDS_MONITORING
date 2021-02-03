/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.PartiehauteStructure;
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
public class PartiehauteStructureFacade extends AbstractFacade<PartiehauteStructure> implements PartiehauteStructureFacadeLocal {

    @PersistenceContext(unitName = "PDSD_MONITORINGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PartiehauteStructureFacade() {
        super(PartiehauteStructure.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(p.idpartiehauteStructure) FROM PartiehauteStructure p");
            List listObj = query.getResultList();
            if (!listObj.isEmpty()) {
                return ((Integer) listObj.get(0)) + 1;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    public PartiehauteStructure findByIdstructure(int idstructure) {
        try {
            Query query = em.createQuery("SELECT p FROM PartiehauteStructure p WHERE p.idstructure.idstructure=:idstructure");
            query.setParameter("idstructure", idstructure);
            List<PartiehauteStructure> partiehauteStructures = query.getResultList();
            if (!partiehauteStructures.isEmpty()) {
                return partiehauteStructures.get(0);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}

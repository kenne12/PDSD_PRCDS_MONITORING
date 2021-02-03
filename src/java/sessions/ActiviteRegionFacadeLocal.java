/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ActiviteRegion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface ActiviteRegionFacadeLocal {

    void create(ActiviteRegion activiteRegion);

    void edit(ActiviteRegion activiteRegion);

    void remove(ActiviteRegion activiteRegion);

    ActiviteRegion find(Object id);

    List<ActiviteRegion> findAll();

    List<ActiviteRegion> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<ActiviteRegion> findByIdregionIdsousaxe(int idregion, int idsousaxe) throws Exception;

}

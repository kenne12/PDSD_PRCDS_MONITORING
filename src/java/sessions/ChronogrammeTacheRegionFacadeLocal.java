/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ChronogrammeTacheRegion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface ChronogrammeTacheRegionFacadeLocal {

    void create(ChronogrammeTacheRegion chronogrammeTacheRegion);

    void edit(ChronogrammeTacheRegion chronogrammeTacheRegion);

    void remove(ChronogrammeTacheRegion chronogrammeTacheRegion);

    ChronogrammeTacheRegion find(Object id);

    List<ChronogrammeTacheRegion> findAll();

    List<ChronogrammeTacheRegion> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<ChronogrammeTacheRegion> findByIdtache(long idtacheregion) throws Exception;

    ChronogrammeTacheRegion findByIdtache(long idtacheregion, int idperiode) throws Exception;

}

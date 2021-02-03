/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.CibleRegion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface CibleRegionFacadeLocal {

    void create(CibleRegion cibleRegion);

    void edit(CibleRegion cibleRegion);

    void remove(CibleRegion cibleRegion);

    CibleRegion find(Object id);

    List<CibleRegion> findAll();

    List<CibleRegion> findRange(int[] range);

    int count();

    List<CibleRegion> findByRegionSousaxe(int idregion, int idsousaxe, int idannee) throws Exception;
    
    List<CibleRegion> findByRegionIdannee(int idregion, int idannee) throws Exception;

    CibleRegion find(int idindicateur, int idregion, int idannee) throws Exception;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ActiviteStructureRegion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface ActiviteStructureRegionFacadeLocal {

    void create(ActiviteStructureRegion activiteStructureRegion);

    void edit(ActiviteStructureRegion activiteStructureRegion);

    void remove(ActiviteStructureRegion activiteStructureRegion);

    ActiviteStructureRegion find(Object id);

    List<ActiviteStructureRegion> findAll();

    List<ActiviteStructureRegion> findRange(int[] range);

    int count();
    
    ActiviteStructureRegion findByIdactiviteIdstructure(long idactiviteregion , int idstructure)throws Exception;
    
}

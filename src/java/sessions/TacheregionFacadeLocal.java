/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Tacheregion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface TacheregionFacadeLocal {

    void create(Tacheregion tacheregion);

    void edit(Tacheregion tacheregion);

    void remove(Tacheregion tacheregion);

    Tacheregion find(Object id);

    List<Tacheregion> findAll();

    List<Tacheregion> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<Tacheregion> findByIdactivite(long idactivite, int idannee) throws Exception;

}

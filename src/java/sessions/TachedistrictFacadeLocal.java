/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Tachedistrict;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface TachedistrictFacadeLocal {

    void create(Tachedistrict tachedistrict);

    void edit(Tachedistrict tachedistrict);

    void remove(Tachedistrict tachedistrict);

    Tachedistrict find(Object id);

    List<Tachedistrict> findAll();

    List<Tachedistrict> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<Tachedistrict> findByIdactivite(long idactivitestructure) throws Exception;

    List<Tachedistrict> findByIdactivite(long idactivitestructure, int idannee) throws Exception;

    List<Tachedistrict> findByIdindicateur(int idindicateur, int idannee) throws Exception;

}

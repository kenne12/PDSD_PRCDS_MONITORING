/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ChronogrammeTacheDistrict;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface ChronogrammeTacheDistrictFacadeLocal {

    void create(ChronogrammeTacheDistrict chronogrammeTacheDistrict);

    void edit(ChronogrammeTacheDistrict chronogrammeTacheDistrict);

    void remove(ChronogrammeTacheDistrict chronogrammeTacheDistrict);

    ChronogrammeTacheDistrict find(Object id);

    List<ChronogrammeTacheDistrict> findAll();

    List<ChronogrammeTacheDistrict> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    ChronogrammeTacheDistrict findByIdtache(long idtache, int idperiode) throws Exception;

    List<ChronogrammeTacheDistrict> findByIdtache(long idtache) throws Exception;

}

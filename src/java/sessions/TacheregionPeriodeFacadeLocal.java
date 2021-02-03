/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TacheregionPeriode;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface TacheregionPeriodeFacadeLocal {

    void create(TacheregionPeriode tacheregionPeriode);

    void edit(TacheregionPeriode tacheregionPeriode);

    void remove(TacheregionPeriode tacheregionPeriode);

    TacheregionPeriode find(Object id);

    List<TacheregionPeriode> findAll();

    List<TacheregionPeriode> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<TacheregionPeriode> findByIdtache(long idtacheregion) throws Exception;

    List<TacheregionPeriode> findByIdindicateur(int idindicateur, int idregion, int idannee, int idperiodeRat) throws Exception;

    //List<TacheregionPeriode> findByIdsousaxe(int idsousaxe, int idstructure, int idannee, int idperiodeRat) throws Exception;

    List<TacheregionPeriode> findByIdsousaxe(int idperiodeRat, int idannee, int idstructure, int idsousaxe) throws Exception;

}

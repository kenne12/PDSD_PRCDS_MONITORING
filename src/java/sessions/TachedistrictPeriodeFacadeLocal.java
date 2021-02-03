/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TachedistrictPeriode;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface TachedistrictPeriodeFacadeLocal {

    void create(TachedistrictPeriode tachedistrictPeriode);

    void edit(TachedistrictPeriode tachedistrictPeriode);

    void remove(TachedistrictPeriode tachedistrictPeriode);

    TachedistrictPeriode find(Object id);

    List<TachedistrictPeriode> findAll();

    List<TachedistrictPeriode> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<TachedistrictPeriode> findByIdtache(long idtache) throws Exception;

    List<TachedistrictPeriode> findByIdindicateur(int idindicateur, int iddistrict, int idannee, int idperiode) throws Exception;

    List<TachedistrictPeriode> findByIdsousaxe(int idsousaxe, int idstructure, int idannee, int idperiode) throws Exception;

}

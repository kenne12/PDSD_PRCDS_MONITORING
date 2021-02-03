/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.CibleRegionValeur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface CibleRegionValeurFacadeLocal {

    void create(CibleRegionValeur cibleRegionValeur);

    void edit(CibleRegionValeur cibleRegionValeur);

    void remove(CibleRegionValeur cibleRegionValeur);

    CibleRegionValeur find(Object id);

    List<CibleRegionValeur> findAll();

    List<CibleRegionValeur> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    CibleRegionValeur findByIdcibleIdperiode(long idcibleregion, int idpreriodeRat) throws Exception;

    List<CibleRegionValeur> findByIdcible(long idcibleregion) throws Exception;

}

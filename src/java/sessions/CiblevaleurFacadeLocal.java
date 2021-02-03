/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Ciblevaleur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface CiblevaleurFacadeLocal {

    void create(Ciblevaleur ciblevaleur);

    void edit(Ciblevaleur ciblevaleur);

    void remove(Ciblevaleur ciblevaleur);

    Ciblevaleur find(Object id);

    List<Ciblevaleur> findAll();

    List<Ciblevaleur> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    Ciblevaleur findByIdcibleIdperiode(long idcible, int idperiode) throws Exception;

    List<Ciblevaleur> findByIdcible(long idcible) throws Exception;

}

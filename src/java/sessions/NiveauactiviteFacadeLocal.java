/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Niveauactivite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface NiveauactiviteFacadeLocal {

    void create(Niveauactivite niveauactivite);

    void edit(Niveauactivite niveauactivite);

    void remove(Niveauactivite niveauactivite);

    Niveauactivite find(Object id);

    List<Niveauactivite> findAll();

    List<Niveauactivite> findRange(int[] range);

    int count();

    List<Niveauactivite> findAllRange() throws Exception;
    
    List<Niveauactivite> findAllRangeExclusion() throws Exception;

}

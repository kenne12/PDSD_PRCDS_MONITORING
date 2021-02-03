/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.UtilisateurRegion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface UtilisateurRegionFacadeLocal {

    void create(UtilisateurRegion utilisateurRegion);

    void edit(UtilisateurRegion utilisateurRegion);

    void remove(UtilisateurRegion utilisateurRegion);

    UtilisateurRegion find(Object id);

    List<UtilisateurRegion> findAll();

    List<UtilisateurRegion> findRange(int[] range);

    int count();

    Long nextId();

    List<UtilisateurRegion> findByUtilisateur(int utilisateur);

}

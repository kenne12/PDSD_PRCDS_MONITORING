/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.IndicateurRegion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface IndicateurRegionFacadeLocal {

    void create(IndicateurRegion indicateurRegion);

    void edit(IndicateurRegion indicateurRegion);

    void remove(IndicateurRegion indicateurRegion);

    IndicateurRegion find(Object id);

    List<IndicateurRegion> findAll();

    List<IndicateurRegion> findRange(int[] range);

    int count();
    
}

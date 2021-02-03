/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.PartiehauteStructure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface PartiehauteStructureFacadeLocal {

    void create(PartiehauteStructure partiehauteStructure);

    void edit(PartiehauteStructure partiehauteStructure);

    void remove(PartiehauteStructure partiehauteStructure);

    PartiehauteStructure find(Object id);

    List<PartiehauteStructure> findAll();

    List<PartiehauteStructure> findRange(int[] range);

    int count();

    int nextId();

    PartiehauteStructure findByIdstructure(int idstructure);

}

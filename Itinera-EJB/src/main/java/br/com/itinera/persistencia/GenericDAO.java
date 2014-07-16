
package br.com.itinera.persistencia;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcelo
 */
public class GenericDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public void inserir(Object novo) throws EntityExistsException, Exception {
        em.persist(novo);
    }

    public void alterar(Object alterado) throws EntityExistsException, Exception {
        em.merge(alterado);
    }

    public void remover(Object remover) {
        Object objetoASerExcluida = em.merge(remover);
        em.remove(objetoASerExcluida);
    }
    
}

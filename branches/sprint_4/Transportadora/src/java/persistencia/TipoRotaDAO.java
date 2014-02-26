/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import modelo.TipoRota;

/**
 *
 * @author Cobra
 */
@Stateless
public class TipoRotaDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public void inserir(TipoRota novo) throws EntityExistsException {
        try{
        em.persist(novo);
        }catch(RollbackException ex){
            System.out.println("ERRO!");
        }
    }
    
    public void alterar(TipoRota alterado){
        em.merge(alterado);
    }
    
    public void remover(TipoRota remover){
        TipoRota categoriaASerExcluida = em.merge(remover);
        em.remove(categoriaASerExcluida);
    }
    
    public List<TipoRota> listar(){
        return em.createNamedQuery("TipoRota.findAll").getResultList();
    }
    
}

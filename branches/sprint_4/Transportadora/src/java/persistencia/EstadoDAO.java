/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Estado;

/**
 *
 * @author Michel
 * @author Leticia
 */
@Stateless
public class EstadoDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Estado> listar(){
        return em.createNamedQuery("Estado.findAll").getResultList();
    }
    
}

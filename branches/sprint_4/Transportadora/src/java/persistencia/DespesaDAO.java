
package persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import modelo.Despesa;

/**
 *
 * @author marcelo
 */
@Stateless
public class DespesaDAO {    
   
    @PersistenceContext
    private EntityManager em;
    
    public List<Despesa> listar(){
        return em.createNamedQuery("Despesa.findAll").getResultList();
    }
    
    public Despesa buscarPorId(int id) {
        try {
            return (Despesa) em.createNamedQuery("Despesa.findByIdDespesa").setParameter("idDespesa", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e1) {
            return null;
        }
    }
    
}

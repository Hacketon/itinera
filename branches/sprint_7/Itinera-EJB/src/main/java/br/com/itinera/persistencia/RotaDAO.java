package br.com.itinera.persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import br.com.itinera.modelo.Rota;

/**
 *
 * @author LeticiaSena
 */
@Stateless
public class RotaDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public void inserir(Rota novo) throws EntityExistsException {
        try{
            // Criar método que verifica se já existe um registro
            em.persist(novo);
        }catch(RollbackException ex){
            System.out.println("ERRO!");
        }
    }
    
    public void alterar(Rota alterado){
        em.merge(alterado);
    }
    
    public void remover(Rota remover){
        Rota excluir = em.merge(remover);
        em.remove(excluir);
    }
    
    public List<Rota> listar(){
        return em.createNamedQuery("Rota.findAll").getResultList();
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Usuario;

/**
 *
 * @author LeticiaSena
 */
@Stateless
public class UsuarioDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public void inserir(Usuario user){
        em.persist(user);
    }
    
    public void alterar(Usuario user){
        em.merge(user);
    }
    
    public void remover(Usuario user){
        Usuario excluir = em.merge(user);
        em.remove(excluir);
    }
    
    public List<Usuario> listar(){
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }
    
    public Usuario buscarPorLogin(String login){
       List<Usuario> users = em.createNamedQuery("Usuario.findByLogin").setParameter("login", login).getResultList();
       return (users.size() == 1)?users.get(0):null;   
    }
    
}

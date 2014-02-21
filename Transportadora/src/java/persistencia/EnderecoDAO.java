/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Endereco;

/**
 *
 * @author Michel
 */
@Stateless
public class EnderecoDAO {
    
    @PersistenceContext
    private EntityManager em;
    public void inserir(Endereco novo){
        em.persist(novo);
    }
    
    public void alterar(Endereco alterado){
        em.merge(alterado);
    }
    
    public void remover(Endereco remover){
        Endereco enderecoAExcluir = em.merge(remover);
        em.remove(enderecoAExcluir);
    }
    
    public List<Endereco> listar(){
        return em.createNamedQuery("Endereco.findAll").getResultList();
    }
    
    public List<Endereco> buscarPorMunicipio(String valorDeBusca){        
        return em.createQuery("SELECT e FROM Endereco e WHERE e.codigoMunicipio.nomeMunicipio = :municipioNome").setParameter("municipioNome", valorDeBusca).getResultList();
    }
    
    public List<Endereco> buscarPorBairro(String valorDeBusca){
        return em.createNamedQuery("Endereco.findByBairro").setParameter("bairro", valorDeBusca).getResultList();
    }
    
    public List<Endereco> buscarPorRua(String valorDeBusca){
        return em.createNamedQuery("Endereco.findByNomeLogradouro").setParameter("nomeLogradouro", valorDeBusca).getResultList();
    }
}

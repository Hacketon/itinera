/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itinera.persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.itinera.modelo.Veiculo;

/**
 *
 * @author LeticiaSena
 */
@Stateless
public class VeiculoDAO {
    
    @PersistenceContext
    private EntityManager em;
    public void inserir(Veiculo novo){
        em.persist(novo);
    }
    
    public void alterar(Veiculo alterado){
        em.merge(alterado);
    }
    
    public void remover(Veiculo remover){
        Veiculo veiculoAExcluir = em.merge(remover);
        em.remove(veiculoAExcluir);
    }
    
    public List<Veiculo> listar(){
        return em.createNamedQuery("Veiculo.findAll").getResultList();
    }
    
    public List<Veiculo> buscarPorPlaca(String placa) {
        return em.createNamedQuery("Veiculo.findByPlacaVeiculo").setParameter("placaVeiculo", "%"+placa+"%").getResultList();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.persistencia;

import br.com.itinera.modelo.Motorista;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lesena
 */
@Stateless
public class MotoristaDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Motorista> listar(){
        return em.createNamedQuery("Motorista.findAll").getResultList();
    }
    
    public void inserir(Motorista m){
        em.persist(m);
    }
    
    public void alterar(Motorista m){
        em.merge(m);
    }
    
    public void excluir(Motorista m){
        Motorista remover = em.merge(m);
        em.remove(remover);
    }
    
    public boolean verificarSeNovoMotoristaJaExisteComMesmoCpf(String cpf){
        if (!em.createNamedQuery("Motorista.findByCpf").setParameter("cpf", cpf)
                .getResultList().isEmpty()){
            return true;
        }
        return false;
    }
    
}

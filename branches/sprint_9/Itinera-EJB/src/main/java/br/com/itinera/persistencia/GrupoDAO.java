/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.persistencia;

import br.com.itinera.modelo.CategoriaVeiculo;
import br.com.itinera.modelo.Grupo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lesena
 */
@Stateless
public class GrupoDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Grupo> listar(){
        return em.createNamedQuery("Grupo.findAll").getResultList();
    }
    
    public void alterar(Grupo alterado){
        em.merge(alterado);
    }

}

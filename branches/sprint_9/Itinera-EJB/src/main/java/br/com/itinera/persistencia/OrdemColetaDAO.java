/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.persistencia;

import br.com.itinera.modelo.OrdemColeta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lesena
 */
@Stateless
public class OrdemColetaDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<OrdemColeta> listar(){
        return em.createNamedQuery("OrdemColeta.findAll").getResultList();
    }
    
}

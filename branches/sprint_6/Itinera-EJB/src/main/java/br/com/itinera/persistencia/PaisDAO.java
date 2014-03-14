/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.itinera.modelo.Pais;

/**
 *
 * @author lesena
 */
@Stateless
public class PaisDAO {
 
    @PersistenceContext
    private EntityManager em;
 
    public List<Pais> listar(){
        return em.createNamedQuery("Pais.findAll").getResultList();
    }
}

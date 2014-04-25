/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itinera.persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.itinera.modelo.Municipio;

/**
 *
 * @author Michel
 */
@Stateless
public class MunicipioDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Municipio> listar(){
        return em.createNamedQuery("Estado.findAll").getResultList();
    }
    
    public List<Municipio> buscarPorNome(String valorParaBusca){
        return em.createNamedQuery("Municipio.findByNomeMunicipio").setParameter("nomeMunicipio",valorParaBusca).getResultList();
    }
}

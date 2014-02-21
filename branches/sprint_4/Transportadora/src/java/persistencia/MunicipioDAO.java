/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Municipio;

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

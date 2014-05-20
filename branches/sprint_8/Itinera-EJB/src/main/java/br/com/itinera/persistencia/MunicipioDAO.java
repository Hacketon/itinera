package br.com.itinera.persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.itinera.modelo.Municipio;
import java.math.BigDecimal;

/**
 *
 * @author Michel
 */
@Stateless
public class MunicipioDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Municipio> listar(){
        return em.createNamedQuery("Municipio.findAll").getResultList();
    }
    
    public List<Municipio> buscarPorNome(String valorParaBusca){
        return em.createNamedQuery("Municipio.findByNomeMunicipio").setParameter("nomeMunicipio",valorParaBusca).getResultList();
    }
    
    public Municipio getById(BigDecimal id){
        return (Municipio) em.createNamedQuery("Municipio.findByIdMunicipio").setParameter("idMunicipio", id).getSingleResult();
    }
    
    public List<Municipio> getByLikeName(String valor){
        return em.createNamedQuery("Municipio.findByLikeName").setParameter("nomeMunicipio", valor).getResultList();
    }
}


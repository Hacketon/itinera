package br.com.itinera.persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.itinera.modelo.CategoriaVeiculo;
import java.math.BigDecimal;

/**
 *
 * @author LeticiaSena
 */
@Stateless
public class CategoriaVeiculoDAO {

    @PersistenceContext
    private EntityManager em;
    
    public void inserir(CategoriaVeiculo novo) throws EntityExistsException, Exception {
        em.persist(novo);
    }

    public void alterar(CategoriaVeiculo alterado) throws EntityExistsException, Exception {
        em.merge(alterado);
    }

    public void remover(CategoriaVeiculo remover) {
        CategoriaVeiculo categoriaASerExcluida = em.merge(remover);
        em.remove(categoriaASerExcluida);
    }

    public List<CategoriaVeiculo> listar() {
        return em.createNamedQuery("CategoriaVeiculo.findAll").getResultList();
    }
    
    public CategoriaVeiculo recuperarPorId(BigDecimal id){
        CategoriaVeiculo cv = (CategoriaVeiculo) em.createNamedQuery("CategoriaVeiculo.findByIdCategoriaVeiculo").setParameter("idCategoriaVeiculo", id).getSingleResult();
        return cv;
    }
}

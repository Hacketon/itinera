package br.com.itinera.persistencia;

import br.com.itinera.modelo.PlanoContas;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcelo
 */
@Stateless
public class PlanoContasDAO {

    @PersistenceContext
    private EntityManager em;
    
    public void inserir(PlanoContas novo) throws EntityExistsException, Exception {
        em.persist(novo);
    }

    public void alterar(PlanoContas alterado) throws EntityExistsException, Exception {
        em.merge(alterado);
    }

    public void remover(PlanoContas remover) {
        PlanoContas planoASerExcluida = em.merge(remover);
        em.remove(planoASerExcluida);
    }

    public List<PlanoContas> listar() {
        return em.createNamedQuery("PlanoContas.findAll").getResultList();
    }
    
    public PlanoContas recuperarPorId(BigDecimal id){
        PlanoContas cv = (PlanoContas) em.createNamedQuery("PlanoContas.findByIdPlanoContas").setParameter("idPlanoContas", id).getSingleResult();
        return cv;
    }

    public List<PlanoContas> buscarPorDescricao(String descricao) {
        return em.createNamedQuery("PlanoContas.findByDescricao").setParameter("descricao", "%"+descricao+"%").getResultList();
    }
}

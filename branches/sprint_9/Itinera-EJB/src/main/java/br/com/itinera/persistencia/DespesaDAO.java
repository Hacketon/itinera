package br.com.itinera.persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import br.com.itinera.modelo.Despesa;
import java.util.Date;

/**
 *
 * @author marcelo
 */
@Stateless
public class DespesaDAO extends GenericDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Despesa> listar() {
        return em.createNamedQuery("Despesa.findAll").getResultList();
    }

    public Despesa buscarPorId(int id) {
        try {
            return (Despesa) em.createNamedQuery("Despesa.findByIdDespesa").setParameter("idDespesa", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e1) {
            return null;
        }
    }

    public List<Despesa> buscarPorData(Date dtInicioFiltro, Date dtFimFiltro) {
        return (List<Despesa>) em.createQuery("SELECT d FROM Despesa d WHERE d.data BETWEEN :dtInicio AND :dtFim")
                .setParameter("dtInicio", dtInicioFiltro)
                .setParameter("dtFim", dtFimFiltro)
                .getResultList();
    }

}

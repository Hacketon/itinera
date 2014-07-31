
package br.com.itinera.persistencia;

import br.com.itinera.modelo.Motorista;
import br.com.itinera.modelo.OrdemColeta;
import java.util.Date;
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
    
    public List<OrdemColeta> buscarPorPeriodoENotaFiscal(Date inicio,Date fim,Integer nota){
        return em.createNamedQuery("OrdemColeta.findByPeriodoInicioFimNF")
               .setParameter("inicio", inicio)
               .setParameter("fim", fim)
               .setParameter("numeroNota", nota)
               .getResultList();
    }
    
    public List<OrdemColeta> buscarPorPeriodo(Date inicio,Date fim){
        return em.createNamedQuery("OrdemColeta.findByPeriodo")
               .setParameter("inicio", inicio)
               .setParameter("fim", fim)
               .getResultList();
    }
    
    public List<OrdemColeta> buscarPorPeriodoInicio(Date inicio){
        return em.createNamedQuery("OrdemColeta.findByPeriodoInicio")
               .setParameter("inicio", inicio)
               .getResultList();
    }
    
    public List<OrdemColeta> buscarPorPeriodoFim(Date fim){
        return em.createNamedQuery("OrdemColeta.findByPeriodoFim")
               .setParameter("fim", fim)
               .getResultList();
    }
    
    public List<OrdemColeta> buscarPorNotaFiscal(Integer nota){
        return em.createNamedQuery("OrdemColeta.findByNumeroNota")
               .setParameter("numeroNota", nota)
               .getResultList();
    }
    
    public List<OrdemColeta> buscarPorPeriodoInicioNotaFiscal(Date inicio,Integer nota){
        return em.createNamedQuery("OrdemColeta.findByPeriodoInicioNF")
               .setParameter("inicio", inicio)
               .setParameter("numeroNota", nota)
               .getResultList();
    }
    
    public List<OrdemColeta> buscarPorPeriodoFimNotaFiscal(Date fim, Integer nota){
        return em.createNamedQuery("OrdemColeta.findByPeriodoFimNF")
               .setParameter("fim", fim)
               .setParameter("numeroNota", nota)
               .getResultList();
    }

    public List<OrdemColeta> buscarPorMotorista(Motorista motorista, Date dtInicioFiltro, Date dtFimFiltro) {
        return em.createQuery("SELECT o FROM OrdemColeta o WHERE o.motoristaId = :motorista AND o.dataEmissaoNf BETWEEN :dtInicio AND :dtFim")
                .setParameter("motorista", motorista)
                .setParameter("dtInicio", dtInicioFiltro)
                .setParameter("dtFim", dtFimFiltro)
                .getResultList();
    }
    
    public void inserir(OrdemColeta ordem){
        em.persist(ordem);
    }
    
    public void alterar(OrdemColeta ordem){
        em.merge(ordem);
    }
    
    public void excluir(OrdemColeta ordem){
        OrdemColeta remover = em.merge(ordem);
        em.remove(remover);
    }
    
}

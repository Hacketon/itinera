/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Empresa;
import modelo.Endereco;
import modelo.PontoColeta;

/**
 *
 * @author Michel
 */
@Stateless
public class PontosColetaDAO {
    
    @PersistenceContext
    private EntityManager em;
    public void inserir(PontoColeta novo){
        em.persist(novo);
    }
    
    public void alterar(PontoColeta alterado){
        em.merge(alterado);
    }
    
    public void remover(PontoColeta remover){
        PontoColeta pontoCExcluir = em.merge(remover);
        em.remove(pontoCExcluir);
    }
    
    public List<PontoColeta> listar(){
        return em.createNamedQuery("PontoColeta.findAll").getResultList();
    }
    
    public List<PontoColeta> buscaPorEmpresaEOuEndereco(Empresa empresa,Endereco endereco){
        List<PontoColeta> result1 = new ArrayList<PontoColeta>();
        List<PontoColeta> result2 = new ArrayList<PontoColeta>();
        if(empresa != null){
        result1 = em.createNamedQuery("PontoColeta.findByEmpresa").setParameter("idEmpresa", empresa.getIdEmpresa()).getResultList();
        }
        if(endereco != null){
        result2 = em.createNamedQuery("PontoColeta.findByEndereco").setParameter("idEndereco", endereco.getIdEndereco()).getResultList();
        }
        if(result1.isEmpty() && result2.isEmpty()) return null;
        else if(result1.isEmpty() && (!result2.isEmpty())) return result2;
        else if((!result1.isEmpty()) && result2.isEmpty()) return result1;
        else{
            for(PontoColeta p: result2){
                result1.add(p);
            }
            return result1;
        }
        
        
    }
    
}

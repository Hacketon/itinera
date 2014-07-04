package br.com.itinera.fachada;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import br.com.itinera.modelo.PlanoContas;
import br.com.itinera.persistencia.PlanoContasDAO;
import java.math.BigDecimal;

/**
 *
 * @author marcelo
 */
@Stateless
public class PlanoContasFachada {
    
    @EJB
    private PlanoContasDAO dao;
    
    public void inserir(PlanoContas planoContas) throws EntityExistsException, Exception{
        dao.inserir(planoContas);
    }
    
    public void alterar(PlanoContas planoContas) throws EntityExistsException, Exception{
        dao.alterar(planoContas);
    }
    
    public void remover(PlanoContas planoContas){
        dao.remover(planoContas);
    }
    
    public List<PlanoContas> listar(){
        return dao.listar();
    }
    
    public Integer contagem(){
        return dao.listar().size();            
    }
    
    public PlanoContas recuperarPorId(BigDecimal id){
        return dao.recuperarPorId(id);
    }
}
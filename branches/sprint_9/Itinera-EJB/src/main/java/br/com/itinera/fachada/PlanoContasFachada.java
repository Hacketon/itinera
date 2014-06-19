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
    private PlanoContasDAO categoriaVeiculoDAO;
    
    public void inserir(PlanoContas categoria) throws EntityExistsException, Exception{
        categoriaVeiculoDAO.inserir(categoria);
    }
    
    public void alterar(PlanoContas categoria) throws EntityExistsException, Exception{
        categoriaVeiculoDAO.alterar(categoria);
    }
    
    public void remover(PlanoContas categoria){
        categoriaVeiculoDAO.remover(categoria);
    }
    
    public List<PlanoContas> listar(){
        return categoriaVeiculoDAO.listar();
    }
    
    public Integer contagem(){
        return categoriaVeiculoDAO.listar().size();            
    }
    
    public PlanoContas recuperarPorId(BigDecimal id){
        return categoriaVeiculoDAO.recuperarPorId(id);
    }
}
package br.com.itinera.fachada;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import br.com.itinera.modelo.CategoriaVeiculo;
import br.com.itinera.persistencia.CategoriaVeiculoDAO;

/**
 *
 * @author LeticiaSena
 */
@Stateless
public class CategoriaVeiculoFachada {
    @EJB
    private CategoriaVeiculoDAO categoriaVeiculoDAO;
    
    public void inserir(CategoriaVeiculo categoria) throws EntityExistsException, Exception{
        categoriaVeiculoDAO.inserir(categoria);
    }
    
    public void alterar(CategoriaVeiculo categoria) throws EntityExistsException, Exception{
        categoriaVeiculoDAO.alterar(categoria);
    }
    
    public void remover(CategoriaVeiculo categoria){
        categoriaVeiculoDAO.remover(categoria);
    }
    
    public List<CategoriaVeiculo> listar(){
        return categoriaVeiculoDAO.listar();
    }
    
    public Integer contagem(){
        return categoriaVeiculoDAO.listar().size();
                
    }
}
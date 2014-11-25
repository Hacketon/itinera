
package br.com.itinera.fachada;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.itinera.modelo.Veiculo;
import br.com.itinera.persistencia.VeiculoDAO;

/**
 *
 * @author LeticiaSena
 */
@Stateless
public class VeiculoFachada {
  
    @EJB
    private VeiculoDAO veiculoDAO;
    
    public void inserir(Veiculo veiculo){
        veiculoDAO.inserir(veiculo);
    }
    
    public void alterar(Veiculo veiculo){
        veiculoDAO.alterar(veiculo);
    }
    
    public void remover(Veiculo veiculo){
        veiculoDAO.remover(veiculo);
    }
    
    public List<Veiculo> listar(){
        return veiculoDAO.listar();
    }
    
    public Integer contagem(){
        return veiculoDAO.listar().size();
    }
    
    public List<Veiculo> buscarPorPlaca(String placa) {
        return veiculoDAO.buscarPorPlaca(placa);
    }
}

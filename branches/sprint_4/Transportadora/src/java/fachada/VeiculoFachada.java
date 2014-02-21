/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Veiculo;
import persistencia.VeiculoDAO;

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
}

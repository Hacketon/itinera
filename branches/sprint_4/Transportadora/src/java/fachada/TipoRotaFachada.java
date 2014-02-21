/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

//para remover esse em amarelo (amarelos são warning) faça assim.Pronto.Verifique se a DAO não está assim.
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.TipoRota;
import persistencia.TipoRotaDAO;

/**
 *
 * @author Cobra
 */
@Stateless
public class TipoRotaFachada {
    @EJB
    //é atributo de classe, com o nome em minusculo.
    private TipoRotaDAO tipoRotaDAO;
    
    //troque as entidades também. 
    public void inserir(TipoRota tipo){
        tipoRotaDAO.inserir(tipo);
    }
    
    public void alterar(TipoRota categoria){
        tipoRotaDAO.alterar(categoria);
    }
    
    public void remover(TipoRota categoria){
        tipoRotaDAO.remover(categoria);
    }
    
    public List<TipoRota> listar(){
        return tipoRotaDAO.listar();
    }
    
    public Integer contagem(){
        return tipoRotaDAO.listar().size();
                
    }
}

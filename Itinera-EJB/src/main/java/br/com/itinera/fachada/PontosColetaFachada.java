/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itinera.fachada;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.itinera.modelo.PontoColeta;
import br.com.itinera.persistencia.PontosColetaDAO;

/**
 *
 * @author Michel
 */
@Stateless
public class PontosColetaFachada {
  
    @EJB
    private PontosColetaDAO pontosColetaDAO;
    
    public void inserir(PontoColeta pontosColeta){
        pontosColetaDAO.inserir(pontosColeta);
    }
    
    public void alterar(PontoColeta pontosColeta){
        pontosColetaDAO.alterar(pontosColeta);
    }
    
    public void remover(PontoColeta pontosColeta){
        pontosColetaDAO.remover(pontosColeta);
    }
    
    public List<PontoColeta> listar(){
        return pontosColetaDAO.listar();
    }
    
    public Integer contagem(){
        return pontosColetaDAO.listar().size();
    }
}

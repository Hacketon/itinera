/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Endereco;
import persistencia.EnderecoDAO;

/**
 *
 * @author Michel
 */
@Stateless
public class EnderecoFachada {
  
    @EJB
    private EnderecoDAO enderecoDAO;
    
    public void inserir(Endereco endereco){
        enderecoDAO.inserir(endereco);
    }
    
    public void alterar(Endereco endereco){
        enderecoDAO.alterar(endereco);
    }
    
    public void remover(Endereco endereco){
        enderecoDAO.remover(endereco);
    }
    
    public List<Endereco> listar(){
        return enderecoDAO.listar();
    }
}

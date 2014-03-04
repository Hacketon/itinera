/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itinera.fachada;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.itinera.modelo.Endereco;
import br.com.itinera.persistencia.EnderecoDAO;

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

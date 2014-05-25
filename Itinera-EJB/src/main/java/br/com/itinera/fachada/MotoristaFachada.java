/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.fachada;

import br.com.itinera.modelo.Motorista;
import br.com.itinera.persistencia.MotoristaDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author lesena
 */
@Stateless
public class MotoristaFachada {
   @EJB
    private MotoristaDAO dao;

    
    public List<Motorista> listarTodos(){
        return dao.listar();
    }
    
    public void inserir(Motorista novo){
        dao.inserir(novo);
    }
    
    public void alterar(Motorista alt){
        dao.alterar(alt);
    }
    
    public void excluir(Motorista rem){
        dao.equals(rem);
    }

}

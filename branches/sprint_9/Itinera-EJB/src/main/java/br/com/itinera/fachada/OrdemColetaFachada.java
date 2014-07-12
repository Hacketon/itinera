/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.fachada;

import br.com.itinera.modelo.OrdemColeta;
import br.com.itinera.persistencia.OrdemColetaDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author lesena
 */
@Stateless
public class OrdemColetaFachada {
    @EJB
    private OrdemColetaDAO dao;
    
    public List<OrdemColeta> listar(){
        return dao.listar();
    }
}

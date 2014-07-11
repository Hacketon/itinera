/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.fachada;

import br.com.itinera.modelo.Grupo;
import br.com.itinera.modelo.Usuario;
import br.com.itinera.persistencia.GrupoDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author lesena
 */
@Stateless
public class GrupoFachada {
    @EJB
    private GrupoDAO dao;
    
    public List<Grupo> listar(){
        return dao.listar();
    }
    
    public void removeUsuarioGrupo(Usuario user,Grupo grupo){
        for(Usuario u: grupo.getUsuarioList()){
            if(u.getLogin().equals(user.getLogin())){
                grupo.getUsuarioList().remove(u);
                break;
            }
        }
        dao.alterar(grupo);
    }
    
    public void adicionaUsuarioGrupo(Usuario user,Grupo grupo){
        boolean existe = false;
        for(Usuario u: grupo.getUsuarioList()){
            if(u.getLogin().equals(user.getLogin())){
                existe = true;
                break;
            }
        }
        if(!existe){
            grupo.getUsuarioList().add(user);
            dao.alterar(grupo);
        }
            
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Usuario;
import persistencia.UsuarioDAO;

/**
 *
 * @author LeticiaSena
 */
@Stateless
public class UsuarioFachada {
    
    @EJB 
    private UsuarioDAO usuarioDao;
    
    public void inserir(Usuario user){
        usuarioDao.inserir(user);
    }
    
    public void alterar(Usuario user){
        usuarioDao.alterar(user);
    }
    
    public void remover(Usuario user){
        usuarioDao.remover(user);
    }
    
    public List<Usuario> listar(){
        return usuarioDao.listar();
    }
    
    public Usuario buscarPorLogin(String login){
        return usuarioDao.buscarPorLogin(login);
    }
    
    public Integer contagem(){
        return usuarioDao.listar().size();
    }
    
}

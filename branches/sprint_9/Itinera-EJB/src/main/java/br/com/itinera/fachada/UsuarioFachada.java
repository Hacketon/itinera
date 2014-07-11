/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itinera.fachada;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.itinera.modelo.Usuario;
import br.com.itinera.persistencia.UsuarioDAO;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author LeticiaSena
 */
@Stateless
public class UsuarioFachada {
    
    @EJB 
    private UsuarioDAO usuarioDao;
    
    public void inserir(Usuario user,String primeiraSenha,String segundaSenha) throws Exception{
        validaSenha(primeiraSenha,segundaSenha);
        user.setSenha(criptografar(primeiraSenha));
        usuarioDao.inserir(user);
    }
    
     public void alterar(Usuario user){
        usuarioDao.alterar(user);
    }
    
    public void alterar(Usuario user,String primeiraSenha,String segundaSenha) throws NoSuchAlgorithmException, Exception{
        validaSenha(primeiraSenha,segundaSenha);
        user.setSenha(criptografar(primeiraSenha));
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
    
    public Usuario buscarPorID(BigDecimal id){
        return usuarioDao.buscarPorId(id);
    }
    
    public Integer contagem(){
        return usuarioDao.listar().size();
    }
    
    public void validaSenha(String primeiraSenha,String segundaSenha) throws Exception{
        if(primeiraSenha.compareTo(segundaSenha) == 0){
            if(!(primeiraSenha.length() >=6)){
              throw new Exception("A senha inserida tem um tamanho menor que 6 caracteres.");
            }
        }
        else{
            throw new Exception("A senha de confirmação não confere com a senha inserida.");
        }
    }

    private String criptografar(String senha) throws NoSuchAlgorithmException {
        MessageDigest mda = MessageDigest.getInstance("SHA-256");
        byte[] hashPassword = mda.digest(senha.getBytes());

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < hashPassword.length; i++) {
            sb.append(Integer.toString((hashPassword[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    
}

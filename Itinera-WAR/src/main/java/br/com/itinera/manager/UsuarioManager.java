/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itinera.manager;

import br.com.itinera.fachada.UsuarioFachada;
import br.com.itinera.ferramentas.Encrypt;
import br.com.itinera.ferramentas.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.com.itinera.modelo.Usuario;
import org.primefaces.context.RequestContext;

/**
 *
 * @author LeticiaSena
 */
@ManagedBean(name = "usuariosManager")
@SessionScoped
public class UsuarioManager implements Serializable{
    private Usuario usuario;
    private String primeiraSenha;
    private String segundaSenha;
    private String elemento = null;
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    @EJB
    private UsuarioFachada fachada;
    
    
    
    //Parte 1 - GETs e SETs
    public String getPrimeiraSenha() {
        return primeiraSenha;
    }

    public void setPrimeiraSenha(String primeiraSenha) {
        this.primeiraSenha = primeiraSenha;
    }

    public String getSegundaSenha() {
        return segundaSenha;
    }

    public void setSegundaSenha(String segundaSenha) {
        this.segundaSenha = segundaSenha;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
    public Integer contagem(){
        return fachada.contagem();
    }
    //Parte 2 - CRUD
    
    public String inserir(){
        if (validarSenha() && criptografar()) {
            usuario.setLogin(usuario.getLogin().toUpperCase());
            fachada.inserir(usuario);
            recarregarUsuarios();
            Mensagem.mostrarMensagemSucesso("Sucesso!", "Usuário inserido com sucesso.");
            return montarPaginaParaListarUsuarios();
        }
        else
            return "";
        
    }
    
    public String alterar(){
            fachada.alterar(usuario);
            Mensagem.mostrarMensagemSucesso("Sucesso!", "Usuário alterado com sucesso!");
            recarregarUsuarios();
            return montarPaginaParaListarUsuarios();
    }
    
    public void excluir(){
        fachada.remover(usuario);
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("exclui.hide()");
        Mensagem.mostrarMensagemSucesso("Sucesso!", "Usuário excluído com sucesso!");
        usuario = new Usuario();
        this.recarregarUsuarios();
    }
    
    public String alterarSenha(){ 
         if (validarSenha() && criptografar()) {
             elemento = null;
             fachada.alterar(usuario);
             Mensagem.mostrarMensagemSucesso("Sucesso!", "A senha do usuário foi alterada com sucesso!");
             return montarPaginaParaListarUsuarios();
         }
         else
             return "";
    }
    
    //Parte 3 - Chamadas de Tela
    public String montarPaginaParaListarUsuarios(){
        recarregarUsuarios();
        return "/componentes/usuario/ListarUsuarios";
    }
    
    public String montarPaginaParaCadastrarUsuarios(){
        this.usuario = new Usuario();
        this.usuario.setAtivo(true);
        return "/componentes/usuario/CadastrarUsuario";
    }
    
    public String montarPaginaParaAlterarUsuarios(){ 
        return "/componentes/usuario/AlterarUsuario";
    }
    
    public String montarPaginaParaAlterarSenha(){ 
        return "/componentes/usuario/AlterarSenha";
    }
    
    //Parte 4 - Métodos diversos
    public void recarregarUsuarios(){
        this.usuarios = fachada.listar();
    }
    

    private boolean validarSenha() {
        boolean senhaValida = false;
        if(this.primeiraSenha.compareTo(segundaSenha) == 0){
            if(this.primeiraSenha.length() >=6){
                senhaValida = true;
            }
            else
                Mensagem.mostrarMensagemErro("Erro!","A senha inserida tem um tamanho menor que 6 caracteres.");
        }
        else{
            
            Mensagem.mostrarMensagemErro("Erro!","A senha de confirmação não confere com a senha inserida.");
        }
        return senhaValida;
    }

    private boolean criptografar() {
        Encrypt encriptador = new Encrypt(primeiraSenha,"MD5","UTF-8");
        boolean deuCerto = false;
        if(encriptador.hasError()){
            Mensagem.mostrarMensagemErro("Erro:","Erro ao encriptar senha:"+encriptador.getErrorMsg());
        }
        else{
            usuario.setSenha(encriptador.getResult());
            deuCerto = true;
        }
        return deuCerto;
    }
            
}

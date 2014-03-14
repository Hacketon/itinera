/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itinera.manager;

import br.com.itinera.fachada.UsuarioFachada;
import br.com.itinera.ferramentas.Encrypt;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import br.com.itinera.modelo.Usuario;

/**
 *
 * @author LeticiaSena
 */
@ManagedBean(name = "loginManager")
@SessionScoped
public class LoginManager implements Serializable{
    private String login;
    private String senha;
    private Usuario usuario;
    private Date dataHoraEntrou;
    private boolean logado = false;
    private String mensagem;
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
   @EJB
   private UsuarioFachada fachada;
   
   public void init(){
       login = "";
       senha = "";
   }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
   
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataHoraEntrou() {
        return dataHoraEntrou;
    }

    public void setDataHoraEntrou(Date dataHoraEntrou) {
        this.dataHoraEntrou = dataHoraEntrou;
    }
   
    public String abrir(){
        return (this.logado)?"/layout/logado/index.xhtml":"/layout/logoff/index.xhtml";
    }
    
    public String entrar(){
        
        if(this.login.isEmpty()){
            mensagem = "Por favor, insira um login para entrar.";
            return "";
        }
        else{
            if(this.senha.isEmpty()){
                mensagem="Por favor, insira uma senha para entrar.";
                return "";
            }
            else{
                //Recuperando usuário
                Usuario usuarioRecuperado = fachada.buscarPorLogin(login.toUpperCase());
                if(usuarioRecuperado == null){
                    mensagem="Login para usuário inválido.";
                    return "";
                }else{
                    if(!usuarioRecuperado.getAtivo()){
                        mensagem = "Usuário inativo";
                        return "";
                    }
                    else{
                        criptografar();
                        if(!(senha.compareTo(usuarioRecuperado.getSenha())==0)){
                            mensagem = "Senha para usuário incorreta.";
                            return "";
                        }else{
                            usuario = usuarioRecuperado;
                            this.logado = true;
                            this.dataHoraEntrou = new Date();
                            return "/layout/logado/index";
                        }
                    }
                }
                
                    
            }
        }
    }
    
    public String sair(){
        this.logado = false;
        usuario = null;
        HttpSession sessao = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        sessao.invalidate();
        return "/index.xhtml?faces-redirect=true";
    }
    
     private boolean criptografar() {
        Encrypt encriptador = new Encrypt(senha,"MD5","UTF-8");
        boolean deuCerto = false;
        if(encriptador.hasError()){
            mensagem = "Erro ao criptografar.";
        }
        else{
            senha = encriptador.getResult();
            deuCerto = true;
        }
        return deuCerto;
    }
    

 
}

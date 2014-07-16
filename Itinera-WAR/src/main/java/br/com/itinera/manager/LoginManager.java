package br.com.itinera.manager;

import br.com.itinera.fachada.UsuarioFachada;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import br.com.itinera.modelo.Usuario;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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
    private boolean grupoAdministrador;
    private boolean grupoSecretaria;
    private boolean grupoGestor;
    
    @EJB
    private UsuarioFachada fachada;
    
    public void init(){
        this.login = "";
        this.senha = "";
        this.grupoAdministrador = false;
        this.grupoGestor = false;
        this.grupoSecretaria = false;
    }
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
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

    public boolean isGrupoAdministrador() {
        return grupoAdministrador;
    }

    public void setGrupoAdministrador(boolean grupoAdministrador) {
        this.grupoAdministrador = grupoAdministrador;
    }

    public boolean isGrupoSecretaria() {
        return grupoSecretaria;
    }

    public void setGrupoSecretaria(boolean grupoSecretaria) {
        this.grupoSecretaria = grupoSecretaria;
    }

    public boolean isGrupoGestor() {
        return grupoGestor;
    }

    public void setGrupoGestor(boolean grupoGestor) {
        this.grupoGestor = grupoGestor;
    }
    
   
    public String abrir(){
        return (this.logado)?"/layout/logado/index.xhtml":"/layout/logoff/index.xhtml";
    }
    
    //Metodo para login
    public String fazerLogin(){
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
                FacesContext contexto = FacesContext.getCurrentInstance();
                HttpServletRequest request = (HttpServletRequest)contexto.getExternalContext().getRequest();
                try{
                    login = login.toUpperCase();
                    request.login(login, senha);
                    this.usuario = fachada.buscarPorLogin(login);
                    this.logado = true;
                    this.dataHoraEntrou = new Date();
                    this.mensagem = "";
                    System.out.println("LOGOU");
                    if(request.isUserInRole("Administrador")){
                        System.out.println("Administrador");
                        grupoAdministrador=true;
                        grupoGestor = true;
                        grupoSecretaria = true;
                    }
                    else if(request.isUserInRole("Gestor")){
                        grupoGestor=true;
                        System.out.println("Gestor");
                    }
                    else if(request.isUserInRole("Secretaria")){
                        System.out.println("Secretária");
                        grupoSecretaria=true;
                    }
                    else if(request.isUserInRole("Motorista"))System.out.println("Pertence à regra Motorista");
                    
                    return "/layout/logado/index.xhtml";
                }catch(Exception e){
                    mensagem = "Usuário ou Senha inválida.";
                    return null;
                }
                
            }
        }
    }
    
    //Efetuar logout
    public String fazerLogout(){
        this.logado = false;
        usuario = null;
        this.init();
        FacesContext contexto = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)contexto.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            System.out.println(ex.getCause());
        }
        return "/index.xhtml?faces-redirect=true";
    }
    
    

 
}

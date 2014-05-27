package br.com.itinera.ferramentas;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author lesena
 * @description Serviço de menssageria para JSF
 */
public class Mensagem {

    public static String duplicidade = "DUPLICIDADE";
    public static String erro = "ERRO";
    public static String sucesso = "SUCESSO";
    public static String erroCritico = "Problema ao finalizar registro. ";
    
    public static void mostrarMensagem(String titulo,String mensagem){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,titulo,mensagem);
        exibeMensagem(msg);
    }
    
    public static void mostrarMensagem(String titulo,String mensagem,String objeto){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,titulo,mensagem);
        exibeMensagem(msg);
    }
   
    public static void mostrarMensagemErro(String titulo,String mensagem){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,titulo,mensagem);
        exibeMensagem(msg);
    }
    
    public static void mostrarMensagemErro(String titulo,String mensagem,String objeto){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,titulo,mensagem);
        exibeMensagem(msg);
    }
    
    public static void mostrarMensagemSucesso(String titulo,String mensagem){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,mensagem);
        exibeMensagem(msg);
    }
    
    public static void mostrarMensagemSucesso(String titulo,String mensagem,String Objeto){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,mensagem);
        exibeMensagem(msg);
    }
    private static void exibeMensagem(FacesMessage fm){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.getExternalContext().getFlash().setRedirect(true);
        context.addMessage(null, fm);
    }
    
    private static void exibeMensagem(String objeto,FacesMessage fm){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.getExternalContext().getFlash().setRedirect(true);
        context.addMessage(objeto, fm);
    }
    
}

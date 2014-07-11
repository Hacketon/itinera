/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.manager;

import br.com.itinera.fachada.GrupoFachada;
import br.com.itinera.fachada.UsuarioFachada;
import br.com.itinera.ferramentas.Mensagem;
import br.com.itinera.modelo.Grupo;
import br.com.itinera.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author lesena
 */
@ManagedBean(name = "grupoManager")
@SessionScoped
public class GrupoManager implements Serializable{
    @EJB 
    private GrupoFachada fachada;
    @EJB
    private UsuarioFachada fachadaUsuario;
    private List<Grupo> grupos;
    private Usuario usuario;
    private Grupo grupo;
    private List<Usuario> usuariosSelecionados, usuariosDoGrupo;

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Usuario> getUsuariosSelecionados() {
        return usuariosSelecionados;
    }

    public void setUsuariosSelecionados(List<Usuario> usuariosSelecionados) {
        this.usuariosSelecionados = usuariosSelecionados;
    }

    public List<Usuario> getUsuariosDoGrupo() {
        return usuariosDoGrupo;
    }

    public void setUsuariosDoGrupo(List<Usuario> usuariosDoGrupo) {
        this.usuariosDoGrupo = usuariosDoGrupo;
    }
  
    public String montarPaginaParaListarGrupos(){
        this.grupos = fachada.listar();
        return "/componentes/grupo/ListarGrupo";
    }
    
    public String montarPaginaParaAdicionarUsuarioAoGrupo(){
        usuariosSelecionados = null;
        usuariosDoGrupo = fachadaUsuario.listar();
        for(Usuario u:this.grupo.getUsuarioList()){
            for(Usuario x:usuariosDoGrupo){
                if(u.getLogin().equals(x.getLogin())){
                    usuariosDoGrupo.remove(x);
                    break;
                }
            }
        }
        return "/componentes/grupo/AdicionarUsuario";
    }
    
    public void removeDoGrupo(){
        fachada.removeUsuarioGrupo(this.usuario,this.grupo);
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("exclui.hide()");
        Mensagem.mostrarMensagemSucesso("Sucesso!","Usuário removido do grupo "+this.grupo.getGrupoNome() + " com sucesso.");
    }
    
    public String adicionarAoGrupo(){
        for(Usuario u:this.usuariosSelecionados){
            fachada.adicionaUsuarioGrupo(u, grupo);
        }
        Mensagem.mostrarMensagemSucesso("Sucesso!","Usuário inseridos ao grupo "+this.grupo.getGrupoNome() + " com sucesso.");
        return montarPaginaParaListarGrupos();
    }
}

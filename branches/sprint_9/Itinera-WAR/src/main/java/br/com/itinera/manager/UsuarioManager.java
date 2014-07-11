/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itinera.manager;

import br.com.itinera.fachada.GrupoFachada;
import br.com.itinera.fachada.UsuarioFachada;
import br.com.itinera.ferramentas.Mensagem;
import br.com.itinera.modelo.Grupo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.com.itinera.modelo.Usuario;
import javax.faces.model.SelectItem;
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
    private List<Grupo> grupoSelecionado;
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    @EJB
    private UsuarioFachada fachada;
    @EJB
    private GrupoFachada fachadaGrupo;
    
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

    public List<Grupo> getGrupoSelecionado() {
        return grupoSelecionado;
    }

    public void setGrupoSelecionado(List<Grupo> grupoSelecionado) {
        this.grupoSelecionado = grupoSelecionado;
    }
    
    //Parte 2 - CRUD
    public String inserir(){
        try{
            usuario.setLogin(usuario.getLogin().toUpperCase());
            fachada.inserir(usuario,primeiraSenha,segundaSenha);    
            for(Grupo g:grupoSelecionado){
                fachadaGrupo.adicionaUsuarioGrupo(usuario, g);
            }
            recarregarUsuarios();
            Mensagem.mostrarMensagemSucesso("Sucesso!", "Usuário inserido com sucesso.");
            return montarPaginaParaListarUsuarios();
        }catch(Exception e){
            Mensagem.mostrarMensagemErro("Erro!", e.getMessage());
        }
        return null;
    }
    
    public String alterar(){
        fachada.alterar(usuario);
        //verifique se teve alteração de permissão
        boolean igual = true;
        for(Grupo g:usuario.getGrupoList()){
            igual = false;
            for(Grupo h:grupoSelecionado){
                if(g.getGrupoId() == h.getGrupoId()){
                    igual = true;
                }
            }
            if(!igual){
                break;
            }
        }
        if(!igual || (usuario.getGrupoList().size() == 0 && grupoSelecionado.size() > 0)){
            //remova todas as permissões de grupo já dada
            for(Grupo g:usuario.getGrupoList()){
                fachadaGrupo.removeUsuarioGrupo(usuario, g);
            }
            //adicione todas as permissões selecionadas
            for(Grupo g:grupoSelecionado){
                fachadaGrupo.adicionaUsuarioGrupo(usuario, g);
            }
        }
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
        this.grupoSelecionado.clear();
        this.recarregarUsuarios();
    }
    
    public String alterarSenha(){ 
        try{
            fachada.alterar(usuario,this.primeiraSenha,this.segundaSenha);
            Mensagem.mostrarMensagemSucesso("Sucesso!", "A senha do usuário foi alterada com sucesso!");
            this.grupoSelecionado.clear();
            return montarPaginaParaListarUsuarios();
        }catch(Exception e){
            Mensagem.mostrarMensagemErro("Erro!", e.getLocalizedMessage());
        }
        return null;
    }
    
    //Parte 3 - Chamadas de Tela
    public String montarPaginaParaListarUsuarios(){
        
        recarregarUsuarios();
        return "/componentes/usuario/ListarUsuarios";
    }
    
    public String montarPaginaParaCadastrarUsuarios(){
        this.grupoSelecionado = new ArrayList<Grupo>();      
        this.usuario = new Usuario();
        this.usuario.setAtivo(true);
        return "/componentes/usuario/CadastrarUsuario";
    }
    
    public String montarPaginaParaAlterarUsuarios(){ 
        this.grupoSelecionado = this.usuario.getGrupoList();
        return "/componentes/usuario/AlterarUsuario";
    }
    
    public String montarPaginaParaAlterarSenha(){ 
        return "/componentes/usuario/AlterarSenha";
    }
    
    //Parte 4 - Métodos diversos
    public void recarregarUsuarios(){
        this.usuarios = fachada.listar();
    }
    
    public SelectItem[] getAtivoOpcoes(){
        SelectItem[] opcoes = new SelectItem[3];
        opcoes[0] = new SelectItem("","Selecione");
        opcoes[1] = new SelectItem("true","Sim");
        opcoes[2] = new SelectItem("false","Não");
        return opcoes;
    }
}

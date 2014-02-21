/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import fachada.TipoRotaFachada;
import ferramentas.Mensagem;
import interfaces.CRUD;
import interfaces.MontarPaginas;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.PersistenceException;
import modelo.TipoRota;
import org.primefaces.context.RequestContext;

/**
 *
 * @author lesena
 */
@ManagedBean(name = "tipoRotaManager")
@SessionScoped
public class TipoRotaManager implements Serializable,MontarPaginas,CRUD { 
    private TipoRota tipoRota;
    private List<TipoRota> tiposRota;
    @EJB 
    private TipoRotaFachada fachada;
    
    public TipoRota getTipoRota() {
        return tipoRota;
    }

    public void setTipoRota(TipoRota tipoRota) {
        this.tipoRota = tipoRota;
    }

    public List<TipoRota> getTiposRota() {
        tiposRota = fachada.listar();
        return tiposRota;
    }    
    
    @Override
    public String montarPaginaParaListar() {
       return "/componentes/tipoRota/ListarTipoRota";
    }

    @Override
    public String montarPaginaParaCadastro() {
        this.tipoRota = new TipoRota();
        return "/componentes/tipoRota/CadastroTipoRota";
    }

    @Override
    public String montarPaginaParaAlterar() {
       return "/componentes/tipoRota/AlterarTipoRota";
    }

    @Override
    public void recuperar() {
       this.getTiposRota();
    }

    @Override
    public String inserir() {
        try{
            fachada.inserir(tipoRota);
            Mensagem.mostrarMensagemSucesso("Sucesso!", "Tipo de Rota inserida com sucesso!");
            return montarPaginaParaListar();
        }catch(Exception e){
            Mensagem.mostrarMensagemErro("Erro!","Descrição já cadastada.");
            return "";
        }
       
    }

    @Override
    public String alterar() {
        try{
            fachada.alterar(tipoRota);
            Mensagem.mostrarMensagemSucesso("Sucesso!", "Tipo de rota alterada com sucesso!");
            return montarPaginaParaListar();
        }catch(Exception e){
            Mensagem.mostrarMensagemErro("Erro!","Descrição já cadastada.");
            return "";
        }
       
    }

    @Override
    public void excluir() {
       fachada.remover(tipoRota);
       RequestContext rc = RequestContext.getCurrentInstance();
       rc.execute("exclui.hide()");
       Mensagem.mostrarMensagemSucesso("Sucesso!", "Tipo de rota excluída com sucesso!");
    }
    
    
}

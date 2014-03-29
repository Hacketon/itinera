package br.com.itinera.manager;

import br.com.itinera.fachada.RotaFachada;
import br.com.itinera.fachada.TipoRotaFachada;
import br.com.itinera.ferramentas.Mensagem;
import br.com.itinera.interfaces.CRUD;
import br.com.itinera.interfaces.MontarPaginas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import br.com.itinera.modelo.Rota;
import br.com.itinera.modelo.TipoRota;
import org.primefaces.context.RequestContext;

/**
 *
 * @author LeticiaSena
 */
@ManagedBean(name = "rotaManager")
@SessionScoped
public class RotaManager implements Serializable,CRUD,MontarPaginas {

    private Rota rota;
    private List<Rota> rotas;
    @EJB
    private RotaFachada fachada;
    @EJB
    private TipoRotaFachada fachadaTipoRota;
        

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public List<Rota> getRotas() {
        return rotas;
    }

    public void setRotas(List<Rota> rotas) {
        this.rotas = rotas;
    }
    
    
    public List<SelectItem> getTiposDeRota() {
        List<SelectItem> tiposDeRota = new ArrayList<SelectItem>();
        List<TipoRota> tipos = fachadaTipoRota.listar();
        for (TipoRota tipo : tipos) {
            tiposDeRota.add(new SelectItem(tipo,tipo.getDescricao()));
        }
        return tiposDeRota;
    }
    
    @Override
    public void recuperar() {
       rotas = fachada.listar();
    }

    @Override
    public String inserir() {
        try{
            fachada.inserir(rota);
        } catch(Exception e){
            Mensagem.mostrarMensagemErro("Erro!","Já existe uma rota com este código.");
            return "";
        }
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("inclui.hide()");
        Mensagem.mostrarMensagemSucesso("Sucesso!", "Rota inserida com sucesso!");
        return montarPaginaParaListar();
    }

    @Override
    public String alterar() {
        try{
            fachada.alterar(rota);
        } catch(UnsupportedOperationException e){
            Mensagem.mostrarMensagemErro("Erro!", e.getMessage());
        } catch(Exception e){
            //Mensagem.mostrarMensagemErro("Erro!","Já existe uma rota com este código.");
            Mensagem.mostrarMensagemErro("Erro!",e.getMessage());
        }
        Mensagem.mostrarMensagemSucesso("Sucesso!", "Rota foi alterada com sucesso!");
        return montarPaginaParaListar();
    }

    @Override
    public void excluir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String montarPaginaParaListar() {
        recuperar();
        return "/componentes/rota/ListarRota";
    }

    @Override
    public String montarPaginaParaCadastro() {
        this.rota = new Rota();
        return "/componentes/rota/CadastroRota";
    }

    @Override
    public String montarPaginaParaAlterar() {
        return "/componentes/rota/AlterarRota";
    }

    
    public String montarPaginaParaCadastrarPontosColeta(){
        return "/compontentes/rota/RotaPontosColeta";
    }
    
}

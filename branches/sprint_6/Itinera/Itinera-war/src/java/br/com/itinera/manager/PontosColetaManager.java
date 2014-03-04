package br.com.itinera.manager;

import br.com.itinera.enuns.TipoLogradouro;
import br.com.itinera.fachada.PontosColetaFachada;
import br.com.itinera.ferramentas.Mensagem;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import br.com.itinera.modelo.Endereco;
import br.com.itinera.modelo.PontoColeta;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Michel
 */
@ManagedBean(name = "pontosColetaManager")
@SessionScoped
public class PontosColetaManager implements Serializable{
    private PontoColeta pontoColeta;
    private List<PontoColeta> pontosColeta = new ArrayList<PontoColeta>();
    private Endereco endereco;
    @EJB 
    private PontosColetaFachada fachada;
    
    //Parte 1 - GETs e SETs
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    
    public PontosColetaManager(){}
    
    public PontoColeta getPontoColeta(){
        return pontoColeta;
    }
    
    public void setPontoColeta(PontoColeta pontoColeta){
        this.pontoColeta = pontoColeta;
    }
    
    public List<PontoColeta> getPontosColetas(){
        return this.pontosColeta;
    }
    
    
    //Parte 2 - CRUD
    public String inserir(){
        fachada.inserir(pontoColeta);
        carregarLista();
        Mensagem.mostrarMensagemSucesso("Sucesso!","Ponto de coleta inserido com sucesso!");
        return montarPaginaParaListarPontosDeColeta();
    }
    
    public String alterar(){
        fachada.alterar(pontoColeta);
        Mensagem.mostrarMensagemSucesso("Sucesso!","Ponto de coleta alterado com sucesso!");
        carregarLista();
        return montarPaginaParaListarPontosDeColeta();
    }
    
    public void excluir(){
        fachada.remover(this.getPontoColeta());
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("exclui.hide()");
        Mensagem.mostrarMensagemSucesso("Sucesso!", "Ponto de coleta excluído com sucesso!");
        pontoColeta = new PontoColeta();
        this.carregarLista();
    }
    
   
  
    //Parte 3 - Chamadas de Tela
    public String montarPaginaParaListarPontosDeColeta(){
        carregarLista();
        return "/componentes/pontoColeta/ListarPontoColeta";
    }
    
    public String montarPaginaParaAlterarPontosDeColeta(){
        return "/componentes/pontoColeta/AlterarPontosDeColeta";
    }
    
    public String montarPaginaParaCadastrarPontosDeColeta(){
        this.pontoColeta = new PontoColeta();
        this.pontoColeta.setIdEndereco(new Endereco());
        this.pontoColeta.setAtivo(true);
        return "/componentes/pontoColeta/CadastroPontosDeColeta";
    }
    
    //Parte 4 - Métodos diversos
    public void carregarLista(){
        this.pontosColeta = fachada.listar();
    }

    public List<SelectItem> getTipoLogradouros() {
        List<SelectItem> tiposLogradouros = new ArrayList<SelectItem>();
        for (TipoLogradouro tc : TipoLogradouro.values()) {
            tiposLogradouros.add(new SelectItem(tc.name(),tc.toString()));
        }
        return tiposLogradouros;
    }
    
    public Integer contagem(){
        return fachada.contagem();
    }
    
    
}

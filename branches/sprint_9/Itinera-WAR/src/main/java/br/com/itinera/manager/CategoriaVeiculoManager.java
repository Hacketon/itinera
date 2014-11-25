package br.com.itinera.manager;

import br.com.itinera.fachada.CategoriaVeiculoFachada;
import br.com.itinera.ferramentas.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityExistsException;
import br.com.itinera.modelo.CategoriaVeiculo;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 *
 * @author LeticiaSena
 */
@ManagedBean(name = "categoriaVeiculoManager")
@SessionScoped
public class CategoriaVeiculoManager implements Serializable {
    
    private String mensagem;
    private String descricaoAnterior;
    private Boolean reboqueAnterior;
    private String mensagem1;
    private String mensagem2;
    private CategoriaVeiculo categoriaVeiculo;
    private List<CategoriaVeiculo> categoriaVeiculos = new ArrayList<CategoriaVeiculo>();
    @EJB
    private CategoriaVeiculoFachada categoriaVeiculoFachada;

    public CategoriaVeiculoManager(){
    }
    
    public String getMensagem1(){
        return mensagem1;
    }
    
    public String getMensagem2(){
        return mensagem2;
    }
    
    
    public String textoReboqueSemiReboque(CategoriaVeiculo c){
        return c.isReboqueSemiReboque()?"Sim":"Não";
    }
    
    public SelectItem[] getReboqueSemiReboqueOpcoes(){
        SelectItem[] opcoes = new SelectItem[3];
        opcoes[0] = new SelectItem("","Selecione");
        opcoes[1] = new SelectItem("true","Sim");
        opcoes[2] = new SelectItem("false","Não");
        return opcoes;
//        return reboqueSemiReboqueOpcoes;
    }
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public CategoriaVeiculo getCategoriaVeiculo() {
        return categoriaVeiculo;
    }

    public void setCategoriaVeiculo(CategoriaVeiculo categoriaVeiculo) {
        this.categoriaVeiculo = categoriaVeiculo;
    }

    public List<CategoriaVeiculo> getCategoriaVeiculos() {
        return categoriaVeiculos;
    }

    public void setCategoriaVeiculos(List<CategoriaVeiculo> catVeiculos) {
        this.categoriaVeiculos = catVeiculos;
    }

    public String montarPaginaParaListarCategoriaVeiculo() {
        this.recuperarCategoriaVeiculo();
        return "/componentes/categoriaVeiculo/ListarCategoriasVeiculo";
    }

    public String montarPaginaParaAlterarCategoriaVeiculo() {
        this.descricaoAnterior = this.categoriaVeiculo.getDescricaoCategoria();
        this.reboqueAnterior = this.categoriaVeiculo.isReboqueSemiReboque();
        return "/componentes/categoriaVeiculo/AlterarCategoriaVeiculo";
    }

    public String montarPaginaParaIncluirCategoriaVeiculo() {
        this.categoriaVeiculo = new CategoriaVeiculo();
        return "/componentes/categoriaVeiculo/CadastroCategoriaVeiculo";
    }

    private void recuperarCategoriaVeiculo() {
        this.setCategoriaVeiculos(categoriaVeiculoFachada.listar());
    }
    
    public String alterar() {
            boolean mudou = false;
            mensagem1 = "";
            mensagem2 = "";
            if(!(this.descricaoAnterior.compareTo(this.categoriaVeiculo.getDescricaoCategoria()) == 0)){
                mensagem1 = "Descrição: " + this.categoriaVeiculo.getDescricaoCategoria() + "(" + this.descricaoAnterior +")";
                mudou = true;
            }
            if(!(this.reboqueAnterior == this.categoriaVeiculo.isReboqueSemiReboque())){
                mudou = true;
                mensagem2 = "É reboque: " + ((this.categoriaVeiculo.isReboqueSemiReboque())?"Sim":"Não") + "(" + (this.reboqueAnterior?"Sim":"Não") + ")";
            }
            if(mudou){
                RequestContext rc = RequestContext.getCurrentInstance();
                rc.execute("altera.show()");
                return "";
            }
            else{
                return realizarAlteracao();
            }
    }
    
    public String realizarAlteracao(){
        try{
            categoriaVeiculoFachada.alterar(this.getCategoriaVeiculo());
            Mensagem.mostrarMensagemSucesso("Sucesso!", "A categoria de veículos foi alterada com sucesso.");
            this.categoriaVeiculo = new CategoriaVeiculo();
            this.recuperarCategoriaVeiculo();
            return "/componentes/categoriaVeiculo/ListarCategoriasVeiculo";
        } catch (EntityExistsException e) {
            Mensagem.mostrarMensagemErro("Erro:", "Uma categoria com esta descrição já foi inserida. Por favor, verifique!");
            return "";
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro("Erro:", "Um erro inesperado aconteceu." + e.getMessage());
            return "";
        }
    }

    public String inserir() {
        try {
            categoriaVeiculoFachada.inserir(this.getCategoriaVeiculo());
            Mensagem.mostrarMensagemSucesso("Sucesso!", "Categoria de veículo inserida com sucesso!");
            this.categoriaVeiculo = new CategoriaVeiculo();
            this.recuperarCategoriaVeiculo();
            return "/componentes/categoriaVeiculo/ListarCategoriasVeiculo";
        } catch (EntityExistsException e) {
            Mensagem.mostrarMensagemErro("Erro:", "Uma categoria com esta descrição já foi inserida. Por favor, verifique!");
            return "";
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro("Erro:", "Um erro inesperado aconteceu." + e.getMessage());
            return "";
        }
    }

    public void excluir() {
        categoriaVeiculoFachada.remover(this.getCategoriaVeiculo());
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("exclui.hide()");
        Mensagem.mostrarMensagemSucesso("Sucesso!", "A categoria de veículos foi excluída com sucesso.");
        categoriaVeiculo = new CategoriaVeiculo();
        this.recuperarCategoriaVeiculo();
    }

    public Integer contagem() {
        return categoriaVeiculoFachada.contagem();
    }
}

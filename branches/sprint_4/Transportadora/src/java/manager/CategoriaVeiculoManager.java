package manager;

import fachada.CategoriaVeiculoFachada;
import ferramentas.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityExistsException;
import modelo.CategoriaVeiculo;
import org.primefaces.context.RequestContext;

/**
 *
 * @author LeticiaSena
 */
@ManagedBean(name = "categoriaVeiculoManager")
@SessionScoped
public class CategoriaVeiculoManager implements Serializable {

    private String mensagem;
    private String titulo;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    private CategoriaVeiculo categoriaVeiculo;
    private List<CategoriaVeiculo> categoriaVeiculos = new ArrayList<CategoriaVeiculo>();
    
    @EJB
    private CategoriaVeiculoFachada categoriaVeiculoFachada;

    public CategoriaVeiculoManager() {
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
        try {
            categoriaVeiculoFachada.alterar(this.getCategoriaVeiculo());
            Mensagem.mostrarMensagemSucesso("Sucesso!", "A categoria de veículos foi alterada com sucesso.");
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
            this.recuperarCategoriaVeiculo();
            return "/componentes/categoriaVeiculo/ListarCategoriasVeiculo?aces-redirect=true";
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

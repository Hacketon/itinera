
package br.com.itinera.manager;

import br.com.itinera.fachada.PlanoContasFachada;
import br.com.itinera.ferramentas.Mensagem;
import br.com.itinera.interfaces.CRUD;
import br.com.itinera.interfaces.MontarPaginas;
import br.com.itinera.modelo.PlanoContas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityExistsException;
import org.primefaces.context.RequestContext;


/**
 *
 * @author marcelo
 */
@ManagedBean(name = "planoContasManager")
@SessionScoped
public class PlanoContasManager implements Serializable, CRUD, MontarPaginas {

    private PlanoContas planoContas;
    private PlanoContas planoContasAnterior;
    private String mensagem;
    private List<PlanoContas> lstPlanoContas = new ArrayList<PlanoContas>();

    public PlanoContasManager() {
    }

    @EJB
    private PlanoContasFachada fachada;

    @Override
    public void recuperar() {
        this.setLstPlanoContas(fachada.listar());
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    

    @Override
    public String inserir() {
        try {
            if (this.getPlanoContas().getPlanoContasId() == null) {
                fachada.inserir(this.getPlanoContas());
                Mensagem.mostrarMensagemSucesso(Mensagem.sucesso, "Plano de Contas inserido com sucessso.");
                return montarPaginaParaListar();
            } else {
                this.alterar();
            }
        } catch (EntityExistsException en) {
            Mensagem.mostrarMensagemErro(Mensagem.duplicidade, "Plano de Contas já existe.");
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro(Mensagem.erro, Mensagem.erroCritico + e.getMessage());
        }
        return "";
    }

    @Override
    public String alterar() {
        validaAlteracao();
        if(mensagem.isEmpty()){
            return realizarAlteracao();
        }
        else{
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("altera.show()");
        }
        return "";
    }
    
    private void validaAlteracao(){
        mensagem = "";
        if(!this.planoContas.equals(this.planoContasAnterior.getDescricao())){
            mensagem += "DESCRIÇÃO: " + this.planoContas.getDescricao() + "(" + this.planoContasAnterior.getDescricao() + ")<br>";
        }
        if(this.planoContas.getTipoCombustivel() != this.planoContasAnterior.getTipoCombustivel()){
            mensagem += "TIPO COMBUSTÍVEL: "+ (this.planoContas.getTipoCombustivel()?"Sim":"Não") + "(" + (this.planoContasAnterior.getTipoCombustivel()?"Sim":"Não") + ")<br>";
        }
    }
    
    public String realizarAlteracao(){
        try {
            fachada.alterar(this.getPlanoContas());
            Mensagem.mostrarMensagemSucesso(Mensagem.sucesso, "Plano de Contas alterado com sucessso.");
            return montarPaginaParaListar();
        } catch (EntityExistsException en) {
            Mensagem.mostrarMensagemErro(Mensagem.duplicidade, "Plano de Contas já existe.");
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro(Mensagem.erro, Mensagem.erroCritico + e.getMessage());
        }
        return "";
    }

    @Override
    public void excluir() {
        try {
            fachada.remover(this.getPlanoContas());
            Mensagem.mostrarMensagemSucesso(Mensagem.sucesso, "Plano de Contas excluído com sucesso.");
            this.montarPaginaParaListar();
        } catch (Exception e) {
            Mensagem.mostrarMensagemSucesso(Mensagem.erroCritico, "Erro ao excluir plano de contas. " + e.getMessage());
        }
    }

    @Override
    public String montarPaginaParaListar() {
        this.recuperar();
        return "/componentes/planoconta/ListarPlanoConta";
    }

    @Override
    public String montarPaginaParaCadastro() {
        this.planoContas = new PlanoContas();
        return this.montarPaginaParaAlterar();
    }

    @Override
    public String montarPaginaParaAlterar() {
        planoContasAnterior = new PlanoContas();
        planoContasAnterior.setDescricao(this.planoContas.getDescricao());
        planoContasAnterior.setTipoCombustivel(this.planoContas.getTipoCombustivel());
        return "/componentes/planoconta/AlterarPlanoConta";
    }

    public PlanoContas getPlanoContas() {
        return planoContas;
    }

    public void setPlanoContas(PlanoContas planoContas) {
        this.planoContas = planoContas;
    }

    public List<PlanoContas> getLstPlanoContas() {
        return lstPlanoContas;
    }

    public void setLstPlanoContas(List<PlanoContas> lstPlanoContas) {
        this.lstPlanoContas = lstPlanoContas;
    }

}

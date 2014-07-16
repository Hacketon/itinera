package br.com.itinera.manager;

import br.com.itinera.fachada.DespesaFachada;
import br.com.itinera.fachada.EmpresaFachada;
import br.com.itinera.fachada.MotoristaFachada;
import br.com.itinera.fachada.VeiculoFachada;
import br.com.itinera.ferramentas.Mensagem;
import br.com.itinera.interfaces.CRUD;
import br.com.itinera.interfaces.MontarPaginas;
import br.com.itinera.modelo.Despesa;
import br.com.itinera.modelo.Empresa;
import br.com.itinera.modelo.Motorista;
import br.com.itinera.modelo.Veiculo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityExistsException;

/**
 *
 * @author marcelo
 */
@ManagedBean(name = "despesaManager")
@SessionScoped
public class DespesaManager implements Serializable, CRUD, MontarPaginas {

    private Despesa despesa;
    private List<Despesa> despesas;
    private List<Empresa> lstEmpresa;
    private List<Veiculo> lstVeiculo;
    private List<Motorista> lstMotorista;
    @EJB
    private DespesaFachada fachada;
    @EJB
    private EmpresaFachada empresaFachada;
    @EJB
    private VeiculoFachada veiculoFachada;
    @EJB
    private MotoristaFachada motoristaFachada;
    
    @Override
    public void recuperar() {
        setDespesas(fachada.listar());
    }

    @Override
    public String inserir() {
        try {
            if (getDespesa().getIdDespesa() == null) {
                fachada.inserir(despesa);
                Mensagem.mostrarMensagemSucesso(Mensagem.sucesso, "Registro inserido com sucessso.");
                return montarPaginaParaListar();
            } else {
                this.alterar();
            }
        } catch (EntityExistsException en) {
            Mensagem.mostrarMensagemErro(Mensagem.duplicidade, "Registro já existe.");
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro(Mensagem.erro, Mensagem.erroCritico + e.getMessage());
        }
        return "";
    }

    @Override
    public String alterar() {
        try {
            fachada.alterar(this.getDespesa());
            Mensagem.mostrarMensagemSucesso(Mensagem.sucesso, "Registro alterado com sucessso.");
            return montarPaginaParaListar();
        } catch (EntityExistsException en) {
            Mensagem.mostrarMensagemErro(Mensagem.duplicidade, "Registro já existe.");
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro(Mensagem.erro, Mensagem.erroCritico + e.getMessage());
        }
        return "";
    }

    @Override
    public void excluir() {
        try {
            fachada.remover(this.getDespesa());
            Mensagem.mostrarMensagemSucesso(Mensagem.sucesso, "Registro excluído com sucesso.");
            this.montarPaginaParaListar();
        } catch (Exception e) {
            Mensagem.mostrarMensagemSucesso(Mensagem.erroCritico, "Erro ao excluir plano de contas. " + e.getMessage());
        }
    }

    @Override
    public String montarPaginaParaListar() {
        this.recuperar();
        return "/componentes/despesa/ListarDespesa";
    }

    @Override
    public String montarPaginaParaCadastro() {
        this.despesa = new Despesa();
        return "/componentes/despesa/AlterarDespesa";
    }

    @Override
    public String montarPaginaParaAlterar() {
        return "/componentes/despesa/AlterarDespesa";
    }

    public List<Empresa> completeFornecedor(String nomeFantasia) {
        lstEmpresa = new ArrayList<Empresa>();
        for (Empresa p : empresaFachada.buscarPorFornecedor(nomeFantasia)) {
            if (p.getNomeFantasia().toUpperCase().contains(nomeFantasia.toUpperCase())) {
                lstEmpresa.add(p);
            }
        }

        return lstEmpresa;
    }

    public List<Veiculo> completeVeiculo(String placa) {
        lstVeiculo = new ArrayList<Veiculo>();
        for (Veiculo v : veiculoFachada.buscarPorPlaca(placa.toUpperCase())) {
            if (v.getPlacaVeiculo().toUpperCase().contains(placa.toUpperCase())) {
                lstVeiculo.add(v);
            }
        }

        return lstVeiculo;
    }

    public List<Motorista> completeMotorista(String nome) {
        lstMotorista = new ArrayList<Motorista>();
        for (Motorista m : motoristaFachada.buscarPorNome(nome)) {
            if (m.getNome().toUpperCase().contains(nome.toUpperCase())) {
                lstMotorista.add(m);
            }
        }

        return lstMotorista;
    }

    public Despesa getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }

    public List<Despesa> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }

    public List<Empresa> getLstEmpresa() {
        return lstEmpresa;
    }

    public void setLstEmpresa(List<Empresa> lstEmpresa) {
        this.lstEmpresa = lstEmpresa;
    }

    public List<Veiculo> getLstVeiculo() {
        return lstVeiculo;
    }

    public void setLstVeiculo(List<Veiculo> lstVeiculo) {
        this.lstVeiculo = lstVeiculo;
    }
    
}

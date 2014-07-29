package br.com.itinera.manager;

import br.com.itinera.enuns.StatusDespesa;
import br.com.itinera.fachada.DespesaFachada;
import br.com.itinera.fachada.EmpresaFachada;
import br.com.itinera.fachada.MotoristaFachada;
import br.com.itinera.fachada.PlanoContasFachada;
import br.com.itinera.fachada.VeiculoFachada;
import br.com.itinera.ferramentas.Mensagem;
import br.com.itinera.interfaces.CRUD;
import br.com.itinera.interfaces.MontarPaginas;
import br.com.itinera.modelo.Despesa;
import br.com.itinera.modelo.Empresa;
import br.com.itinera.modelo.Motorista;
import br.com.itinera.modelo.PlanoContas;
import br.com.itinera.modelo.Veiculo;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
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
    private List<PlanoContas> lstPlanoContas;
    private String total;
    private Date dtInicioFiltro;
    private Date dtFimFiltro;
    private boolean alterarStatus;

    @EJB
    private DespesaFachada fachada;
    @EJB
    private EmpresaFachada empresaFachada;
    @EJB
    private VeiculoFachada veiculoFachada;
    @EJB
    private MotoristaFachada motoristaFachada;
    @EJB
    private PlanoContasFachada planoContasFachada;

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
            if (getDespesa().getIdDespesa() == null) {
                fachada.inserir(despesa);
            } else {
                fachada.alterar(despesa);
            }
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
        if (mesEmAberto()) {
            if (confirmaExclusao()) {
                try {
                    fachada.remover(this.getDespesa());
                    Mensagem.mostrarMensagemSucesso(Mensagem.sucesso, "Registro excluído com sucesso.");
                    this.montarPaginaParaListar();
                } catch (Exception e) {
                    Mensagem.mostrarMensagemSucesso(Mensagem.erroCritico, "Erro ao excluir plano de contas. " + e.getMessage());
                }
            }
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
        this.despesa.setStatus("C");
        this.alterarStatus = false;
        return "/componentes/despesa/AlterarDespesa";
    }

    @Override
    public String montarPaginaParaAlterar() {
        this.alterarStatus = true;
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
        for (Motorista m : motoristaFachada.buscarPorNomeSomenteAtivo(nome)) {
            if (m.getNome().toUpperCase().contains(nome.toUpperCase())) {
                lstMotorista.add(m);
            }
        }
        return lstMotorista;
    }

    public List<PlanoContas> completePlanoContas(String descricao) {
        lstPlanoContas = new ArrayList<PlanoContas>();
        for (PlanoContas p : planoContasFachada.buscarPorDescricao(descricao)) {
            if (p.getDescricao().toUpperCase().contains(descricao.toUpperCase())) {
                lstPlanoContas.add(p);
            }
        }

        return lstPlanoContas;
    }

    public void calcularTotalDaDespesa() {
        if (this.despesa.getQuantidade() != null && this.despesa.getValor() != null) {
            this.setTotal(new DecimalFormat("#.##").format(this.despesa.getQuantidade().doubleValue() * this.despesa.getValor().doubleValue()));
        }
    }

    public void filtrarDespesa() {
        if (this.getDtInicioFiltro() != null && this.getDtFimFiltro() != null) {
            setDespesas(fachada.filtrarDespesasPorData(this.getDtInicioFiltro(), this.getDtFimFiltro()));
        }
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

    public List<Motorista> getLstMotorista() {
        return lstMotorista;
    }

    public void setLstMotorista(List<Motorista> lstMotorista) {
        this.lstMotorista = lstMotorista;
    }

    public List<PlanoContas> getLstPlanoContas() {
        return lstPlanoContas;
    }

    public void setLstPlanoContas(List<PlanoContas> lstPlanoContas) {
        this.lstPlanoContas = lstPlanoContas;
    }

    public void setLstVeiculo(List<Veiculo> lstVeiculo) {
        this.lstVeiculo = lstVeiculo;
    }

    public List<SelectItem> getStatus() {
        List<SelectItem> status = new ArrayList<SelectItem>();
        status.add(new SelectItem(null, "Selecione..."));
        status.add(new SelectItem("C", "Confirmado"));
        status.add(new SelectItem("E", "Enviado"));
        status.add(new SelectItem("R", "Erro"));
        status.add(new SelectItem("N", "Não Enviado"));
//        for (StatusDespesa e : StatusDespesa.values()) {
//            status.add(new SelectItem(e.name(), e.toString()));
//        }
        return status;
    }

    public Double getTotalDeDespesas() {
        Double t = Double.valueOf(0);
        for (Despesa d : despesas) {
            t += d.getValor().doubleValue();
        }
        return t;
    }

    public String getTotal() {
        this.calcularTotalDaDespesa();
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Date getDtInicioFiltro() {
        return dtInicioFiltro;
    }

    public void setDtInicioFiltro(Date dtInicioFiltro) {
        this.dtInicioFiltro = dtInicioFiltro;
    }

    public Date getDtFimFiltro() {
        return dtFimFiltro;
    }

    public void setDtFimFiltro(Date dtFimFiltro) {
        this.dtFimFiltro = dtFimFiltro;
    }

    public boolean isAlterarStatus() {
        return alterarStatus;
    }

    public void setAlterarStatus(boolean alterarStatus) {
        this.alterarStatus = alterarStatus;
    }

    private boolean mesEmAberto() {
        return true;
    }
    
    private boolean confirmaExclusao() {
        return true;
    }

}

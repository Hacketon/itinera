package br.com.itinera.manager;

import br.com.itinera.fachada.DespesaFachada;
import br.com.itinera.fachada.MotoristaFachada;
import br.com.itinera.fachada.OrdemColetaFachada;
import br.com.itinera.ferramentas.Mensagem;
import br.com.itinera.interfaces.MontarPaginas;
import br.com.itinera.modelo.Despesa;
import br.com.itinera.modelo.Motorista;
import br.com.itinera.modelo.OrdemColeta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author marcelo
 */
@ManagedBean(name = "fechamentoManager")
@SessionScoped
public class FechamentoManager implements Serializable, MontarPaginas {

    @EJB
    private DespesaFachada despesaFachada;

    @EJB
    private MotoristaFachada motoristaFachada;

    @EJB
    private OrdemColetaFachada ordemColetaFachada;

    private List<Despesa> despesas = new ArrayList<Despesa>();
    private List<OrdemColeta> ordemColetas = new ArrayList<OrdemColeta>();
    private Motorista motorista;
    private double totalDespesa;
    private double totalOrdemColeta;

    private Date dtInicioFiltro;
    private Date dtFimFiltro;

    public FechamentoManager() {
        motorista = new Motorista();
    }

    @Override
    public String montarPaginaParaListar() {
        return "/componentes/fechamento/FechamentoMotorista";
    }

    @Override
    public String montarPaginaParaCadastro() {
        return "";
    }

    @Override
    public String montarPaginaParaAlterar() {
        return "";
    }

    public void filtrar() {
        if (this.motorista.getMotoristaId() == null) {
            Mensagem.mostrarMensagem("ATENÇÃO", "Escolha um motorista");
        } else {
            totalDespesa = 0;
            totalOrdemColeta = 0;
            despesas = despesaFachada.filtrarDespesaPorMotorista(this.motorista, dtInicioFiltro, dtFimFiltro);
            ordemColetas = ordemColetaFachada.buscarPorMotorista(this.motorista, dtInicioFiltro, dtFimFiltro);
        }
    }

    private void calcularTotalDespesa() {
        if (despesas.isEmpty()) {
            setTotalDespesa(Double.valueOf(0));
        } else {
            for (Despesa d : despesas) {
                totalDespesa += d.getValor().doubleValue();
            }
        }
    }

    private void calcularTotalOrdemColeta() {
        if (ordemColetas.isEmpty()) {
            setTotalOrdemColeta(Double.valueOf(0));
        } else {
            for (OrdemColeta oc : ordemColetas) {
                totalOrdemColeta += oc.getValorTotal().doubleValue();
            }
        }
    }

    public DespesaFachada getDespesaFachada() {
        return despesaFachada;
    }

    public void setDespesaFachada(DespesaFachada despesaFachada) {
        this.despesaFachada = despesaFachada;
    }

    public MotoristaFachada getMotoristaFachada() {
        return motoristaFachada;
    }

    public void setMotoristaFachada(MotoristaFachada motoristaFachada) {
        this.motoristaFachada = motoristaFachada;
    }

    public OrdemColetaFachada getOrdemColetaFachada() {
        return ordemColetaFachada;
    }

    public void setOrdemColetaFachada(OrdemColetaFachada ordemColetaFachada) {
        this.ordemColetaFachada = ordemColetaFachada;
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

    public List<Despesa> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }

    public List<OrdemColeta> getOrdemColetas() {
        return ordemColetas;
    }

    public void setOrdemColetas(List<OrdemColeta> ordemColetas) {
        this.ordemColetas = ordemColetas;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public double getTotalDespesa() {
        this.calcularTotalDespesa();
        return totalDespesa;
    }

    public void setTotalDespesa(double totalDespesa) {
        this.totalDespesa = totalDespesa;
    }

    public double getTotalOrdemColeta() {
        this.calcularTotalOrdemColeta();
        return totalOrdemColeta;
    }

    public void setTotalOrdemColeta(double totalOrdemColeta) {
        this.totalOrdemColeta = totalOrdemColeta;
    }

}

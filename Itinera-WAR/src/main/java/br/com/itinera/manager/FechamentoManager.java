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
    private double totalOrdemColetaMotorista;
    private double saldo;
    private final double valorKmRodado = 0.30;
    private final String corSaldoPositivo = "#4be234";
    private final String corSaldoNegativo = "#ed1212";

    private Date dtInicioFiltro;
    private Date dtFimFiltro;

    public FechamentoManager() {
        this.inicializarTotal();
        motorista = new Motorista();
    }

    @Override
    public String montarPaginaParaListar() {
        this.inicializarTotal();
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
    
    public String montarPaginaParaListarFechamentoEmpresa() {
        this.inicializarTotal();
        this.calcularTotais();
        return "/componentes/fechamento/Fechamento";
    }

    public void filtrar() {
        if (this.motorista.getMotoristaId() == null) {
            Mensagem.mostrarMensagem("ATENÇÃO", "Escolha um motorista");
        } else {
            this.inicializarTotal();
            despesas = despesaFachada.filtrarDespesaPorMotorista(this.motorista, dtInicioFiltro, dtFimFiltro);
            ordemColetas = ordemColetaFachada.buscarPorMotorista(this.motorista, dtInicioFiltro, dtFimFiltro);
        }
    }
    
    public void filtrarFechamento() {
        if (this.dtInicioFiltro == null && this.dtFimFiltro == null) {
            Mensagem.mostrarMensagem("ATENÇÃO", "Preencha as datas.");
        } else {
            this.inicializarTotal();
            this.calcularTotais();
            despesas = despesaFachada.filtrarDespesasPorData(dtInicioFiltro, dtFimFiltro);
            ordemColetas = ordemColetaFachada.buscarPorPeriodo(dtInicioFiltro, dtFimFiltro);
        }
    }

    private void calcularTotalDespesa() {
        if (despesas.isEmpty()) {
            totalDespesa = 0;
        } else {
            for (Despesa d : despesas) {
                totalDespesa += d.getValor().doubleValue();
            }
        }
    }

    private void calcularTotalOrdemColeta() {
        if (ordemColetas.isEmpty()) {
            totalOrdemColeta = 0;
        } else {
            for (OrdemColeta oc : ordemColetas) {
                totalOrdemColeta += oc.getValorTotal().doubleValue();
            }
        }
    }

    private void calcularTotalOrdemColetaMotorista() {
        if (ordemColetas.isEmpty()) {
            totalOrdemColetaMotorista = 0;
        } else {
            for (OrdemColeta oc : ordemColetas) {
                totalOrdemColetaMotorista += oc.getDistancia().doubleValue() * valorKmRodado;
            }
        }
    }
    
    private void calcularSaldo() {
        saldo = totalOrdemColeta - (totalDespesa+totalOrdemColetaMotorista);
    }
    
    private void inicializarTotal() {
        totalDespesa = 0;
        totalOrdemColeta = 0;
        totalOrdemColetaMotorista = 0;
        saldo = 0;
    }
    
    private void calcularTotais() {
        this.calcularTotalDespesa();
        this.calcularTotalOrdemColeta();
        this.calcularTotalOrdemColetaMotorista();
        this.calcularSaldo();
    }

    public String definirCorSaldo() {
        return (saldo >= 0 ) ? corSaldoPositivo : corSaldoNegativo;
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
        return totalDespesa;
    }

    public void setTotalDespesa(double totalDespesa) {
        this.totalDespesa = totalDespesa;
    }

    public double getTotalOrdemColeta() {
        return totalOrdemColeta;
    }

    public void setTotalOrdemColeta(double totalOrdemColeta) {
        this.totalOrdemColeta = totalOrdemColeta;
    }

    public double getTotalOrdemColetaMotorista() {
        return totalOrdemColetaMotorista;
    }

    public void setTotalOrdemColetaMotorista(double totalOrdemColetaMotorista) {
        this.totalOrdemColetaMotorista = totalOrdemColetaMotorista;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}

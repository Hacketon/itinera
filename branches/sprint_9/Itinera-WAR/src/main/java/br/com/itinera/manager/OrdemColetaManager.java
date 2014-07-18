/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.manager;

import br.com.itinera.fachada.EmpresaFachada;
import br.com.itinera.fachada.MotoristaFachada;
import br.com.itinera.fachada.OrdemColetaFachada;
import br.com.itinera.fachada.VeiculoFachada;
import br.com.itinera.ferramentas.Mensagem;
import br.com.itinera.modelo.Empresa;
import br.com.itinera.modelo.Motorista;
import br.com.itinera.modelo.OrdemColeta;
import br.com.itinera.modelo.Veiculo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author lesena
 */
@ManagedBean(name="ordemColetaManager")
@SessionScoped
public class OrdemColetaManager implements Serializable{
   @EJB
   private OrdemColetaFachada fachada;
   @EJB
   private EmpresaFachada empresaFachada;
   @EJB 
   private MotoristaFachada motoristaFachada;
   @EJB 
   private VeiculoFachada veiculoFachada;
   private OrdemColeta ordemColeta;
   private List<OrdemColeta> ordensColeta;
   private Date filtroDataInicio;
   private Date filtroDataFim;
   private Integer filtroNotaFiscal;
   private BigDecimal valorTotal;
   private BigDecimal valorUnitario;
   private Integer quantidade;
   private boolean inserindo;

    public OrdemColeta getOrdemColeta() {
        return ordemColeta;
    }

    public void setOrdemColeta(OrdemColeta ordemColeta) {
        this.ordemColeta = ordemColeta;
    }

    public List<OrdemColeta> getOrdensColeta() {
        return ordensColeta;
    }

    public void setOrdensColeta(List<OrdemColeta> ordensColeta) {
        this.ordensColeta = ordensColeta;
    }

    public Date getFiltroDataInicio() {
        return filtroDataInicio;
    }

    public boolean isInserindo() {
        return inserindo;
    }

    public void setInserindo(boolean inserindo) {
        this.inserindo = inserindo;
    }    

    public void setFiltroDataInicio(Date filtroDataInicio) {
        this.filtroDataInicio = filtroDataInicio;
    }

    public Date getFiltroDataFim() {
        return filtroDataFim;
    }
    
    public BigDecimal getValorTotal(){
        return valorTotal;
    }

    public void setFiltroDataFim(Date filtroDataFim) {
        this.filtroDataFim = filtroDataFim;
    }

    public Integer getFiltroNotaFiscal() {
        return filtroNotaFiscal;
    }

    public void setFiltroNotaFiscal(Integer filtroNotaFiscal) {
        this.filtroNotaFiscal = filtroNotaFiscal;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
   
    public String montarPaginaListagem(){
        this.inserindo = false;
        filtroDataFim = null;
        filtroDataInicio = null;
        filtroNotaFiscal  = null;
        this.ordensColeta = fachada.listar();
        return "/componentes/ordemColeta/ListarOrdemColeta";
    }
    
    public String montarPaginaCadastro(){
        System.out.println("PÁGINA DE CADASTRO");
        this.setInserindo(true);
        this.ordemColeta = new OrdemColeta();
        this.valorTotal = null;
        this.valorUnitario = BigDecimal.valueOf(0);
        this.quantidade = 0;
        return "/componentes/ordemColeta/CadastroOrdemColeta";
    }
    
    public String montarPaginaAlterar(){
        System.out.println("PÁGINA DE ALTERAÇÃO");
        this.inserindo = false;
        this.valorTotal = this.ordemColeta.getValorTotal();
        this.valorUnitario = this.ordemColeta.getValorUnitario();
        this.quantidade = this.ordemColeta.getQuantidade().intValue();
        return "/componentes/ordemColeta/CadastroOrdemColeta";
    }
    
    public String inserir(){
        System.out.println("ENTRANDO");
        this.ordemColeta.setValorUnitario(this.valorUnitario);
        this.ordemColeta.setQuantidade(BigInteger.valueOf(this.quantidade.longValue()));
        this.ordemColeta.setValorTotal(valorTotal);
        System.out.println("INSERT");
//        ordemColeta.setDestinatarioId(empresaFachada.listar().get(1));
//        ordemColeta.setMotoristaId(motoristaFachada.listarMotoristaAtivo().get(0));
//        ordemColeta.setQuantidade(BigInteger.TEN);
//        ordemColeta.setValorUnitario(BigDecimal.valueOf(155));
//        ordemColeta.setRemetenteId(empresaFachada.listar().get(0));
//        ordemColeta.setValorTotal(BigDecimal.valueOf(this.ordemColeta.getQuantidade().doubleValue() * this.ordemColeta.getValorUnitario().doubleValue()));
//        ordemColeta.setVeiculoId(veiculoFachada.listar().get(1));
        fachada.inserir(ordemColeta);
        Mensagem.mostrarMensagemSucesso("Sucesso!", "Ordem de Coleta inserida com sucesso!");
        return montarPaginaListagem();
    }
    
    public String alterar(){
        System.out.println("ENTRANDO");
        System.out.println(inserindo);
//        this.ordemColeta.setValorUnitario(this.valorUnitario);
//        this.ordemColeta.setQuantidade(BigInteger.valueOf(this.quantidade.longValue()));
//        this.ordemColeta.setValorTotal(valorTotal);
        System.out.println("ALTERAR");
        fachada.alterar(ordemColeta);
        return montarPaginaListagem();
                
    }
    
    public void excluir(){
        //TODO Realizar método para excluir a ordem de coleta e exibir mensagem de sucesso. 
    }
    
    public void filtrar(){
       this.ordensColeta = fachada.buscarPorPeriodoNotaFiscal(filtroDataInicio, filtroDataFim, filtroNotaFiscal);
    }
    
    public List<Motorista> completeMotorista(String nome) {
        List<Motorista> lstMotorista = new ArrayList<Motorista>();
        for (Motorista m : motoristaFachada.buscarPorNomeSomenteAtivo(nome)) {
            if (m.getNome().toUpperCase().contains(nome.toUpperCase())) {
                lstMotorista.add(m);
            }
        }
        return lstMotorista;
    }
    
        public List<Veiculo> completeVeiculo(String placa) {
        List<Veiculo> lstVeiculo = new ArrayList<Veiculo>();
        for (Veiculo v : veiculoFachada.buscarPorPlaca(placa.toUpperCase())) {
            if (v.getPlacaVeiculo().toUpperCase().contains(placa.toUpperCase())) {
                lstVeiculo.add(v);
            }
        }

        return lstVeiculo;
    }
    
    public List<Empresa> completeRemetente(String query){
       List<Empresa> suggestions = new ArrayList<Empresa>();
        for(Empresa p : empresaFachada.buscarPorTipo('U')) {
            if(p.getNomeFantasia().toUpperCase().contains(query.toUpperCase()))
                suggestions.add(p);
        }
         
        return suggestions;
    }
    
    public List<Empresa> completeDestinatario(String query){
        List<Empresa> suggestions = new ArrayList<Empresa>();
        for(Empresa p : empresaFachada.buscarPorTipo('C')){
            suggestions.add(p);
        }
        for(Empresa p : empresaFachada.buscarPorTipo('U')) {
            if(p.getNomeFantasia().toUpperCase().contains(query.toUpperCase()))
                suggestions.add(p);
        }
        return suggestions;
    }
    
    public String formatarEndereco(Empresa emp){
        if(emp.toString() == null){
            return "";
        }
        else{
            return emp.getEndereco() +" - "+ emp.getIdMunicipio();   
        }
    }
    
    public String formatarData(Date data){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
    
    public void calcular(){   
        if(!(this.valorUnitario == null && this.quantidade == null)){
            if(this.valorUnitario.doubleValue() > 0 && this.quantidade > 0){
                this.valorTotal = this.valorUnitario.multiply(BigDecimal.valueOf(quantidade));
            }   
        }
    }
    
    public String formataTextoLongo(String textoLongo){
        if(textoLongo.length() > 20)
            return textoLongo.substring(0, 17) + "...";
        else
            return textoLongo;
    }
    
    public String formataMoeda(String numero){
        return numero.replace('.',',');
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.manager;

import br.com.itinera.fachada.EmpresaFachada;
import br.com.itinera.fachada.OrdemColetaFachada;
import br.com.itinera.modelo.Empresa;
import br.com.itinera.modelo.OrdemColeta;
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
   private OrdemColeta ordemColeta;
   private List<OrdemColeta> ordensColeta;
   private Date filtroDataInicio;
   private Date filtroDataFim;
   private String filtroNotaFiscal;
   private BigDecimal valorTotal;
   private BigDecimal valorUnitario;
   private Integer quantidade;
   

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

    public String getFiltroNotaFiscal() {
        return filtroNotaFiscal;
    }

    public void setFiltroNotaFiscal(String filtroNotaFiscal) {
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
        this.ordensColeta = fachada.listar();
        return "/componentes/ordemColeta/ListarOrdemColeta";
    }
    
    public String montarPaginaCadastro(){
        this.ordemColeta = new OrdemColeta();
        this.valorTotal = null;
        this.valorUnitario = null;
        this.quantidade = null;
        return "/componentes/ordemColeta/CadastroOrdemColeta";
    }
    
    public String montarPaginaAlterar(){
        //TODO Realizar metodo para preparar a página para alteração e redirecionar à outra página
        return "/componentes/ordemColeta/CadastroOrdemColeta";
    }
    
    public void salvar(){
        this.ordemColeta.setValorUnitario(this.valorUnitario);
        this.ordemColeta.setQuantidade(BigInteger.valueOf(this.quantidade.longValue()));
        this.ordemColeta.setValorTotal(valorTotal);
        //TODO realizar método para salvar 
    }
    
    public void excluir(){
        //TODO Realizar método para excluir a ordem de coleta e exibir mensagem de sucesso. 
    }
    
    public void filtrar(){
        //TODO Realizar o filtro sobre os itens da lista referentes à busca dos itens selecionados. 
    }
    
    public void completeMotorista(){
        //TODO Realizar aqui o método para autocomplete
    }
    
    public void completeVeiculo(){
        //TODO Realizar aqui o método para autocomplete
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
        if(this.valorUnitario.doubleValue() > 0 && this.quantidade > 0){
            this.valorTotal = this.valorUnitario.multiply(BigDecimal.valueOf(quantidade));
        }   
        else
            this.valorTotal = null;
    }
}

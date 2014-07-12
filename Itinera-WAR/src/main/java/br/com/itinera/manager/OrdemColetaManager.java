/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.manager;

import br.com.itinera.fachada.OrdemColetaFachada;
import br.com.itinera.modelo.OrdemColeta;
import java.io.Serializable;
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
   private OrdemColeta ordemColeta;
   private List<OrdemColeta> ordensColeta;
   private Date filtroDataInicio;
   private Date filtroDataFim;
   private String filtroNotaFiscal;

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

    public void setFiltroDataFim(Date filtroDataFim) {
        this.filtroDataFim = filtroDataFim;
    }

    public String getFiltroNotaFiscal() {
        return filtroNotaFiscal;
    }

    public void setFiltroNotaFiscal(String filtroNotaFiscal) {
        this.filtroNotaFiscal = filtroNotaFiscal;
    }
    
   
    public String montarPaginaListagem(){
        this.ordensColeta = fachada.listar();
        return "/componentes/ordemColeta/ListarOrdemColeta";
    }
    
    public String montarPaginaCadastro(){
        //TODO Realizar metodo para preparar a página para cadastro e redirecionar à outra página
        return "";
    }
    
    public String montarPaginaAlterar(){
        //TODO Realizar metodo para preparar a página para alteração e redirecionar à outra página
        return "";
    }
    
    public void excluir(){
        //TODO Realizar método para excluir a ordem de coleta e exibir mensagem de sucesso. 
    }
    
    public void filtrar(){
        //TODO Realizar o filtro sobre os itens da lista referentes à busca dos itens selecionados. 
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import enuns.TipoCombustivel;
import fachada.CategoriaVeiculoFachada;
import fachada.VeiculoFachada;
import ferramentas.Mensagem;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import modelo.CategoriaVeiculo;
import modelo.Veiculo;
import org.primefaces.context.RequestContext;

/**
 *
 * @author LeticiaSena
 */
@ManagedBean(name = "veiculoManager")
@SessionScoped
public class VeiculoManager implements Serializable{
    
    private Veiculo veiculo;
    private List<Veiculo> veiculos = new ArrayList<Veiculo>();

    @EJB
    private VeiculoFachada fachada;
    
    @EJB 
    private CategoriaVeiculoFachada categoriaFachada;
    
    //Parte 1 - GETs e SETs
    public VeiculoManager(){}
    
    public void setVeiculo(Veiculo veiculo){
        this.veiculo = veiculo;
    }
    
    public Veiculo getVeiculo(){
        return this.veiculo;
    }
    
    public List<Veiculo> getVeiculos(){
        return this.veiculos;
    }
    
    public Integer contagem(){
        return fachada.contagem();
    }
    //Parte 2 - CRUD
    public String inserir(){
        fachada.inserir(this.veiculo);
        Mensagem.mostrarMensagemSucesso("Sucesso!","Veiculo inserido com sucesso!");
        recuperarVeiculos();
        return montarPaginaParaListarVeiculo();
    }
    
    public String alterar(){
        fachada.alterar(this.getVeiculo());
        recuperarVeiculos();
        Mensagem.mostrarMensagemSucesso("Sucesso!","Veiculo alterado com sucesso!");
      return montarPaginaParaListarVeiculo();
    }
    
    public void excluir(){
        fachada.remover(this.veiculo);
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("exclui.hide()");
        Mensagem.mostrarMensagemSucesso("Sucesso!","Veiculo excluído com sucesso!");
        montarPaginaParaListarVeiculo();
    }
    
    
    public void recuperarVeiculos(){
        this.veiculos = fachada.listar();
    }
  
    
    public String formataData(Date data){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
    
    
    
    //Parte 3 - Chamadas de Tela
    public String montarPaginaParaCadastrarVeiculo(){
        this.veiculo = new Veiculo();
        Date data = new Date();
        this.veiculo.setAtivo(true);
        this.veiculo.setDataInclusao(data);
        return "/componentes/veiculo/CadastroVeiculo";
    }
    
    public String montarPaginaParaAlterarVeiculo(){
        return "/componentes/veiculo/AlterarVeiculo";
    }
    
    public String montarPaginaParaListarVeiculo(){
        this.recuperarVeiculos();
        return "/componentes/veiculo/ListarVeiculo";
    }
    
    
    public List<SelectItem> getTipoCombustiveis() {
        List<SelectItem> tiposCombustiveis = new ArrayList<SelectItem>();
        for (TipoCombustivel tc : TipoCombustivel.values()) {
            tiposCombustiveis.add(new SelectItem(tc.name(),tc.toString()));
        }
        return tiposCombustiveis;
    }
    
    public List<SelectItem> getCategoriasVeiculos() {
        List<SelectItem> categoriasVeiculos = new ArrayList<SelectItem>();
        List<CategoriaVeiculo> categorias = categoriaFachada.listar();
        
        for (CategoriaVeiculo cv : categorias) {
            categoriasVeiculos.add(new SelectItem(cv,cv.getDescricaoCategoria()));
        }
        return categoriasVeiculos;
    }
   
       
    
}

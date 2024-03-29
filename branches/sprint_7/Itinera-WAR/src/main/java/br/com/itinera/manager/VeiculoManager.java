/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itinera.manager;

import br.com.itinera.enuns.TipoCombustivel;
import br.com.itinera.fachada.CategoriaVeiculoFachada;
import br.com.itinera.fachada.VeiculoFachada;
import br.com.itinera.ferramentas.Mensagem;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import br.com.itinera.modelo.CategoriaVeiculo;
import br.com.itinera.modelo.Veiculo;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author LeticiaSena
 */
@ManagedBean(name = "veiculoManager")
@SessionScoped
public class VeiculoManager implements Serializable{
    private Veiculo antigo;
    private Veiculo veiculo;
    private List<Veiculo> veiculos = new ArrayList<Veiculo>();
    private Boolean tabVisivelComCombustivel;
    private Boolean tabVisivelSemCombustivel; 
    private String mensagemAlteracao;

    
    @EJB
    private VeiculoFachada fachada;
    
    @EJB 
    private CategoriaVeiculoFachada categoriaFachada;
    
    //Parte 1 - GETs e SETs
    public VeiculoManager(){
        antigo = new Veiculo();
    }
    
    public void setVeiculo(Veiculo veiculo){
        this.veiculo = veiculo;
    }
    
    public Veiculo getVeiculo(){
        return this.veiculo;
    }
    
    public String getMensagemAlteracao() {
        return mensagemAlteracao;
    }

    public void setMensagemAlteracao(String mensagemAlteracao) {
        this.mensagemAlteracao = mensagemAlteracao;
    }
    
    public boolean getTabVisivelComCombustivel(){
        return this.tabVisivelComCombustivel;
    }
    
    public boolean getTabVisivelSemCombustivel(){
        return this.tabVisivelSemCombustivel;
    }
    
    public List<Veiculo> getVeiculos(){
        return this.veiculos;
    }
    
    public void combustivelVisualizar(AjaxBehaviorEvent abe){
       CategoriaVeiculo selected = categoriaFachada.recuperarPorId(veiculo.getIdCategoriaVeiculo().getIdCategoriaVeiculo());
       if(selected.isReboqueSemiReboque()){
        this.tabVisivelComCombustivel = false;
        this.tabVisivelSemCombustivel = true;
       }else{
        this.tabVisivelComCombustivel = true;
        this.tabVisivelSemCombustivel = false;
       }
               
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
         if(verificaAlteracao()){
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("altera.show()");
            return "";
        }
        else{
            return realizarAlteracao();
        }
        
    }
    
    public String realizarAlteracao(){
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
        this.tabVisivelComCombustivel = false;
        this.tabVisivelSemCombustivel = false;
        this.veiculo = new Veiculo();
        Date data = new Date();
        this.veiculo.setAtivo(true);
        this.veiculo.setDataInclusao(data);
        return "/componentes/veiculo/CadastroVeiculo";
    }
    
    public String montarPaginaParaAlterarVeiculo(){
        antigo = new Veiculo();
        antigo.setPlacaVeiculo(veiculo.getPlacaVeiculo());
        antigo.setMarcaVeiculo(veiculo.getMarcaVeiculo());
        antigo.setModeloVeiculo(veiculo.getModeloVeiculo());
        antigo.setIdCategoriaVeiculo(veiculo.getIdCategoriaVeiculo());
        
        if(this.veiculo.getIdCategoriaVeiculo().isReboqueSemiReboque()){
            this.tabVisivelComCombustivel = false;
            this.tabVisivelSemCombustivel = true;
        }else{
            this.tabVisivelComCombustivel = true;
            this.tabVisivelSemCombustivel = false;
        }
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
   
   public String converterBooleanTexto(Boolean ativo){
       return ativo?"Ativo":"Inativo";
       
   }
   
   public String converterNumerico(String valor){
       valor = valor.replace(",", ".");
       return valor;
   }
  
    
   public SelectItem[] getListaOpcoesAtivoInativo(){
       SelectItem[] opcoes = new SelectItem[3];
       opcoes[0] = new SelectItem("","Selecione");
       opcoes[1] = new SelectItem("True","Ativo");
       opcoes[2] = new SelectItem("False","Inativo");
       return opcoes;
   }

    private boolean verificaAlteracao() {
        veiculo.setIdCategoriaVeiculo(this.categoriaFachada.recuperarPorId(veiculo.getIdCategoriaVeiculo().getIdCategoriaVeiculo()));
        this.mensagemAlteracao = "";
        if(!antigo.getPlacaVeiculo().equals(veiculo.getPlacaVeiculo())){
            this.mensagemAlteracao += "Placa: "+ veiculo.getPlacaVeiculo() + " ("+antigo.getPlacaVeiculo()+")<br>";
        }
        if(!antigo.getMarcaVeiculo().equals(veiculo.getMarcaVeiculo())){
            this.mensagemAlteracao += "Marca: "+ veiculo.getMarcaVeiculo() + " ("+antigo.getMarcaVeiculo()+")<br>";
        }
        if(!antigo.getModeloVeiculo().equals(veiculo.getModeloVeiculo())){
            this.mensagemAlteracao += "Modelo: "+ veiculo.getModeloVeiculo() + " ("+antigo.getModeloVeiculo()+")<br>";
        }
        if(!(antigo.getIdCategoriaVeiculo().getIdCategoriaVeiculo() == veiculo.getIdCategoriaVeiculo().getIdCategoriaVeiculo())){
            
            this.mensagemAlteracao += "Categoria: "+ veiculo.getIdCategoriaVeiculo() + " ("+antigo.getIdCategoriaVeiculo()+")<br>";
        }
        
        return !this.mensagemAlteracao.isEmpty();
    }
}

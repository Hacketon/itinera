
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

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
   private BigDecimal filtroNotaFiscal;
   private BigDecimal valorTotal;
   private boolean inserindo;
   private OrdemColeta ordemColetaAnterior;
   private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

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

    public BigDecimal getFiltroNotaFiscal() {
        return filtroNotaFiscal;
    }

    public void setFiltroNotaFiscal(BigDecimal filtroNotaFiscal) {
        this.filtroNotaFiscal = filtroNotaFiscal;
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
        this.setInserindo(true);
        this.ordemColeta = new OrdemColeta();
        return "/componentes/ordemColeta/CadastroOrdemColeta";
    }
    
    public String montarPaginaAlterar(){
        this.ordemColetaAnterior = new OrdemColeta();
        this.ordemColetaAnterior.setMotoristaId(this.ordemColeta.getMotoristaId());
        this.ordemColetaAnterior.setVeiculoId(this.ordemColeta.getVeiculoId());
        this.ordemColetaAnterior.setRemetenteId(this.ordemColeta.getRemetenteId());
        this.ordemColetaAnterior.setDestinatarioId(this.ordemColeta.getDestinatarioId());
        this.ordemColetaAnterior.setQuantidade(this.ordemColeta.getQuantidade());
        this.ordemColetaAnterior.setValorUnitario(this.ordemColeta.getValorUnitario());
        this.inserindo = false;
        return "/componentes/ordemColeta/CadastroOrdemColeta";
    }
    
    public String inserir(){
        try{
//            imprimirOrdem();
            fachada.inserir(ordemColeta);
            Mensagem.mostrarMensagemSucesso("Sucesso!", "Ordem de Coleta inserida com sucesso!");
            return montarPaginaListagem();
        }catch(Exception e){
            Mensagem.mostrarMensagemErro("Erro!",e.getLocalizedMessage());
            return "";
        }
    }
    
    public void verificarAlteracao(){
        mensagem = "";
        //Verifica se realmente deve alterar a página
        if(this.ordemColeta.getMotoristaId().getMotoristaId().intValue() != this.ordemColetaAnterior.getMotoristaId().getMotoristaId().intValue()){
            mensagem += "MOTORISTA: " + this.ordemColeta.getMotoristaId() + "(" + this.ordemColetaAnterior.getMotoristaId() + ")<br>";
        }
        if(this.ordemColeta.getVeiculoId().getIdVeiculo().intValue() != this.ordemColetaAnterior.getVeiculoId().getIdVeiculo().intValue()){
            mensagem += "VEÍCULO: "+ this.ordemColeta.getVeiculoId() + "(" + this.ordemColetaAnterior.getVeiculoId() + ")<br>";
        }
        if(this.ordemColeta.getRemetenteId().getIdEmpresa().intValue() != this.ordemColetaAnterior.getRemetenteId().getIdEmpresa().intValue()){
            mensagem += "REMETENTE: " + this.ordemColeta.getRemetenteId() + "(" + this.ordemColetaAnterior.getRemetenteId() + ")<br>";
        }
        if(this.ordemColeta.getDestinatarioId().getIdEmpresa().intValue() != this.ordemColetaAnterior.getDestinatarioId().getIdEmpresa().intValue()){
            mensagem += "DESTINATÁRIO: " + this.ordemColeta.getDestinatarioId() + "(" + this.ordemColetaAnterior.getDestinatarioId() + ")<br>";
        }
        if(this.ordemColeta.getValorUnitario().doubleValue() != this.ordemColetaAnterior.getValorUnitario().doubleValue()){
            mensagem += "VALOR UNITÁRIO: " + formataMoeda(this.ordemColeta.getValorUnitario().toString()) + "(" + formataMoeda(this.ordemColetaAnterior.getValorUnitario().toString()) + ")<br>";            
        }
        if(this.ordemColeta.getQuantidade().intValue() != this.ordemColetaAnterior.getQuantidade().intValue()){
            mensagem += "QUANTIDADE: " + this.ordemColeta.getQuantidade() + "(" + this.ordemColetaAnterior + ")<br>";
        }
    }
    
    public String alterar(){
        verificarAlteracao();
        if(mensagem.isEmpty()){
            return concluirAlteracao();
        }
        else{
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("altera.show()");
            return "";
        }
    }
    
    public String concluirAlteracao(){
        try{
            fachada.alterar(ordemColeta);
            Mensagem.mostrarMensagemSucesso("Sucesso!", "Ordem de Coleta alterada com sucesso!");
            return montarPaginaListagem();
        }catch(Exception e){
            Mensagem.mostrarMensagemErro("Erro!",e.getLocalizedMessage());
            return "";
        }
    }
    
    public String excluir(){
        try{
            fachada.excluir(ordemColeta);
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("exclui.hide()");
            Mensagem.mostrarMensagemSucesso("Sucesso!", "Ordem de Coleta excluída com sucesso!");
            return this.montarPaginaListagem();
        }catch(Exception e){
            Mensagem.mostrarMensagemErro("Erro!", "Um erro ocorreu: "+ e.getLocalizedMessage());
            return "";
        }
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
        ordemColeta.setValorTotal( 
                BigDecimal.valueOf(
                ordemColeta.getValorUnitario().doubleValue() *
                ordemColeta.getQuantidade().doubleValue())
        );
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
    
    private void imprimirOrdem(){
        System.out.println("IMPRIMIR ORDEM");
        String value = "";
        //value += "ID:" + this.ordemColeta.getOrdemColetaId() + "\n";
        value += "Data: "+ this.ordemColeta.getDataOrdemColeta() + "\n";
        value += "Motorista: " + this.ordemColeta.getMotoristaId()+ "\n";
        value += "Veículo: " + this.ordemColeta.getVeiculoId()+ "\n";
        value += "Remetente: " + this.ordemColeta.getRemetenteId()+ "\n";
        value += "Destinatário: " + this.ordemColeta.getDestinatarioId()+ "\n";
        value += "Rota: " + this.ordemColeta.getRota()+ "\n";
        value += "Quantidade: " + this.ordemColeta.getQuantidade()+ "\n";
        value += "Valor Unitário: "+ this.ordemColeta.getValorUnitario()+ "\n";
        value += "Valor Total: " + this.ordemColeta.getValorTotal()+ "\n";
        value += "Emissão NF: "+ this.ordemColeta.getDataEmissaoNf()+ "\n";
        value += "NF: " + this.ordemColeta.getNumeroNota()+ "\n";
        System.out.println(value);
    }
}

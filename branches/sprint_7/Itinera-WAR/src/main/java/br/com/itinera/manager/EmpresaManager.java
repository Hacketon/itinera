package br.com.itinera.manager;

import br.com.itinera.enuns.TipoEmpresa;
import br.com.itinera.fachada.EmpresaFachada;
import br.com.itinera.ferramentas.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import br.com.itinera.modelo.Endereco;
import br.com.itinera.modelo.Empresa;
import br.com.itinera.modelo.Email;
import br.com.itinera.modelo.EmpresaResponsavel;
import br.com.itinera.modelo.Telefone;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Marcelo
 * alterado por Ivens
 */
@ManagedBean(name = "empresaManager")
@SessionScoped
public class EmpresaManager implements Serializable {
    private boolean inserindo;
    private Empresa empresa;
    private Telefone telefone;
    private Email empresaEmail;
    private EmpresaResponsavel empresaResp;
    private Endereco endereco;
    private List<Empresa> empresas = new ArrayList<Empresa>();
    @EJB
    private EmpresaFachada fachada;

      public EmpresaManager() {
        telefone = new Telefone();
        empresaResp = new EmpresaResponsavel();
        empresaEmail = new Email();
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public List<Empresa> getEmpresas() {
        return this.empresas;
    }
    public Integer contagem(){
        return fachada.contagem();
    }


    public void salvar() throws Exception {
        try {   
        if(this.empresa.getIdEmpresa() == null){ 
                fachada.salvar(empresa);
                 Mensagem.mostrarMensagemSucesso("Sucesso!", "Empresa inserida com sucesso!");
           }
            else{
                fachada.alterar(empresa);
                 Mensagem.mostrarMensagemSucesso("Sucesso!", "Empresa alterada com sucesso!");
            }
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro("Erro!", "Problema ao finalizar registro. " + e.getMessage());
        }
    }
            
    public void excluir() {
        try {
            fachada.remover(this.empresa);
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("exclui.hide()");
            Mensagem.mostrarMensagemSucesso("Sucesso!", "Empresa excluída com sucesso!");
            listarEmpresa();
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro("Erro!", "Problema ao excluir registro. " + e.getMessage());
        }
    }
    
    public void onEdit(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo", "ok");
        Mensagem.mostrarMensagem("Editar.", "Registro Alterado");
    }  
      
    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelado", "!!!");
        Mensagem.mostrarMensagem("Cancelar.", "Alteração Cancelada");
    }
    
    public void recuperarEmpresas() {
        this.empresas = fachada.listar();
    }

    public String salvarEmpresa(Empresa fornecedor) {
        if (fornecedor == null) {
            this.inserindo = true;
            this.empresa = new Empresa();
        }else{
            this.inserindo = false;
        }
        return "/componentes/empresa/AlterarEmpresa";
    }

    public String listarEmpresa() {
        this.recuperarEmpresas();
        return "/componentes/empresa/ListarEmpresa";
    }
    
    public void novoEmail() {
        try {
            empresaEmail = new Email();
            if (empresa.getEmpresaEmailList() == null) {
                List<Email> lstEmpresaEmail = new ArrayList<Email>();
                empresa.setEmpresaEmailList(lstEmpresaEmail);
            }
            empresa.getEmpresaEmailList().add(empresaEmail);
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro("Erro!", "Problema ao adicionar e-mail. " + e.getMessage());
        }
    }
    
    public void novoTelefone() {
        try {
            telefone = new Telefone();
            telefone.setTelefoneTipo('F');
            if (empresa.getEmpresaTelefoneList()== null) {
                List<Telefone> lstTelefone = new ArrayList<Telefone>();
                empresa.setEmpresaTelefoneList(lstTelefone);
            }
            empresa.getEmpresaTelefoneList().add(telefone);
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro("Erro!", "Problema ao adicionar telefone. " + e.getMessage());
        }
        
    }
    
    public void novoResponsavel() {
        try {
            empresaResp = new EmpresaResponsavel();
            empresaResp.setIdEmpresa(this.getEmpresa());
            if (empresa.getEmpresaResponsavelList() == null) {
                List<EmpresaResponsavel> lstEmpresaResp = new ArrayList<EmpresaResponsavel>();
                empresa.setEmpresaResponsavelList(lstEmpresaResp);
            }
            empresa.getEmpresaResponsavelList().add(empresaResp);
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro("Erro!", "Problema ao adicionar responsável. " + e.getMessage());
        }
    }
    
    public void excluirEmail(Email empEmail) {
        empresa.getEmpresaEmailList().remove(empEmail);
    }
    
    public void excluirTelefone(Telefone empTel) {
        empresa.getEmpresaTelefoneList().remove(empTel);
    }
    
    public void excluirResponsavel(EmpresaResponsavel fornResp) {
        empresa.getEmpresaResponsavelList().remove(fornResp);
    }

    public Telefone getEmpresaTelefone() {
        return telefone;
    }

    public void setEmpresaTelefone(Telefone empresaTelefone) {
        this.telefone = empresaTelefone;
    }

    public Email getEmpresaEmail() {
        return empresaEmail;
    }

    public void setEmpresaEmail(Email empresaEmail) {
        this.empresaEmail = empresaEmail;
    }

    public EmpresaResponsavel getEmpresaResp() {
        return empresaResp;
    }

    public void setEmpresaResp(EmpresaResponsavel fornecedorResp) {
        this.empresaResp = fornecedorResp;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
       public List<SelectItem> getTipoEmpresa() {
        List<SelectItem> tipoEmpresa = new ArrayList<SelectItem>();
        for (TipoEmpresa te : TipoEmpresa.values()) {
            tipoEmpresa.add(new SelectItem(te.name(),te.toString()));
        }
        return tipoEmpresa;
    }
}
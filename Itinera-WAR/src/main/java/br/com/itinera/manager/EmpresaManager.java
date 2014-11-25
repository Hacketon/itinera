package br.com.itinera.manager;

import br.com.itinera.enuns.TipoEmpresa;
import br.com.itinera.enuns.TipoLogradouro;
import br.com.itinera.fachada.EmpresaFachada;
import br.com.itinera.fachada.MunicipioFachada;
import br.com.itinera.ferramentas.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import br.com.itinera.modelo.Endereco;
import br.com.itinera.modelo.Empresa;
import br.com.itinera.modelo.Email;
import br.com.itinera.modelo.EmpresaResponsavel;
import br.com.itinera.modelo.Municipio;
import br.com.itinera.modelo.Telefone;
import javax.persistence.EntityExistsException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Marcelo alterado por Ivens
 */
@ManagedBean(name = "empresaManager")
@SessionScoped
public class EmpresaManager implements Serializable {

    private boolean inserindo;
    private Empresa empresaAntiga;
    private String mensagemAlteracao;
    private Empresa empresa;
    private Telefone telefone;
    private Email empresaEmail;
    private EmpresaResponsavel empresaResp;
    private Endereco endereco;
    private List<Empresa> empresas = new ArrayList<Empresa>();
    @EJB
    private EmpresaFachada fachada;
    @EJB
    private MunicipioFachada municipioFachada;

    public EmpresaManager() {
        telefone = new Telefone();
        empresaResp = new EmpresaResponsavel();
        empresaEmail = new Email();
    }

    public String getMensagemAlteracao() {
        return this.mensagemAlteracao;
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
    
    public boolean getEnderecoObrigatorio(){
        return true;
    }

    public Integer contagem() {
        return fachada.contagem();
    }

    public String salvar() {
        try {
            if (this.empresa.getIdEmpresa() == null) {
                fachada.salvar(empresa);
                Mensagem.mostrarMensagemSucesso("Sucesso!", "Empresa inserida com sucesso!");
                return montarPaginaListarEmpresa();
            } else {
                if (verificaAlteracao()) {
                    RequestContext rc = RequestContext.getCurrentInstance();
                    rc.execute("altera.show()");
                } else {
                    return alterar();
                }
            }
        } catch (EntityExistsException e) {
            Mensagem.mostrarMensagemErro("Uma empresa com este cnpj já foi inserida. Por favor, verifique!", "Uma empresa com este cnpj já foi inserida. Por favor, verifique!");
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro("Problema ao finalizar registro. " + e.getMessage(), "Problema ao finalizar registro. " + e.getMessage());
        }
        return "";
    }

    public String alterar() {
        try {
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("altera.hide()");
            fachada.alterar(empresa);
            Mensagem.mostrarMensagemSucesso("Sucesso!", "Empresa alterada com sucesso!");
            return montarPaginaListarEmpresa();
        } catch (EntityExistsException e) {
            Mensagem.mostrarMensagemErro("Uma empresa com este cnpj já foi inserida. Por favor, verifique!", "Uma empresa com este cnpj já foi inserida. Por favor, verifique!");
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro("Um erro inesperado aconteceu." + e.getMessage(), "Um erro inesperado aconteceu." + e.getMessage());
        }
        return "";
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

    public void recuperarEmpresas() {
        this.empresas = fachada.listar();
    }

    public String salvarEmpresa(Empresa fornecedor) {
        if (fornecedor == null) {
            this.inserindo = true;
            this.empresa = new Empresa();
        } else {
            this.inserindo = false;
            this.empresaAntiga = new Empresa();
            this.empresaAntiga.setTipo(this.empresa.getTipo());
            this.empresaAntiga.setRazaoSocial(this.empresa.getRazaoSocial());
            this.empresaAntiga.setCnpj(this.empresa.getCnpj());
            this.empresaAntiga.setNomeFantasia(this.empresa.getNomeFantasia());
            this.empresaAntiga.setIe(this.empresa.getIe());
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
            if (empresa.getEmpresaTelefoneList() == null) {
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
            tipoEmpresa.add(new SelectItem(te.name(), te.toString()));
        }
        return tipoEmpresa;
    }

    public List<SelectItem> getTipoEmpresaOpcoes() {
        List<SelectItem> tipoEmpresa = new ArrayList<SelectItem>();
        tipoEmpresa.add(new SelectItem("", "Selecione"));
        for (TipoEmpresa te : TipoEmpresa.values()) {
            tipoEmpresa.add(new SelectItem(te.name(), te.toString()));
        }
        return tipoEmpresa;
    }

    public String converterTipo(String tipoChar) {
        TipoEmpresa tipo = TipoEmpresa.valueOf(tipoChar);
        return tipo.toString();
    }

    private boolean verificaAlteracao() {
        this.mensagemAlteracao = "";
        if (!this.empresaAntiga.getTipo().equals(this.empresa.getTipo())) {

            this.mensagemAlteracao += "Tipo de Empresa: " + converterTipo(this.empresa.getTipo().toString()) + " (" + converterTipo(this.empresaAntiga.getTipo().toString()) + ")<br>";
        }
        if (!this.empresaAntiga.getCnpj().equals(this.empresa.getCnpj())) {
            this.mensagemAlteracao += "CNPJ: " + this.empresa.getCnpj() + " (" + this.empresaAntiga.getCnpj() + ")<br>";
        }
        if (!this.empresaAntiga.getNomeFantasia().equals(this.empresa.getNomeFantasia())) {
            this.mensagemAlteracao += "Nome Fantasia: " + this.empresa.getNomeFantasia() + " (" + this.empresaAntiga.getNomeFantasia() + ")<br>";
        }
        if (!this.empresaAntiga.getRazaoSocial().equals(this.empresa.getRazaoSocial())) {
            this.mensagemAlteracao += "Razão Social: " + this.empresa.getRazaoSocial() + " (" + this.empresaAntiga.getRazaoSocial() + ")<br>";
        }
        if (!this.empresaAntiga.getIe().equals(this.empresa.getIe())) {
            this.mensagemAlteracao += "Inscrição Estadual: " + this.empresa.getIe() + " (" + this.empresaAntiga.getIe() + ")<br>";
        }
        return !this.mensagemAlteracao.isEmpty();
    }

    public List<SelectItem> getTipoLogradouro() {
        List<SelectItem> tipoLogrdouro = new ArrayList<SelectItem>();
        for (TipoLogradouro te : TipoLogradouro.values()) {
            tipoLogrdouro.add(new SelectItem(te.name(), te.toString()));
        }
        return tipoLogrdouro;
    }

    public List<Municipio> completeMunicipio(String query) {
        List<Municipio> suggestions = new ArrayList<Municipio>();
        for (Municipio p : municipioFachada.listMunicipios()) {
            if (p.getNomeMunicipio().toUpperCase().contains(query.toUpperCase())) {
                suggestions.add(p);
            }
        }

        return suggestions;
    }

    private String montarPaginaListarEmpresa() {
        this.recuperarEmpresas();
        return "/componentes/empresa/ListarEmpresa";
    }

}

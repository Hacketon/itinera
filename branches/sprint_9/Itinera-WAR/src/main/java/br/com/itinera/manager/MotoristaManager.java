
package br.com.itinera.manager;

import br.com.itinera.enuns.TipoLogradouro;
import br.com.itinera.fachada.MotoristaFachada;
import br.com.itinera.fachada.MunicipioFachada;
import br.com.itinera.ferramentas.Mensagem;
import br.com.itinera.interfaces.CRUD;
import br.com.itinera.interfaces.MontarPaginas;
import br.com.itinera.modelo.Email;
import br.com.itinera.modelo.Endereco;
import br.com.itinera.modelo.Motorista;
import br.com.itinera.modelo.Municipio;
import br.com.itinera.modelo.Telefone;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.persistence.EntityExistsException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author marcelo
 */
@ManagedBean(name="motoristaManager")
@SessionScoped
public class MotoristaManager implements Serializable, CRUD, MontarPaginas {
    
    private Motorista motorista;
//    private Telefone telefone;
//    private Endereco endereco;
//    private Email email;
    private List<Motorista> motoristas;
//    private boolean operacao;
    
    @EJB
    private MotoristaFachada fachada;
//    @EJB
//    private MunicipioFachada municipioFachada;
    
    public MotoristaManager() {
//        telefone = new Telefone();
//        email = new Email();
    }
    
    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public List<Motorista> getMotoristas() {
        return motoristas;
    }

    public void setMotoristas(List<Motorista> motoristas) {
        this.motoristas = motoristas;
    }

//    public void setEndereco(Endereco endereco) {
//        this.endereco = endereco;
//    }
//
//    public Endereco getEndereco() {
//        return endereco;
//    }

    private void recuperarMotorista() {
        this.setMotoristas(fachada.listarTodos());
    }

    public String alterar() {
        try {
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("altera.hide()");
            fachada.alterar(motorista);
            Mensagem.mostrarMensagemSucesso(Mensagem.sucesso, "Motorista alterado com sucesso!");
            return montarPaginaParaListar();
        } catch (EntityExistsException e) {
            Mensagem.mostrarMensagemErro(Mensagem.duplicidade, "Um motorista com este cpf já foi inserido. Por favor, verifique!");
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro(Mensagem.erro, Mensagem.erroCritico + e.getMessage());
        }
        return "";
    }

    public void excluir() {
        fachada.excluir(this.getMotorista());
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("exclui.hide()");
        Mensagem.mostrarMensagemSucesso(Mensagem.sucesso, "O motorista foi excluído com sucesso.");
        this.motorista = new Motorista();
        this.recuperarMotorista();
    }
    
    public void listar() {
        montarPaginaParaListar();
    }

    @Override
    public String montarPaginaParaListar() {
        this.recuperarMotorista();
        return "/componentes/motorista/ListarMotorista";
    }

    @Override
    public String montarPaginaParaCadastro() {
        this.motorista = new Motorista();
        return montarPaginaParaAlterar();
    }

    @Override
    public String montarPaginaParaAlterar() {
//        validarOperacao();
        return "/componentes/motorista/AlterarMotorista";
    }

    @Override
    public void recuperar() {
        
    }

    @Override
    public String inserir() {
        try {
            if (this.motorista.getMotoristaId()== null) {
                //fachada.salvar(motorista);
                Mensagem.mostrarMensagemSucesso(Mensagem.sucesso, "Motorista inserido com sucesso!");
            } else {
//                if (verificaAlteracao()) {
//                    RequestContext rc = RequestContext.getCurrentInstance();
//                    rc.execute("altera.show()");
//                } else {
                    alterar();
//                }
            }
        } catch (EntityExistsException e) {
            Mensagem.mostrarMensagemErro(Mensagem.duplicidade, "Um motorista com este cpf já foi inserido. Por favor, verifique!");
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro(Mensagem.erro, Mensagem.erroCritico + e.getMessage());
        }
        return "/componentes/motorista/ListarMotorista";
    }

//    private void validarOperacao() {
//        this.operacao = (this.motorista.getMotoristaId() > null) ? true : false;
//        System.out.println("op validar " + this.operacao + " id " + this.motorista.getMotoristaId());
//    }
//    
//    public boolean retornaOperacao() {
//        System.out.println("op retorna " + this.operacao);
//        return this.operacao;
//    }


//    public List<SelectItem> getTipoLogradouro() {
//        List<SelectItem> tipoLogrdouro = new ArrayList<SelectItem>();
//        for (TipoLogradouro te : TipoLogradouro.values()) {
//            tipoLogrdouro.add(new SelectItem(te.name(), te.toString()));
//        }
//        return tipoLogrdouro;
//    }

//    public List<Municipio> completeMunicipio(String query) {
//        List<Municipio> suggestions = new ArrayList<Municipio>();
//        for (Municipio p : municipioFachada.listMunicipios()) {
//            if (p.getNomeMunicipio().toUpperCase().contains(query.toUpperCase())) {
//                suggestions.add(p);
//            }
//        }
//
//        return suggestions;
//    }    
    
}


package br.com.itinera.manager;

import br.com.itinera.fachada.MotoristaFachada;
import br.com.itinera.ferramentas.Mensagem;
import br.com.itinera.interfaces.CRUD;
import br.com.itinera.interfaces.MontarPaginas;
import br.com.itinera.modelo.Email;
import br.com.itinera.modelo.Motorista;
import br.com.itinera.modelo.Telefone;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    private Motorista motoristaAntigo;
    private List<Motorista> motoristas;
    private String mensagemAlteracao;

    private boolean inserindo;
    private Telefone telefone;
    private Email email;
    
    @EJB
    private MotoristaFachada fachada;
    
    public MotoristaManager() {
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

    private void recuperarMotorista() {
        this.setMotoristas(fachada.listarTodos());
    }
    
    public String getMensagemAlteracao() {
        return this.mensagemAlteracao;
    }

    public void setMensagemAlteracao(String mensagemAlteracao) {
        this.mensagemAlteracao = mensagemAlteracao;
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
        return montarPaginaParaListar();
    }
    
    public String salvarMotorista(Motorista motorista) {
        if (motorista == null) {
            this.inserindo = true;
            this.motorista = new Motorista();
        } else {
            this.inserindo = false;
            this.motoristaAntigo = new Motorista();
            this.motoristaAntigo.setCpf(this.motorista.getCpf());
            this.motoristaAntigo.setNome(this.motorista.getNome());
        }
        return this.montarPaginaParaAlterar();
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
    
    public void novoTelefone() {
        try {
            telefone = new Telefone();
            telefone.setTelefoneTipo('F');
            if (motorista.getTelefoneList() == null) {
                List<Telefone> lstTelefone = new ArrayList<Telefone>();
                motorista.setTelefoneList(lstTelefone);
            }
            motorista.getTelefoneList().add(telefone);
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro("Erro!", "Problema ao adicionar telefone. " + e.getMessage());
        }

    }
    
    public void excluirTelefone(Telefone tel) {
        motorista.getTelefoneList().remove(tel);
    }
    
    public void novoEmail() {
        try {
            email = new Email();
            if (motorista.getEmailList() == null) {
                List<Email> lstEmail = new ArrayList<Email>();
                motorista.setEmailList(lstEmail);
            }
            motorista.getEmailList().add(email);
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro(Mensagem.erro, "Problema ao adicionar telefone. " + e.getMessage());
        }
    }
    
    public void excluirEmail(Email email) {
        motorista.getEmailList().remove(email);
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
                fachada.salvar(motorista);
                Mensagem.mostrarMensagemSucesso(Mensagem.sucesso, "Motorista inserido com sucesso!");
                return montarPaginaParaListar();
            } else {
                if (verificaAlteracao()) {
                    RequestContext rc = RequestContext.getCurrentInstance();
                    rc.execute("altera.show()");
                } else {
                    return alterar();
                }
            }
        } catch (EntityExistsException e) {
            Mensagem.mostrarMensagemErro(Mensagem.duplicidade, "Um motorista com este cpf já foi inserido. Por favor, verifique!");
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro(Mensagem.erro, Mensagem.erroCritico + e.getMessage());
        }
        return "";
    }

    private boolean verificaAlteracao() {
        setMensagemAlteracao("");
        if (!this.motoristaAntigo.getCpf().equals(this.motorista.getCpf())) {
            setMensagemAlteracao(getMensagemAlteracao() + "CPF: " + this.motorista.getCpf() + " (" + this.motoristaAntigo.getCpf() + ")<br>" );
        }
        
        if (!this.motoristaAntigo.getNome().equals(this.motorista.getNome())){
            setMensagemAlteracao(getMensagemAlteracao() + "NOME: "+this.motorista.getNome() + " (" + this.motoristaAntigo.getNome() + ") <br>" );
        }
        
        return !getMensagemAlteracao().isEmpty();
    }
    
}

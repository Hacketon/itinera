package br.com.itinera.fachada;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.itinera.modelo.Email;
import br.com.itinera.modelo.Empresa;
import br.com.itinera.modelo.EmpresaResponsavel;
import br.com.itinera.modelo.Telefone;
import br.com.itinera.persistencia.EmailDAO;
import br.com.itinera.persistencia.EmpresaDAO;
import br.com.itinera.persistencia.TelefoneDAO;

/**
 *
 * @author Marcelo
 */
@Stateless
public class EmpresaFachada {
    @EJB
    private EmpresaDAO dao;
    @EJB
    private EmailDAO emailDao;
    @EJB
    private TelefoneDAO telefoneDao;
    
    public void salvar (Empresa empresa) throws Exception{
        this.verificarSeNovaEmpresaJaExisteComMesmoCnpj(empresa.getCnpj());
        this.verificarSeExisteResponsavelSemPreenchimento(empresa);
        this.verificarSeExisteEmailSemPreenchimento(empresa);
        this.verificarSeExisteTelefoneSemPreenchimento(empresa);
        this.verificarSeEmailInformadoJaExisteTabelaEmail(empresa);
        this.verificarSeTelefoneInformadoJaExisteTabelaTelefone(empresa);
        dao.inserir(empresa);
        
        /*if(empresa.getEndereco() != null){
            salvarPontoColeta(empresa);
        }
        * */
    }
    
    public void alterar(Empresa empresa) throws Exception{
        this.verificarSeExisteResponsavelSemPreenchimento(empresa);
        this.verificarSeExisteEmailSemPreenchimento(empresa);
        this.verificarSeExisteTelefoneSemPreenchimento(empresa);
        this.verificarSeEmailInformadoJaExisteTabelaEmail(empresa);
        this.verificarSeTelefoneInformadoJaExisteTabelaTelefone(empresa);
        dao.alterar(empresa);
    }
    
    public void remover(Empresa empresa){
        dao.remover(empresa);
    }
    
    public List<Empresa> listar(){
        return dao.listar();
    }
    
    public List<Telefone> listarTelefone(){
        return dao.listarTelefone();
    }
    
    public Integer contagem(){
        return dao.listar().size();
                
    }
    
    //Busca as empresas por nome de Município ou Bairro ou Rua
    public List<Empresa> buscarPorEndereco(String valorDeBusca){ 
        return dao.listarBuscaPorEndereco(valorDeBusca);
    }
    
    public List<Empresa> buscarPorNome(String valorDeBusca){
        return dao.listarBuscaPorNome(valorDeBusca);
    }
    
    private void verificarSeNovaEmpresaJaExisteComMesmoCnpj(String cnpjEmpresa) throws Exception{
        if (dao.verificarSeNovaEmpresaJaExisteComMesmoCnpj(cnpjEmpresa)){
            throw  new Exception("Já existe outra empresa cadastrada com esse CNPJ, verifique!");
        }
    }

    private void verificarSeEmailInformadoJaExisteTabelaEmail(Empresa empresa) {
        List<Email> newListEmail = new ArrayList<Email>();        
        
        if (empresa.getEmpresaEmailList() != null && !empresa.getEmpresaEmailList().isEmpty()){
            for (Iterator<Email> it = empresa.getEmpresaEmailList().iterator(); it.hasNext();) {
                Email newEmail = emailDao.retornaEmailInformadoQueJaExisteTabelaEmail(it.next());
                newListEmail.add(newEmail);
            }        
            empresa.setEmpresaEmailList(newListEmail);
        }
    }
    
    private void verificarSeTelefoneInformadoJaExisteTabelaTelefone(Empresa empresa) {
        List<Telefone> newListTelefone = new ArrayList<Telefone>();
        List<Telefone> oldListTelefone = empresa.getEmpresaTelefoneList();
        
        if (empresa.getEmpresaTelefoneList() != null && !empresa.getEmpresaTelefoneList().isEmpty()){
            for (Iterator<Telefone> it = oldListTelefone.iterator(); it.hasNext();) {
                Telefone newTelefone = telefoneDao.retornaTelefoneInformadoQueJaExisteTabelaEmail(it.next());
                newListTelefone.add(newTelefone);
            }        
            empresa.setEmpresaTelefoneList(newListTelefone);
        }
    }

    private void verificarSeExisteResponsavelSemPreenchimento(Empresa empresa) {
        List<EmpresaResponsavel> oldListResponsavel = new ArrayList<EmpresaResponsavel>();
        oldListResponsavel = empresa.getEmpresaResponsavelList();
        
        for (Iterator<EmpresaResponsavel> it = oldListResponsavel.iterator(); it.hasNext();){
            EmpresaResponsavel newResponsavel = it.next();
            
            if ( (newResponsavel.getNome() == null || newResponsavel.getNome().equals("") )
                     && (newResponsavel.getCargo() == null || newResponsavel.getCargo().equals(""))) {
                it.remove();
            }
        }
        empresa.setEmpresaResponsavelList(oldListResponsavel);
    }

    private void verificarSeExisteEmailSemPreenchimento(Empresa empresa) {
        List<Email> oldListEmail = new ArrayList<Email>();
        oldListEmail = empresa.getEmpresaEmailList();
        
        for (Iterator<Email> it = oldListEmail.iterator(); it.hasNext();){
            Email newEmail = it.next();
            
            if (newEmail.getEmailEndereco() == null || newEmail.getEmailEndereco().equals("") ) {
                it.remove();
            }
        }
        empresa.setEmpresaEmailList(oldListEmail);
    }

    private void verificarSeExisteTelefoneSemPreenchimento(Empresa empresa) {
        List<Telefone> oldListTelefone = new ArrayList<Telefone>();
        oldListTelefone = empresa.getEmpresaTelefoneList();
        
        for (Iterator<Telefone> it = oldListTelefone.iterator(); it.hasNext();){
            Telefone newTelefone = it.next();
            
            if (newTelefone.getTelefoneNumero() == null || newTelefone.getTelefoneNumero().equals("") ) {
                it.remove();
            }
        }
        empresa.setEmpresaTelefoneList(oldListTelefone);
    }
    
    public List<Empresa> buscarPorTipo(Character tipoEmpresa){
       return dao.listarBuscaPorTipo(tipoEmpresa);
    }
}
package fachada;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Email;
import modelo.Empresa;
import modelo.Telefone;
import persistencia.EmailDAO;
import persistencia.EmpresaDAO;
import persistencia.PontosColetaDAO;
import persistencia.TelefoneDAO;

/**
 *
 * @author Marcelo
 */
@Stateless
public class EmpresaFachada {
    @EJB
    private PontosColetaDAO pontosColetaDAO;
    @EJB
    private EmpresaDAO dao;
    @EJB
    private EmailDAO emailDao;
    @EJB
    private TelefoneDAO telefoneDao;
    
    public void salvar (Empresa empresa) throws Exception{
        this.verificarSeNovaEmpresaJaExisteComMesmoCnpj(empresa.getCnpj());
        this.verificarSeEmailInformadoJaExisteTabelaEmail(empresa);
        this.verificarSeTelefoneInformadoJaExisteTabelaTelefone(empresa);
        dao.inserir(empresa);
        
        /*if(empresa.getEndereco() != null){
            salvarPontoColeta(empresa);
        }
        * */
    }
    
    public void alterar(Empresa empresa) throws Exception{
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
    
   /* private void salvarPontoColeta(Empresa empresa){
        boolean jaexiste = false;
        List<PontoColeta> pontos = pontosColetaDAO.buscaPorEmpresaEOuEndereco(empresa, empresa.getEndereco());
        if(pontos.size() > 0){
         
            //desativar os anteriores.
            for(PontoColeta p: pontos){
                if(empresa.getEndereco().getIdEndereco() == p.getIdEndereco().getIdEndereco()){
                    jaexiste = true;
                    break;
                }else{
                    p.setAtivo(false);
                    pontosColetaDAO.alterar(p);
                }
            }
        }
        
        if(!jaexiste){
        //ao preencher o endereço, salvar automaticamente um ponto de coleta com o mesmo endereço.
        PontoColeta ponto = new PontoColeta();
        ponto.setDescricao(empresa.getNomeFantasia());
        ponto.setAtivo(true);
        ponto.setIdEmpresa(empresa);
        ponto.setIdEndereco(empresa.getEndereco());
        pontosColetaDAO.inserir(ponto);
        }
    }
   */ 
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
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.fachada;

import br.com.itinera.modelo.Email;
import br.com.itinera.modelo.Motorista;
import br.com.itinera.modelo.Telefone;
import br.com.itinera.persistencia.EmailDAO;
import br.com.itinera.persistencia.MotoristaDAO;
import br.com.itinera.persistencia.TelefoneDAO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author lesena
 */
@Stateless
public class MotoristaFachada {
   @EJB
    private MotoristaDAO dao;
   @EJB 
   private EmailDAO emailDao;
   @EJB
   private TelefoneDAO telefoneDao;
   

    
    public List<Motorista> listarTodos(){
        return dao.listar();
    }
    
    public void inserir(Motorista novo){
        this.verificarSeEmailInformadoJaExisteTabelaEmail(novo);
        this.verificarSeTelefoneInformadoJaExisteTabelaTelefone(novo);
        dao.inserir(novo);
    }
    
    public void salvar (Motorista motorista) throws Exception{
        this.verificarSeNovoMotoristaJaExisteComMesmoCpf(motorista.getCpf());
        this.verificarSeEmailInformadoJaExisteTabelaEmail(motorista);
        this.verificarSeTelefoneInformadoJaExisteTabelaTelefone(motorista);
        dao.inserir(motorista);

    }
    
    public void alterar(Motorista alt){
        this.verificarSeEmailInformadoJaExisteTabelaEmail(alt);
        this.verificarSeTelefoneInformadoJaExisteTabelaTelefone(alt);
        dao.alterar(alt);
    }
    
    public void excluir(Motorista rem){
        dao.equals(rem);
    }
    
    private void verificarSeNovoMotoristaJaExisteComMesmoCpf(String cpf) throws Exception{
        if (dao.verificarSeNovoMotoristaJaExisteComMesmoCpf(cpf)){
            throw  new Exception("Já existe outro motorista cadastrado com esse CPF, verifique!");
        }
    }
    
    private void verificarSeEmailInformadoJaExisteTabelaEmail(Motorista motorista) {
        List<Email> newListEmail = new ArrayList<Email>();
        if (motorista.getEmailList()!= null && !motorista.getEmailList().isEmpty()){
            for (Iterator<Email> it = motorista.getEmailList().iterator(); it.hasNext();) {
                Email newEmail = emailDao.retornaEmailInformadoQueJaExisteTabelaEmail(it.next());
                newListEmail.add(newEmail);
            }        
            motorista.setEmailList(newListEmail);
        }
    }
    
    private void verificarSeTelefoneInformadoJaExisteTabelaTelefone(Motorista motorista) {
        List<Telefone> newListTelefone = new ArrayList<Telefone>();
        List<Telefone> oldListTelefone = motorista.getTelefoneList();
        
        if (oldListTelefone != null && !oldListTelefone.isEmpty()){
            for (Iterator<Telefone> it = oldListTelefone.iterator(); it.hasNext();) {
                Telefone newTelefone = telefoneDao.retornaTelefoneInformadoQueJaExisteTabelaEmail(it.next());
                newListTelefone.add(newTelefone);
            }        
            motorista.setTelefoneList(newListTelefone);
        }
    }

}

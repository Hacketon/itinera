/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Email;

/**
 *
 * @author bruno-note
 */
@Stateless
public class EmailDAO {
    
   @PersistenceContext
    private EntityManager em;
    
    public List<Email> listar(){
        return em.createNamedQuery("Empresa.findAll").getResultList();
    }
    
    public Email retornaEmailInformadoQueJaExisteTabelaEmail(Email emailEmpresa){

        List<Email> emailList = em.createNamedQuery("Email.findByEmailEndereco")
                .setParameter("emailEndereco",emailEmpresa.getEmailEndereco()).getResultList();
        if (!emailList.isEmpty()){
           for (Iterator<Email> it = emailList.iterator(); it.hasNext();) {
             emailEmpresa = em.getReference(Email.class, it.next().getEmailId());
           }
              
        }
        return emailEmpresa;
    } 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.persistencia;

import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.itinera.modelo.Telefone;

/**
 *
 * @author bruno-note
 */
@Stateless
public class TelefoneDAO {
    
   @PersistenceContext
    private EntityManager em;
   
    public Telefone retornaTelefoneInformadoQueJaExisteTabelaEmail(Telefone telefoneEmpresa){

        List<Telefone> telefoneList = em.createNamedQuery("Telefone.findByTelefoneNumero")
                .setParameter("telefoneNumero",telefoneEmpresa.getTelefoneNumero()).getResultList();
        if (!telefoneList.isEmpty()){
           for (Iterator<Telefone> it = telefoneList.iterator(); it.hasNext();) {
             telefoneEmpresa = em.getReference(Telefone.class, it.next().getTelefoneId());
           }
              
        }
        return telefoneEmpresa;
    } 
    
}

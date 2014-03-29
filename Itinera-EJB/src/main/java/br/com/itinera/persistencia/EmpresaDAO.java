package br.com.itinera.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.itinera.modelo.Empresa;
import br.com.itinera.modelo.Telefone;

/**
 *
 * @author Marcelo
 */
@Stateless
public class EmpresaDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public void alterar(Empresa empresa) {
        em.merge(empresa);
    }
    
    public void inserir(Empresa empresa) {
        em.persist(empresa);
    }
    
    public void remover(Empresa remover){
        Empresa aSerExcluido = em.merge(remover);
        em.remove(aSerExcluido);
    }
    
    public List<Empresa> listar(){
        return em.createNamedQuery("Empresa.findAll").getResultList();
    }
    
    public List<Telefone> listarTelefone(){
        return em.createNamedQuery("Telefone.findAll").getResultList();
    }
    
    public boolean verificarSeNovaEmpresaJaExisteComMesmoCnpj(String cnpjEmpresa){
        if (!em.createQuery("Select e from Empresa e where e.cnpj = :cnpjEmpresa")
                .setParameter("cnpjEmpresa",cnpjEmpresa).getResultList().isEmpty()){
            return true;
        } 
        
        return false;   
    }
    
    //Busca por Municipio, Bairro ou Rua.
    public List<Empresa> listarBuscaPorEndereco(String valorDeBusca){
        if(valorDeBusca.isEmpty()){
            return null;
        }else{
            valorDeBusca = "%" + valorDeBusca.toLowerCase() + "%";
            List<Empresa> resultadoFinal = new ArrayList<Empresa>();
            List<Empresa> resultadoBuscaMunicipio = 
                    em.createQuery("SELECT e FROM Empresa e WHERE lower(e.endereco.idMunicipio.nomeMunicipio) like :municipioNome")
                    .setParameter("municipioNome", valorDeBusca)
                    .getResultList();
            List<Empresa> resultadoBuscaBairro = 
                    em.createQuery("SELECT e FROM Empresa e WHERE lower(e.endereco.bairro) like :bairro")
                    .setParameter("bairro", valorDeBusca)
                    .getResultList();
            List<Empresa> resultadoBuscaLogradouro = 
                    em.createQuery("SELECT e FROM Empresa e WHERE lower(e.endereco.nomeLogradouro) like  :logradouro")
                    .setParameter("logradouro", valorDeBusca)
                    .getResultList();
            if(resultadoBuscaMunicipio.size() > 0 ){
                for(Empresa emp:resultadoBuscaMunicipio){
                     resultadoFinal.add(emp);
                }
            }
            boolean existe;
            if(resultadoBuscaBairro.size() > 0){
                existe = false;
                for(Empresa emp:resultadoBuscaBairro){
                    for(Empresa emp2:resultadoFinal){
                        if(emp.getIdEmpresa() == emp2.getIdEmpresa()){
                            existe = true;
                            break;
                        }
                    }
                    if(!existe){
                        resultadoFinal.add(emp);
                    }
                }
            }
            if(resultadoBuscaLogradouro.size() > 0){
                for(Empresa emp:resultadoBuscaLogradouro){
                    existe = false;
                    for(Empresa emp2:resultadoFinal){
                        if(emp.getIdEmpresa() == emp2.getIdEmpresa()){
                            existe = true;
                            break;
                        }
                    }
                    if(!existe){
                        resultadoFinal.add(emp);
                    }
                }
            }
            return resultadoFinal;
        }
    }
    
    public List<Empresa> listarBuscaPorNome(String valorDeBusca){
        if(valorDeBusca.isEmpty()){
            return null;
        }else{
            valorDeBusca = "%" + valorDeBusca.toLowerCase() + "%";
            List<Empresa> resultado = new ArrayList<Empresa>();
            
            List<Empresa> resultadoNomeFantasia = em.createQuery("SELECT e FROM Empresa e WHERE lower(e.nomeFantasia) like :nomeFantasia").setParameter("nomeFantasia", valorDeBusca).getResultList();
            List<Empresa> resultadoRazaoSocial = em.createQuery("SELECT e FROM Empresa e WHERE lower(e.razaoSocial) like :razaoSocial").setParameter("razaoSocial", valorDeBusca).getResultList();
            if(resultadoNomeFantasia.size() > 0){
                for(Empresa emp:resultadoNomeFantasia){
                    resultado.add(emp);
                }
            }
            if(resultadoRazaoSocial.size() > 0){
                boolean existe;
                for(Empresa emp:resultadoRazaoSocial){
                   existe = false;
                    for(Empresa emp2:resultado){
                        if(emp.getIdEmpresa() == emp2.getIdEmpresa()){
                            existe = true;
                            break;
                        }
                    }
                    if(!existe){
                        resultado.add(emp);   
                    }
                }
            }
            return resultado;
        }
    }
    
}

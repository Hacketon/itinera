/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;




import fachada.EnderecoFachada;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Endereco;

/**
 *
 * @author Michel
 */
@ManagedBean(name = "enderecoManager")
@SessionScoped
public class EnderecoManager implements Serializable{
    
    private Endereco endereco;
    private List<Endereco> enderecos = new ArrayList<Endereco>();

    @EJB
    private EnderecoFachada fachada;
    
    @EJB 
    private EnderecoFachada enderecoFachada;
    
    //Parte 1 - GETs e SETs
    public EnderecoManager(){}
    
    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }
    
    public Endereco getEndereco(){
        return this.endereco;
    }
    
    public List<Endereco> getEnderecos(){
        return this.enderecos;
    }
    
    //Parte 2 - CRUD
    public void inserir(){}
    
    public void alterar(){}
    
    public void excluir(){}
    
   
  
    //Parte 3 - Chamadas de Tela
    public String montarPaginaParaListarEnderecos(){
        return "/componentes/veiculo/CadastroVeiculo";
    }
 
}

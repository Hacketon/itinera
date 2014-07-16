package br.com.itinera.fachada;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.itinera.modelo.Despesa;
import br.com.itinera.persistencia.DespesaDAO;
import javax.persistence.EntityExistsException;

/**
 *
 * @author marcelo
 */
@Stateless
public class DespesaFachada {
    @EJB
    private DespesaDAO dao;
    
    public void inserir(Despesa despesa) throws EntityExistsException, Exception{
        dao.inserir(despesa);
    }
    
    public void alterar(Despesa despesa) throws EntityExistsException, Exception{
        dao.alterar(despesa);
    }
    
    public void remover(Despesa despesa) throws Exception {
        dao.remover(despesa);
    }
    
    public List<Despesa> listar(){
        return dao.listar();
    }
    
    public Despesa buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
    
}
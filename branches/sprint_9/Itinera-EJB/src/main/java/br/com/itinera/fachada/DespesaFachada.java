package br.com.itinera.fachada;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.itinera.modelo.Despesa;
import br.com.itinera.persistencia.DespesaDAO;

/**
 *
 * @author marcelo
 */
@Stateless
public class DespesaFachada {
    @EJB
    private DespesaDAO despesaDAO;
    
    public List<Despesa> listar(){
        return despesaDAO.listar();
    }
    
    public Despesa buscarPorId(int id) {
        return despesaDAO.buscarPorId(id);
    }
    
}
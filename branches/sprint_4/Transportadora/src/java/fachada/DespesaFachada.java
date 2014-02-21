package fachada;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Despesa;
import persistencia.DespesaDAO;

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
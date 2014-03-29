
package br.com.itinera.webservices.soap;

import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import br.com.itinera.fachada.DespesaFachada;

/**
 *
 * @author marcelo
 */
@WebService(serviceName = "Despesa")
public class Despesa {
    @EJB
    private DespesaFachada despesaFachada;
    
    /**
     * @return Lista de Despesas
     */
    @WebMethod(operationName = "listarDespesa")
    public List<br.com.itinera.modelo.Despesa> listar() {
        return despesaFachada.listar();
    }
    
    @WebMethod(operationName = "buscarPorId")
    public br.com.itinera.modelo.Despesa buscarPorId(@WebParam(name = "id") int id) {
        return despesaFachada.buscarPorId(id);
    }
}


package webservice.soap;

import fachada.DespesaFachada;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

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
    public List<modelo.Despesa> listar() {
        return despesaFachada.listar();
    }
    
    @WebMethod(operationName = "buscarPorId")
    public modelo.Despesa buscarPorId(@WebParam(name = "id") int id) {
        return despesaFachada.buscarPorId(id);
    }
}

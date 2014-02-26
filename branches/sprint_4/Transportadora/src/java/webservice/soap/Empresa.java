/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice.soap;

import fachada.EmpresaFachada;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author lesena
 */
@WebService(serviceName = "Empresa")
public class Empresa {
    @EJB
    private EmpresaFachada ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "listar")
    public List<modelo.Empresa> listar() {
        return ejbRef.listar();
    }

    @WebMethod(operationName = "buscarPorEndereco")
    public List<modelo.Empresa> buscarPorEndereco(@WebParam(name = "valorDeBusca") String valorDeBusca) {
        return ejbRef.buscarPorEndereco(valorDeBusca);
    }

    @WebMethod(operationName = "buscarPorNome")
    public List<modelo.Empresa> buscarPorNome(@WebParam(name = "valorDeBusca") String valorDeBusca) {
        return ejbRef.buscarPorNome(valorDeBusca);
    }
    
}

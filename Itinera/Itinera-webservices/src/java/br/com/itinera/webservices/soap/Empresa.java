/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.webservices.soap;

import br.com.itinera.fachada.EmpresaFachada;
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
    public List<br.com.itinera.modelo.Empresa> listar() {
        return ejbRef.listar();
    }

    @WebMethod(operationName = "buscarPorEndereco")
    public List<br.com.itinera.modelo.Empresa> buscarPorEndereco(@WebParam(name = "valorDeBusca") String valorDeBusca) {
        return ejbRef.buscarPorEndereco(valorDeBusca);
    }

    @WebMethod(operationName = "buscarPorNome")
    public List<br.com.itinera.modelo.Empresa> buscarPorNome(@WebParam(name = "valorDeBusca") String valorDeBusca) {
        return ejbRef.buscarPorNome(valorDeBusca);
    }
    
}

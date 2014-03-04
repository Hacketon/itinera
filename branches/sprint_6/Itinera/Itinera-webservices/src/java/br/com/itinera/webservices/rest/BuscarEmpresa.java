/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.webservices.rest;

import br.com.itinera.fachada.EmpresaFachada;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import br.com.itinera.modelo.Empresa;

/**
 * REST Web Service
 *
 * @author lesena
 */
@Path("generic")
@Stateless
public class BuscarEmpresa {

    @Context
    private UriInfo context;
    @EJB
    private EmpresaFachada fachada;
    /**
     * Creates a new instance of BuscarEmpresaPorEndereco
     */
    public BuscarEmpresa() {
        
    }

    /**
     * Retrieves representation of an instance of webservice.rest.BuscarEmpresa
     * @return an instance of java.lang.String
     */
    @GET @Produces("application/json")
    public List<Empresa> buscarTodas() {
        List<Empresa> emp = fachada.listar();
        return emp;
    }

}

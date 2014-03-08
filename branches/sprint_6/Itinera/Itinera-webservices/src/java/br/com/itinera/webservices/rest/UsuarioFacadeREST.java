/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itinera.webservices.rest;

import br.com.itinera.fachada.UsuarioFachada;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import br.com.itinera.modelo.Usuario;
import javax.ejb.EJB;

/**
 *
 * @author Ivens
 */
@Stateless
@Path("usuario")
public class UsuarioFacadeREST {

    @EJB
    private UsuarioFachada fachada;

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Usuario entity) {
        fachada.inserir(entity);
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void edit(Usuario entity) {
        fachada.alterar(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") BigDecimal id) {
        Usuario user = fachada.buscarPorID(id);
        fachada.remover(user);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Usuario find(@PathParam("id") BigDecimal id) {
        return fachada.buscarPorID(id);
    }
    
    @GET
    @Path("/login/{login}")
    @Produces({"application/json; charset=UTF-8"})
    public Usuario recuperarPorLogin(@PathParam("login") String login) {
        return fachada.buscarPorLogin(login);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Usuario> findAll() {
        return fachada.listar();
    }

    
}


package br.com.itinera.webservices.rest;

import br.com.itinera.fachada.UsuarioFachada;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import br.com.itinera.modelo.Usuario;
import javax.ejb.EJB;

/**
 * REST Web Service
 *
 * @author marcelo
 */
@Stateless
@Path("autenticacao")
public class AutenticarUsuario {
    
    @EJB
    private UsuarioFachada fachada;

    public AutenticarUsuario() {
    }

    /**
     * @param id
     * @return Usuario
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Usuario find(@PathParam("id") BigDecimal id) {
        return fachada.buscarPorID(id);
    }
    
}

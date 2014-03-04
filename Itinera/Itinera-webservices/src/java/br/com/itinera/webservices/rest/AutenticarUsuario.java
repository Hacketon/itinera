
package br.com.itinera.webservices.rest;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import br.com.itinera.modelo.Usuario;

/**
 * REST Web Service
 *
 * @author marcelo
 */
@Stateless
@Path("autenticacao")
public class AutenticarUsuario {
    
    @PersistenceContext(unitName = "TransportadoraPU ")
    private EntityManager em;
    
    String query = "SELECT 1 FROM Usuario u WHERE u.login = :login";

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
        return em.find(Usuario.class, id);
    }
    
//    @GET
//    @Path("{login}")
//    @Produces("application/json")
//    public boolean validarUsuario(@PathParam("login") String login) {
//        if (em.createQuery(query).setParameter("login", login).equals("1")) {
//            return true;
//        }
//        return false;
//    }
}

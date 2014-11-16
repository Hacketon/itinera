
package br.com.itinera.ws;

import br.com.itinera.modelo.Usuario;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author marcelo
 */
@Stateless
@Path("autenticacao")
public class UsuarioFacadeREST extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "ItineraPU")
    private EntityManager em;

    public UsuarioFacadeREST() {
        super(Usuario.class);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Usuario find(@PathParam("id") BigDecimal id) {
        return super.find(id);
    }

//    @GET
//    @Override
//    @Produces({"application/xml", "application/json"})
//    public List<Usuario> findAll() {
//        return super.findAll();
//    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

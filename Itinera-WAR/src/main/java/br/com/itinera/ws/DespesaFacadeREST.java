package br.com.itinera.ws;

import br.com.itinera.fachada.DespesaFachada;
import br.com.itinera.modelo.Despesa;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author marcelo
 */
@Stateless
@Path("despesa")
public class DespesaFacadeREST extends AbstractFacade<Despesa> {
    @PersistenceContext(unitName = "ItineraPU")
    private EntityManager em;
    private DespesaFachada fachada;

    public DespesaFacadeREST() {
        super(Despesa.class);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    @Produces(MediaType.APPLICATION_JSON)
    public void create(Despesa despesa) {
        try {
            super.create(despesa);
        } catch (Exception ex) {
            System.out.println("Erro em : " + despesa.getIdDespesa() + " - " + ex.getMessage());
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") BigDecimal id, Despesa entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") BigDecimal id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Despesa find(@PathParam("id") BigDecimal id) {
        return super.find(id);
    }

    @GET
    @Produces("application/json")
    public List<Despesa> findAll(@PathParam("id") BigDecimal id) {
        return super.findAll();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

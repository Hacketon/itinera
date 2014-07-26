
package br.com.itinera.enuns.converter;

import br.com.itinera.fachada.PlanoContasFachada;
import br.com.itinera.modelo.PlanoContas;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author marcelo
 */
@FacesConverter(value = "conversorPlanoContas")
@SessionScoped
@ManagedBean(name = "mngPlanoContasConverter")
public class PlanoContasConverter implements Converter, Serializable {

    @EJB
    private PlanoContasFachada fachada;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value.equals("")) {
            return null;
        } else {
            for (PlanoContas e:fachada.buscarPorDescricao(value)) {
                if (e.toString().equals(value)) {
                    return e;
                }
            }
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof PlanoContas) {
            if (((PlanoContas)value).getDescricao() != null) {
                return (value.toString());
            } else {
                return "";
            }
        }
        return "";
    }
    
}
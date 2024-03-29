package br.com.itinera.enuns.converter;

import br.com.itinera.fachada.VeiculoFachada;
import br.com.itinera.modelo.Veiculo;
import javax.ejb.EJB;
import java.io.Serializable;
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
@FacesConverter(value = "conversorVeiculo")
@SessionScoped
@ManagedBean(name = "mngVeiculoConverter")
public class VeiculoConverter implements Converter, Serializable {

    @EJB
    private VeiculoFachada fachada;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value.equals("")) {
            return null;
        } else {
            for (Veiculo e:fachada.listar()) {
                if (e.toString().equals(value)) {
                    return e;
                }
            }
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Veiculo) {
            if (((Veiculo)value).getPlacaVeiculo() != null) {
                return ((Veiculo)value).toString();
            } else {
                return "";
            }
        }
        return "";
    }
    
}

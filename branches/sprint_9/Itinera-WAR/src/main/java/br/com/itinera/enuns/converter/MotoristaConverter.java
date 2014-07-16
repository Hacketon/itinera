
package br.com.itinera.enuns.converter;

import br.com.itinera.fachada.MotoristaFachada;
import br.com.itinera.modelo.Motorista;
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
@FacesConverter(value = "conversorMotorista")
@SessionScoped
@ManagedBean(name = "mngMotoristaConverter")
public class MotoristaConverter implements Converter,Serializable{

    @EJB
    MotoristaFachada fachada;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.equals("")) {
            return null;
        } else {
            for (Motorista m: fachada.listarMotoristaAtivo()) {
                if (m.toString().equals(value)) {
                    return m;
                }
            }
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Motorista) {
            if (((Motorista)value).getNome() != null) {
                return (value.toString());
            } else {
                return "";
            }
        }
        return "";
    }
    
}

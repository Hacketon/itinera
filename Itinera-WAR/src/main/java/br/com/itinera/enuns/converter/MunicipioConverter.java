package br.com.itinera.enuns.converter;

import br.com.itinera.fachada.MunicipioFachada;
import br.com.itinera.modelo.Municipio;
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
 * @author lesena
 */
@FacesConverter(value = "conversorMunicipio")
@SessionScoped
@ManagedBean(name = "mngMunicipioConverter")
public class MunicipioConverter  implements Converter, Serializable{
    @EJB
    private MunicipioFachada fachada;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.equals("")) {
            return null;
        } else {
            for (Municipio m : fachada.listMunicipios()) {
                if (m.toString().equals(value)) {
                    return m;
                }
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Municipio) {
            if (((Municipio) value).getNomeMunicipio() != null) {
                return ((Municipio) value).toString();
            } else {
                return "";
            }
        }
        return "";
    }

}

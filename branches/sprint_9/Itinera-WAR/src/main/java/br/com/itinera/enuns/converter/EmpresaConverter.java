
package br.com.itinera.enuns.converter;

import br.com.itinera.fachada.EmpresaFachada;
import br.com.itinera.modelo.Empresa;
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
@FacesConverter(value = "conversorEmpresa")
@SessionScoped
@ManagedBean(name = "mngEmpresaConverter")
public class EmpresaConverter implements Converter, Serializable {
    
    @EJB
    private EmpresaFachada fachada;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value.equals("")) {
            return null;
        } else {
            for (Empresa e:fachada.buscarPorFornecedor(value)) {
                if (e.toString().equals(value)) {
                    return e;
                }
            }
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Empresa) {
            if (((Empresa)value).getNomeFantasia() != null) {
                return ((Empresa)value).toString();
            } else {
                return "";
            }
        }
        return "";
    }
    
}

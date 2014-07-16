
package br.com.itinera.enuns.converter;

import br.com.itinera.fachada.CategoriaVeiculoFachada;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.itinera.modelo.CategoriaVeiculo;
import java.math.BigDecimal;
import javax.ejb.EJB;

/**
 *
 * @author LeticiaSena
 */
@FacesConverter(value="conversorCategoriaVeiculo")
public class CategoriaVeiculoConverter implements Converter{
    @EJB
    private CategoriaVeiculoFachada fachada;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if (value.equals("")) {
            return null;
        } else {
            CategoriaVeiculo cv = new CategoriaVeiculo(); 
            cv.setIdCategoriaVeiculo(new BigDecimal(value));
            return cv;
        }
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof CategoriaVeiculo){
            return ((CategoriaVeiculo)value).getIdCategoriaVeiculo().toString();
        }
        return "";
    }
    
}

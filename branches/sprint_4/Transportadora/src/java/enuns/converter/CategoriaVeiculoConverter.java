/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enuns.converter;

import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.CategoriaVeiculo;

/**
 *
 * @author LeticiaSena
 */
@FacesConverter(value="conversorCategoriaVeiculo")
public class CategoriaVeiculoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        CategoriaVeiculo cv = new CategoriaVeiculo();
        if (value.equals("")) {
        } else {
            cv.setIdCategoriaVeiculo(new BigDecimal(value));
        }
        return cv;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof CategoriaVeiculo){
            return ((CategoriaVeiculo)value).getIdCategoriaVeiculo().toString();
        }
        return "";
    }
    
}

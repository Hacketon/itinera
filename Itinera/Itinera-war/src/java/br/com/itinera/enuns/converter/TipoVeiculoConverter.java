/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itinera.enuns.converter;

import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.itinera.modelo.TipoRota;

/**
 *
 * @author LeticiaSena
 */
@FacesConverter(value="conversorTipoRota")
public class TipoVeiculoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TipoRota tr = new TipoRota();
        if (value.equals("")) {
        } else {
            tr.setIdTipoRota(new BigDecimal(value));
        }
        return tr;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof TipoRota){
            return ((TipoRota)value).getIdTipoRota().toString();
        }
        return "";
    }
    
}

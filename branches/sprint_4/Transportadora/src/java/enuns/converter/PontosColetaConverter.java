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
import modelo.PontoColeta;

/**
 *
 * @author LeticiaSena
 */
@FacesConverter(value="conversorPontosColeta")
public class PontosColetaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        PontoColeta cv = new PontoColeta();
        if (value.equals("")) {
        } else {
            cv.setIdPontoColeta(new BigDecimal(value));
        }
        return cv;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof PontoColeta){
            return ((PontoColeta)value).getIdPontoColeta().toString();
        }
        return "";
    }
    
}

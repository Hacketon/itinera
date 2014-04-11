/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.enuns.converter;

import br.com.itinera.modelo.CategoriaVeiculo;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lesena
 */
@FacesConverter("conversorMoedaVirgula")
public class NumericoVirgulaConverter implements Converter {
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt","PT"));
        DecimalFormat df = (DecimalFormat) nf;
        df.setParseBigDecimal(true);
        try{
            BigDecimal decimal = (BigDecimal) df.parse(value);
            return decimal;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return NumberFormat.getNumberInstance(new Locale("pt","PT")).format(value);
    }
    
}

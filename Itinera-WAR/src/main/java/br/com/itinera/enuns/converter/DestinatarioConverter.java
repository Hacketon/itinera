/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author lesena
 */
@FacesConverter(value="conversorDestinatario")
@SessionScoped
@ManagedBean(name = "mngDestinatarioConverter")
public class DestinatarioConverter implements Converter,Serializable{
    @EJB 
    private EmpresaFachada fachada;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         if(value.equals(""))
            return null;
        else{
           //somente empresas do tipo usina e cliente
           for(Empresa empresa:fachada.buscarPorTipo('U')){
               if(empresa.getNomeFantasia().toString().equals(value))
                   return empresa;
           }
           for(Empresa empresa:fachada.buscarPorTipo('C')){
               if(empresa.getNomeFantasia().toString().equals(value))
                   return empresa;
           }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
          if(value != null && value instanceof Empresa){
             if(((Empresa)value).getNomeFantasia() != null){
                return ((Empresa)value).getNomeFantasia();
             }
             else{
                 return "";
             }
        }
        return "";
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.fachada;

import br.com.itinera.modelo.Municipio;
import br.com.itinera.persistencia.MunicipioDAO;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author bruno-note
 * @author lesena
 */
@Stateless
public class MunicipioFachada {
    @EJB
    private MunicipioDAO municipioDao;

    public List<Municipio> listMunicipios() {
        return this.municipioDao.listar();
    }
    
    public Municipio getById(BigDecimal id){
        return this.municipioDao.getById(id);
    }
    
    public List<Municipio> getByLikeName(String name){
        return this.municipioDao.getByLikeName("%"+name+"%");
    }
}

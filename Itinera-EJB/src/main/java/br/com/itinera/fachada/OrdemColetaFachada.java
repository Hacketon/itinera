/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.fachada;

import br.com.itinera.modelo.OrdemColeta;
import br.com.itinera.persistencia.OrdemColetaDAO;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author lesena
 */
@Stateless
public class OrdemColetaFachada {
    @EJB
    private OrdemColetaDAO dao;
    
    public List<OrdemColeta> listar(){
        return dao.listar();
    }
    
    public List<OrdemColeta> buscarPorPeriodoNotaFiscal(Date inicio,Date fim,Integer nota){
        boolean bInicio = (inicio == null);
        boolean bFim = (fim == null);
        boolean bNota = (nota == null);
        
             if( bNota &&  bFim && !bInicio) return dao.buscarPorPeriodoInicio(inicio);
        else if( bNota && !bFim &&  bInicio) return dao.buscarPorPeriodoFim(fim);
        else if( bNota && !bFim && !bInicio) return dao.buscarPorPeriodo(inicio, fim);
        else if(!bNota &&  bFim &&  bInicio) return dao.buscarPorNotaFiscal(nota);
        else if(!bNota &&  bFim && !bInicio) return dao.buscarPorPeriodoInicioNotaFiscal(inicio, nota);
        else if(!bNota && !bFim &&  bInicio) return dao.buscarPorPeriodoFimNotaFiscal(fim, nota);
        else if(!bNota && !bFim && !bInicio) return dao.buscarPorPeriodoENotaFiscal(inicio, fim, nota);
        
        return listar();
    }
    
    public void inserir(OrdemColeta ordem){
        dao.inserir(ordem);
    }
    
    public void alterar(OrdemColeta ordem){
        dao.alterar(ordem);
    }
    
    public void excluir(OrdemColeta ordem){
        dao.excluir(ordem);
    }
}

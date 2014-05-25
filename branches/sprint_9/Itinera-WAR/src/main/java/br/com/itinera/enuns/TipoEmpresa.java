/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.enuns;

/**
 *
 * @author lesena
 */
public enum TipoEmpresa {
    C, U, F, G;
    
    @Override
    public String toString(){
        switch(this){
            case C:
                return "Cliente";
            case U:
                return "Usina";
            case F:
                return "Fornecedor";
            case G:
                return "Empresa do Grupo";
        }
        return null;
    }
}

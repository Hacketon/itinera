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
    CLIENTE, USINA, FORNECEDOR, GRUPO;
    
    @Override
    public String toString(){
        switch(this){
            case CLIENTE:
                return "Cliente";
            case USINA:
                return "Usina";
            case FORNECEDOR:
                return "Fornecedor";
            case GRUPO:
                return "Empresa do Grupo";
        }
        return null;
    }

}

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
public enum EstadoCivil {
    
    SOLTEIRO('S'),CASADO('C'),DIVORCIADO('D'),AMAZIADO('A');

    private Character valor;
    
    private EstadoCivil(Character value){
        this.valor = value;
    }
    
    public String toString(){
        switch(valor){
            case 'S':
                return "Solteiro";
            case 'C':
                return "Casado";
            case 'D':
                return "Divorciado";
            case 'A':
                return "Amaziado";
            default:
                return null;
        }
    }
    
}

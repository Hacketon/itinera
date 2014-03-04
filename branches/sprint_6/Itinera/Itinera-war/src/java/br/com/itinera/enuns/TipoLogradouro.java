/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itinera.enuns;

/**
 *
 * @author Michel
 */
public enum TipoLogradouro {
    AVENIDA,
    KM,
    PRAÇA,
    RUA,
    TRAVESSA;
    
    @Override
    public String toString(){
        switch (this) {
            case AVENIDA: {
                return "Avenida";
            }
            case KM: {
                return "Km";
            }
            case PRAÇA: {
                return "Praça";
            }
            case RUA: {
                return "Rua";
            }
            case TRAVESSA: {
                return "Travessa";
            }
        }
        return null;
    }
    
}
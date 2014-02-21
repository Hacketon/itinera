/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enuns;

/**
 *
 * @author bruno-note
 */
public enum TipoCombustivel {
    DIESEL,
    GASOLINA,
    ETANOL,
    GNV,
    FLEX;
    
    @Override
    public String toString(){
        switch (this) {
            case DIESEL: {
                return "Diesel";
            }
            case GASOLINA: {
                return "Gasolina";
            }
            case ETANOL: {
                return "Etanol";
            }
            case GNV: {
                return "Gás Natural Veícular";
            }
            case FLEX: {
                return "Flex (Gasolina e Etanol)";
            }
        }
        return null;
    }
    
}

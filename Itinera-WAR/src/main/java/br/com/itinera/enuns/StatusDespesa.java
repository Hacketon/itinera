
package br.com.itinera.enuns;

/**
 *
 * @author marcelo
 */
public enum StatusDespesa {
    
    ENVIADO('E'), CONFIRMADO('C'), ERRO('R'), NAO_ENVIADO('N');

    private Character valor;
    
    private StatusDespesa(Character value){
        this.valor = value;
    }
    
    public String toString(){
        switch(valor){
            case 'E':
                return "Enviado";
            case 'C':
                return "Confirmado";
            case 'R':
                return "Erro";
            case 'N':
                return "Não Enviado";
            default:
                return null;
        }
    }
    
}
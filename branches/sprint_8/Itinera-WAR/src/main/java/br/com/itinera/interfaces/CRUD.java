package br.com.itinera.interfaces;

/**
 *
 * @author lesena
 * @description interface para base de CRUD (inserção, alteração, listagem, remoção
 */
public interface CRUD {
    
    //Métodos de manipulação de objeto
    public void recuperar();
    
    public String inserir();
    
    public String alterar();
    
    public void excluir();
}

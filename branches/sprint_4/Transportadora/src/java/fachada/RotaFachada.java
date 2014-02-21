package fachada;

import enuns.ModoCRUD;
import ferramentas.Mensagem;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Rota;
import persistencia.RotaDAO;

/**
 *
 * @author LeticiaSena
 */
@Stateless
public class RotaFachada {
    private ModoCRUD modo;
    @EJB 
    private RotaDAO rotaDao;
    
    public void inserir(Rota objeto) throws UnsupportedOperationException {
        try {
            modo = ModoCRUD.INSERIR;
            rotaDao.inserir(objeto);
        } catch (UnsupportedOperationException e) {
            Mensagem.mostrarMensagemErro("Erro!","Não é possível alterar uma rota para ativa, se não houver ao menos dois pontos de coleta relacionados!");
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro("Erro!", e.getMessage());
        }
    }
    
    public void alterar(Rota objeto) throws UnsupportedOperationException {
        try {
            modo = ModoCRUD.ATUALIZAR;
            rotaDao.alterar(objeto);
        } catch (UnsupportedOperationException e) {
            Mensagem.mostrarMensagemErro("Erro!","Não é possível alterar uma rota para ativa, se não houver ao menos dois pontos de coleta relacionados!");
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro("Erro!", e.getMessage());
        }
    }
    
    public void remover(Rota objeto){
        try {
            rotaDao.remover(objeto);
        } catch (Exception e) {
            Mensagem.mostrarMensagemErro("Erro!", e.getMessage());
        }
    }
    
    public List<Rota> listar(){
        return rotaDao.listar();
    }

}

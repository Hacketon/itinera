package br.com.itinera.itineramobile;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import br.com.itinera.itineramobile.adapter.ListaDespesaAdapter;
import br.com.itinera.itineramobile.banco.DatabaseHelper;
import br.com.itinera.itineramobile.bean.Despesa;
import br.com.itinera.itineramobile.tarefa.PesquisaDespesaTarefa;

public class PesquisarDespesa extends ListActivity {

	private static final int ALTERAR = 1;
	
	private ProgressDialog loadingDialog;
	private AlertDialog errorAlertDialog;
	private ListaDespesaAdapter adapter;
	private List<Despesa> lstListaDespesa;
	boolean blnShort = false;
    int Posicao = 0;    //determinar a posição da despesa dentro da lista lstListaDespesa
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pesquisar_despesa);
		
		//InstÃ¢ncia de objeto da tela de loading
		loadingDialog = new ProgressDialog(PesquisarDespesa.this);
		loadingDialog.setMessage("Carregando despesas...");
		loadingDialog.setCancelable(true);
		loadingDialog.show();
		
		//Monta lista
		new PesquisaDespesaTarefa(this, "", "").execute();
		
		registerForContextMenu(getListView());
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pesquisar_despesa, menu);
		return true;
	}
	
	public void montarListaAposProcessamento(List<Despesa> listaDespesa){
		
		if (listaDespesa != null && !listaDespesa.isEmpty()){
			lstListaDespesa = listaDespesa;
			adapter = new ListaDespesaAdapter(PesquisarDespesa.this, lstListaDespesa);
			setListAdapter(adapter);
		}
		
		loadingDialog.dismiss();
	}
	
	
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        Despesa despesa = null;
	         
	        try
	        {
	            super.onActivityResult(requestCode, resultCode, data);
	            if (resultCode == Activity.RESULT_OK)
	            {
	                //obtem dados inseridos/alterados na Activity Despesa
	                despesa = (Despesa)data.getExtras().getSerializable("despesa");
	                 
	                //o valor do requestCode foi definido na função startActivityForResult
	                if (requestCode == ALTERAR){
	                    	                     
	                    //atualiza o contato na lista de contatos em memória
	                    lstListaDespesa.set(Posicao, despesa);
	                	
	                	//lstListaDespesa = new DatabaseHelper(this).pesquisarPorCampo("", "");
	                }
	                 
	                //método responsável pela atualiza da lista de dados na tela
	                adapter.notifyDataSetChanged();
	            }
	        }
	        catch (Exception e) {
	            showErrorMessage("Erro : " + e.getMessage());
	        }        
	    }  
	 
	 @Override   
	    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {        
	        try
	        {
	            //Criação do popup menu com as opções que termos
	             
	            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
	            if (!blnShort)
	            {
	                Posicao = info.position;
	            }
	            blnShort = false; 
	             
	            menu.setHeaderTitle("Selecione:");            
	            //a origem dos dados do menu está definido no arquivo arrays.xml 
	            String[] menuItems = getResources().getStringArray(R.array.menu);             
	            for (int i = 0; i<menuItems.length; i++) {                
	                menu.add(Menu.NONE, i, i, menuItems[i]);            
	            }        
	        }catch (Exception e) {
	        	showErrorMessage("Erro : " + e.getMessage());
	        }            
	    }  
	 
	     
	    //Este método é disparado quando o usuário clicar em um item do ContextMenu
	    @Override   
	    public boolean onContextItemSelected(MenuItem item) {       
	        Despesa despesa = null;
	        try
	        {
	            int menuItemIndex = item.getItemId();        
	 
	            //Carregar a instância POJO com a posição selecionada na tela
	            despesa = (Despesa) getListAdapter().getItem(Posicao);
	             
	            if (menuItemIndex == 0){
	                //Carregar a Activity com o registro selecionado na tela
	                 
	                Intent it = new Intent(this, CadastroDespesa.class);
	                it.putExtra("tipo", ALTERAR);
	                it.putExtra("despesa", despesa);
	                startActivityForResult(it, ALTERAR); //chama a tela de alteração
	            }else if (menuItemIndex == 1){
	                //Excluir do Banco de Dados e da tela o registro selecionado
	            	new DatabaseHelper(this).delete(despesa); 
	                lstListaDespesa.remove(despesa);
	                adapter.notifyDataSetChanged(); //atualiza a tela
	            }
	        }catch (Exception e) {
	        	showErrorMessage("Erro : " + e.getMessage());
	        }   
	        return true;   
	         
	    }    
	 
	    @Override
	    protected void onListItemClick(ListView l, View v, int position, long id) {
	        super.onListItemClick(l, v, position, id);
	        //por padrão o ContextMenu, só é executado através de LongClick, mas
	        //nesse caso toda vez que executar um ShortClick, abriremos o menu
	        //e também guardaremos qual a posição do itm selecionado
	         
	        Posicao = position;
	        blnShort = true;
	        this.openContextMenu(l);
	    }
	
	private void createErrorAlertDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Error").setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		errorAlertDialog = builder.create();
	}

	private void showErrorMessage(String msg) {
		createErrorAlertDialog();
		errorAlertDialog.setMessage(msg);
		errorAlertDialog.show();
	}
	
	
	

}

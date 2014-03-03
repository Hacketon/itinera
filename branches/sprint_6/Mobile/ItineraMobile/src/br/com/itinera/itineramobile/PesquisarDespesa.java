package br.com.itinera.itineramobile;

import java.util.List;

import br.com.itinera.itineramobile.adapter.ListaDespesaAdapter;
import br.com.itinera.itineramobile.bean.Despesa;
import br.com.itinera.itineramobile.tarefa.PesquisaDespesaTarefa;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class PesquisarDespesa extends Activity {

	private ProgressDialog loadingDialog;
	private AlertDialog errorAlertDialog;
	private Button btnPesquisarDespesaVoltar;
	private int codigoUsuario;
	private String nomeUsuario;	
	private ListaDespesaAdapter adapter;
	private ListView lstListaDespesa;
	private Button btnAlterar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pesquisar_despesa);
		
		lstListaDespesa = (ListView)findViewById(R.id.lstListaDespesas);
		btnPesquisarDespesaVoltar = (Button)findViewById(R.id.btnPesquisarDespesaVoltar);
		btnAlterar = (Button)findViewById(R.id.btnAlterar);
		
		//Recupera parâmetros da tela anterior
		Intent i = getIntent();
		Bundle parametros = i.getExtras();
		
		if(parametros != null){
			codigoUsuario = parametros.getInt("codigo");
			nomeUsuario = parametros.getString("nome");
		}
		
		btnPesquisarDespesaVoltar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// click botao voltar
				Intent i = new Intent(PesquisarDespesa.this, MenuDespesas.class);
				
				Bundle parametros = new Bundle();
				parametros.putInt("codigo", codigoUsuario);
				parametros.putString("nome", nomeUsuario);
				
				i.putExtras(parametros);
				
				startActivity(i);				
			}
		});
		
		//Instância de objeto da tela de loading
		loadingDialog = new ProgressDialog(PesquisarDespesa.this);
		loadingDialog.setMessage("Carregando despesas...");
		loadingDialog.setCancelable(true);
		loadingDialog.show();
		new PesquisaDespesaTarefa(this, "", "").execute();
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pesquisar_despesa, menu);
		return true;
	}
	
	public void montarListaAposProcessamento(List<Despesa> listaDespesa){
		adapter = new ListaDespesaAdapter(this, listaDespesa);
		lstListaDespesa.setAdapter(adapter);
		lstListaDespesa.setVisibility(View.VISIBLE);
		loadingDialog.dismiss();
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

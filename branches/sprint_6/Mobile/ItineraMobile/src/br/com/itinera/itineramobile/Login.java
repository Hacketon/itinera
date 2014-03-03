package br.com.itinera.itineramobile;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import br.com.itinera.itineramobile.bean.Usuario;
import br.com.itinera.itineramobile.tarefa.UsuarioTarefa;


public class Login extends Activity {

	private Button btnVoltar;
	private Button btnEntrar;
	private TextView txtUsuario;
	private TextView txtSenha;
	private TextView txtDataAtual;
	private ProgressDialog loadingDialog;
	private AlertDialog errorAlertDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//Referencia aos elementos da tela
		btnVoltar = (Button) findViewById(R.id.btnLVoltar);
		btnEntrar = (Button) findViewById(R.id.btnLoginEntrar);
		txtUsuario = (TextView) findViewById(R.id.txtUsuario);
		txtSenha = (TextView) findViewById(R.id.txtSenha);
		txtDataAtual = (TextView) findViewById(R.id.txtDataAtual);
		
		txtUsuario.setText("ADMIN");
		txtSenha.setText("adm123");
		
		//Coloca a data atual no devido campo
		txtDataAtual.setText(dataAtual());
		
		//Instância de objeto da tela de loading
		loadingDialog = new ProgressDialog(Login.this);
		loadingDialog.setMessage("Efetuando login...");
		loadingDialog.setCancelable(true);
		
		
		
		btnVoltar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Click btn voltar
				Intent i = new Intent(Login.this, MainActivity.class);
				startActivity(i);				
			}
		});
		
		btnEntrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//click btn entrar - chama servi�o
				loadingDialog.show();
				
				if(txtUsuario.getText().toString().trim().equals("")){
					loadingDialog.dismiss();
					showErrorMessage("O campo Usuário é obrigatório!");
					txtUsuario.requestFocus();
					return;
				}
				
				if(txtSenha.getText().toString().trim().equals("")){
					loadingDialog.dismiss();
					showErrorMessage("O campo Senha é obrigatório!");
					txtSenha.requestFocus();
					return;
				}
				
				new UsuarioTarefa(Login.this).execute(txtUsuario.getText().toString().toUpperCase(),txtSenha.getText().toString());				
			}
		});
		
	}
	
	
	
	public void acaoAposLogin(String retornoValidacao, Usuario usuario){
		loadingDialog.dismiss();
		if(retornoValidacao.equals("ok")){
			//usuário e senha válidos, abrir menu principal			
			Intent i = new Intent(Login.this, MenuPrincipal.class);
			
			Bundle parametros = new Bundle();
			parametros.putInt("codigo", usuario.getCodigo());
			parametros.putString("nome", usuario.getNome());
			
			i.putExtras(parametros);
			startActivity(i);
			
		}else{
			showErrorMessage(retornoValidacao);
			txtUsuario.setText("");
			txtSenha.setText("");
			txtUsuario.requestFocus();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
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
	
	private String dataAtual(){
	    //Método que retorna uma String com a data formatada nos padrões convencionais
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(new Date());
	}
}

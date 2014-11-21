package br.com.itinera.itineramobile;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MenuPrincipal extends Activity {

	private TextView txtSaudacao;
	private TextView txtMenuDataAtual;
	private Button btnMenuConsultas;
	private Button btnMenuDespesas;
	private int codigoUsuario;
	private String nomeUsuario;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
		
		//Referência ao campo do layout
		txtSaudacao = (TextView)findViewById(R.id.txtSaudacao);
		txtMenuDataAtual = (TextView)findViewById(R.id.txtMenuDataAtual);
		btnMenuConsultas = (Button)findViewById(R.id.btnMenuConsultas);
		btnMenuDespesas = (Button)findViewById(R.id.btnMenuDespesas);
		
		txtMenuDataAtual.setText(dataAtual());
		
		//Recupera parâmetros da tela anterior
		Intent i = getIntent();
		Bundle parametros = i.getExtras();
		
		if(parametros != null){
			txtSaudacao.setText("Bem-vindo, "+parametros.getString("nome"));
			
			codigoUsuario = parametros.getInt("codigo");
			nomeUsuario = parametros.getString("nome");
		}
	
		
		btnMenuConsultas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// click botao consultas
				
				Bundle parametros = new Bundle();
				parametros.putInt("codigo", codigoUsuario);
				parametros.putString("nome", nomeUsuario);
				
				Intent i = new Intent(MenuPrincipal.this, MenuConsultas.class);
				i.putExtras(parametros);
				startActivity(i);				
			}
		});
		
		btnMenuDespesas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// click botao consultas
				
				Bundle parametros = new Bundle();
				parametros.putInt("codigo", codigoUsuario);
				parametros.putString("nome", nomeUsuario);
				
				Intent i = new Intent(MenuPrincipal.this, MenuDespesas.class);
				i.putExtras(parametros);
				startActivity(i);				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}
	
	private String dataAtual(){
	    //Método que retorna uma String com a data formatada nos padrões convencionais
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(new Date());
	}

}

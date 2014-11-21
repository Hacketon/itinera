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

public class MenuConsultas extends Activity {

	private TextView txtMenuConsultasDataAtual;
	private TextView txtMenuConsultasSaudacao;
	private Button btnConsultaEmpresaNome;
	private Button btnConsultaEmpresaEndereco;
	private Button btnConsultaEmpresaCnpj;
	private int codigoUsuario;
	private String nomeUsuario;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_consultas);
		
		//Referência aos botões da tela
		txtMenuConsultasDataAtual = (TextView)findViewById(R.id.txtMenuConsultasDataAtual);
		txtMenuConsultasSaudacao = (TextView)findViewById(R.id.txtMenuConsultasSaudacao);
		btnConsultaEmpresaNome = (Button)findViewById(R.id.btnConsultaEmpresaNome);
		btnConsultaEmpresaEndereco = (Button)findViewById(R.id.btnConsultaEmpresaEndereco);
		btnConsultaEmpresaCnpj = (Button)findViewById(R.id.btnConsultaEmpresaCnpj);
		
		txtMenuConsultasDataAtual.setText(dataAtual());
		
		//Recupera parâmetros da tela anterior
		Intent i = getIntent();
		Bundle parametros = i.getExtras();
		
		if(parametros != null){
			txtMenuConsultasSaudacao.setText("Bem-vindo, "+parametros.getString("nome"));
			
			codigoUsuario = parametros.getInt("codigo");
			nomeUsuario = parametros.getString("nome");
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_consultas, menu);
		return true;
	}
	
	private String dataAtual(){
	    //Método que retorna uma String com a data formatada nos padrões convencionais
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(new Date());
	}

}

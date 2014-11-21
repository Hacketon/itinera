package br.com.itinera.itineramobile;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.itinera.itineramobile.banco.DatabaseHelper;
import br.com.itinera.itineramobile.bean.Despesa;
import br.com.itinera.itineramobile.util.Mask;
import br.com.itinera.itineramobile.util.Moeda;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CadastroDespesa extends Activity {

	private int codigoUsuario;
	private Button btnCadastrarDespesa;
	private TextView txtCadastrarDespesaDataAtual;
	private TextView txtCadastrarDespesaSaudacao;
	private EditText txtCadastroDespesaFornecedor;
	private EditText txtCadastroDespesaDocumento;
	private EditText txtCadastroDespesaData;
	private EditText txtCadastroDespesaValor;
	private Spinner spnCadastroDespesaTipoDespesa;
	private AlertDialog errorAlertDialog;
	private long _id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_despesa);
		
		btnCadastrarDespesa = (Button) findViewById(R.id.btnCadastrarDespesa);
		
		txtCadastroDespesaFornecedor = (EditText) findViewById(R.id.txtCadastroDespesaFornecedor);
		txtCadastroDespesaDocumento = (EditText) findViewById(R.id.txtCadastroDespesaDocumento);
		txtCadastroDespesaData = (EditText) findViewById(R.id.txtCadastroDespesaData);
		txtCadastroDespesaValor = (EditText) findViewById(R.id.txtCadastroDespesaValor);
		//spnCadastroDespesaTipoDespesa = (Spinner) findViewById(R.id.spnCadastroDespesaTipoDespesa);
		
		txtCadastroDespesaData.addTextChangedListener(Mask.insert("##/##/####", txtCadastroDespesaData));
		
		txtCadastroDespesaValor.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// Perda de foco: qndo hasFocus = false
				if(!hasFocus){
					txtCadastroDespesaValor.setText(Moeda.mascaraDinheiro(txtCadastroDespesaValor.getText().toString(), Moeda.DINHEIRO_REAL));
				}
				
			}
		});
		
		btnCadastrarDespesa.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Click cadastrar
				DatabaseHelper banco = new DatabaseHelper(CadastroDespesa.this);
				
				Despesa d = new Despesa();
				d.setCodigoDespesa(0);
				d.setCodigoUsuario(codigoUsuario);
				d.setData(txtCadastroDespesaData.getText().toString());
				d.setNomeFornecedor(txtCadastroDespesaFornecedor.getText().toString());
				d.setNumeroDocumento(txtCadastroDespesaDocumento.getText().toString());
				d.setTipoDespesa("Combustível");
				d.setValor(Double.valueOf(Moeda.desmascaraDinheiro(txtCadastroDespesaValor.getText().toString())));
				if ( _id != 0 ){
					d.set_id(_id);	
				}
				
				
				if(!validarCampos(d, txtCadastroDespesaFornecedor, txtCadastroDespesaData, txtCadastroDespesaValor)){
					return;
				}
				
				try {
					banco.salvar(d);
					showErrorMessage("Cadastro de despesa efetuado com sucesso.");
					limparCampos(txtCadastrarDespesaDataAtual, txtCadastrarDespesaSaudacao, txtCadastroDespesaData, txtCadastroDespesaDocumento, txtCadastroDespesaFornecedor, txtCadastroDespesaValor);
				} catch (Exception e) {
					showErrorMessage("Falha ao gravar despesa.");
				}
			}

			private void limparCampos(TextView txtCadastrarDespesaDataAtual, TextView txtCadastrarDespesaSaudacao, EditText txtCadastroDespesaData, EditText txtCadastroDespesaDocumento, EditText txtCadastroDespesaFornecedor, EditText txtCadastroDespesaValor) {
				// MÃ©todo de limpar campos
				//txtCadastrarDespesaDataAtual.setText("");
				//txtCadastrarDespesaSaudacao.setText("");
				txtCadastroDespesaData.setText("");
				txtCadastroDespesaDocumento.setText("");
				txtCadastroDespesaFornecedor.setText("");
				txtCadastroDespesaValor.setText("");	
				txtCadastroDespesaFornecedor.requestFocus();
			}
		});
		
		//Recupera parÃ¢metros da tela anterior
		Intent i = getIntent();
		Bundle parametros = i.getExtras();
				
		if(parametros != null){
			//txtCadastrarDespesaSaudacao.setText("Bem-vindo, "+parametros.getString("nome"));
				
			codigoUsuario = parametros.getInt("codigo");
			String fornecedor = new String();
			
			long id = parametros.getLong("_id");
			if (id != 0){
			
				txtCadastroDespesaFornecedor.setText(parametros.getString("nomeFornecedor"));
				txtCadastroDespesaDocumento.setText(parametros.getString("numeroDocumento"));
				txtCadastroDespesaData.setText(parametros.getString("data"));
				Long valor = parametros.getLong("valor");
				txtCadastroDespesaValor.setText(valor.toString());		
				_id = parametros.getLong("_id");
				
				
			}				
			
		}
				
		//txtCadastrarDespesaDataAtual.setText(dataAtual());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_despesa, menu);
		return true;
	}
	
	private String dataAtual(){
	    //MÃ©todo que retorna uma String com a data formatada nos padrÃµes convencionais
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(new Date());
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
	
	private boolean validarCampos(Despesa d, EditText txtCadastroDespesaFornecedor, EditText txtCadastroDespesaData, EditText txtCadastroDespesaValor){
		if(d.getNomeFornecedor().trim().equals("")){
			showErrorMessage("O campo fornecedor é obrigatório!");
			txtCadastroDespesaFornecedor.requestFocus();
			return false;
		}
		
		if(d.getData().trim().equals("")){
			showErrorMessage("O campo data é obrigatório!");
			txtCadastroDespesaData.requestFocus();
			return false;
		}
		
		if(d.getValor()== 0.0){
			showErrorMessage("O campo valor é obrigatório!");
			txtCadastroDespesaValor.requestFocus();
			return false;
		}
		return true;
	}
	
}

package br.com.itinera.itineramobile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CadastroDespesa extends Activity {

	private static final int INCLUIR = 0;
	private static final int ALTERAR = 1;
	Despesa despesa;
	private int codigoUsuario;
	private Button btnCadastrarDespesa;
	private TextView txtCadastrarDespesaDataAtual;
	private TextView txtCadastrarDespesaSaudacao;
	private EditText txtCadastroDespesaFornecedor;
	private EditText txtCadastroDespesaDocumento;
	private DatePicker datePickerCadastroDespesaData;
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
		datePickerCadastroDespesaData = (DatePicker) findViewById(R.id.datePickerCadastroDespesaData);
		txtCadastroDespesaValor = (EditText) findViewById(R.id.txtCadastroDespesaValor);
		//spnCadastroDespesaTipoDespesa = (Spinner) findViewById(R.id.spnCadastroDespesaTipoDespesa);
		
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
				
				despesa.setCodigoDespesa(0);
				despesa.setCodigoUsuario(codigoUsuario);
				int day = datePickerCadastroDespesaData.getDayOfMonth();
				int month = datePickerCadastroDespesaData.getMonth();
				int year = datePickerCadastroDespesaData.getYear();
				//PRECISA AJUSTAR PARA PEGAR A DATA DO PICKER
				despesa.setData("01/01/2014");
				despesa.setNomeFornecedor(txtCadastroDespesaFornecedor.getText().toString());
				despesa.setNumeroDocumento(txtCadastroDespesaDocumento.getText().toString());
				despesa.setTipoDespesa("Combustível");
				despesa.setValor(Double.valueOf(Moeda.desmascaraDinheiro(txtCadastroDespesaValor.getText().toString())));
				
				if(!validarCampos(despesa)){
					return;
				}
				
				
				DatabaseHelper banco = new DatabaseHelper(CadastroDespesa.this);
				
				
				try {
					banco.salvar(despesa);
					showErrorMessage("Cadastro de despesa efetuado com sucesso.");
					limparCampos(txtCadastrarDespesaDataAtual, txtCadastrarDespesaSaudacao, datePickerCadastroDespesaData, txtCadastroDespesaDocumento, txtCadastroDespesaFornecedor, txtCadastroDespesaValor);
					//Quando confirmar a inclusão ou alteração deve-se devolver
		            //o registro com os dados preenchidos na tela e informar
		            //o RESULT_OK e em seguida finalizar a Activity		             
		            Intent data = new Intent();
		            data.putExtra("despesa", despesa);
		            setResult(Activity.RESULT_OK, data);    
		            finish();
				} catch (Exception e) {
					showErrorMessage("Falha ao gravar despesa.");
				}
			}

			private void limparCampos(TextView txtCadastrarDespesaDataAtual, TextView txtCadastrarDespesaSaudacao, DatePicker txtCadastroDespesaData, EditText txtCadastroDespesaDocumento, EditText txtCadastroDespesaFornecedor, EditText txtCadastroDespesaValor) {
				// TO-DO TRATAR COMO LIMPAR O CAMPO
				//txtCadastroDespesaData.setText("");
				txtCadastroDespesaDocumento.setText("");
				txtCadastroDespesaFornecedor.setText("");
				txtCadastroDespesaValor.setText("");	
				txtCadastroDespesaFornecedor.requestFocus();
			}
		});
		
		//Recupera parÃ¢metros da tela anterior
		Intent i = getIntent();
		final Bundle parametros = i.getExtras();
				
		if(parametros != null){

			codigoUsuario = parametros.getInt("codigo");
			int tipo = parametros.getInt("tipo");
			
			if (tipo == INCLUIR){
			
				despesa = new Despesa();
			}
			else{
				
				despesa = (Despesa)parametros.getSerializable("despesa");
				txtCadastroDespesaFornecedor.setText(despesa.getNomeFornecedor());
				txtCadastroDespesaDocumento.setText(despesa.getNumeroDocumento());
				
				//CONVERTER O BUNDLE EM Date
				
				// SETAR O PICKER COM A DATA RECUPERADA
				//txtCadastroDespesaData.setText(parametros.getString("data"));
				Long valor = despesa.getValor().longValue();
				txtCadastroDespesaValor.setText(valor.toString());		
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
	
	private boolean validarCampos(Despesa d){
		if(d.getNomeFornecedor().trim().equals("")){
			showErrorMessage("O campo fornecedor é obrigatório!");
			txtCadastroDespesaFornecedor.requestFocus();
			return false;
		}
		
		if(d.getData().trim().equals("")){
			showErrorMessage("O campo data é obrigatório!");
			datePickerCadastroDespesaData.requestFocus();
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

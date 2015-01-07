package br.com.itinera.itineramobile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.itinera.itineramobile.banco.DatabaseHelper;
import br.com.itinera.itineramobile.bean.Despesa;
import br.com.itinera.itineramobile.util.Mask;
import br.com.itinera.itineramobile.util.Moeda;

public class CadastroDespesa extends Activity {

	private static final int INCLUIR = 0;
	private static final int ALTERAR = 1;
	Despesa despesa;
	private int codigoUsuario;
	private Button btnCadastrarDespesa;
	private EditText txtCadastroDespesaFornecedor;
	private EditText txtCadastroDespesaDocumento;
	private Button buttonDatePickerCadastroDespesaData;
	private EditText txtCadastroDespesaValor;
	private Spinner spnCadastroDespesaTipoDespesa;
	private AlertDialog errorAlertDialog;
	private int year,month,day;
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_despesa);
		context = this;
		btnCadastrarDespesa = (Button) findViewById(R.id.btnCadastrarDespesa);
		
		txtCadastroDespesaFornecedor = (EditText) findViewById(R.id.txtCadastroDespesaFornecedor);
		txtCadastroDespesaDocumento = (EditText) findViewById(R.id.txtCadastroDespesaDocumento);
		buttonDatePickerCadastroDespesaData = (Button) findViewById(R.id.buttonDatePickerCadastroDespesaData);
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
		
		buttonDatePickerCadastroDespesaData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog(0);
			}
		});
		
		btnCadastrarDespesa.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				despesa.setCodigoDespesa(0);
				despesa.setCodigoUsuario(codigoUsuario);
				//PRECISA AJUSTAR PARA PEGAR A DATA DO PICKER
				
				if (context.getString(R.string.label_selecionar_data).equals(buttonDatePickerCadastroDespesaData.getText().toString())){
					despesa.setData("");
				} else {
					despesa.setData(buttonDatePickerCadastroDespesaData.getText().toString());
				}
				
				//despesa.setData(buttonDatePickerCadastroDespesaData.getText().toString());
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
					limparCampos();
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

			private void limparCampos() {
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
				buttonDatePickerCadastroDespesaData.setText(despesa.getData());
				
				// SETAR O PICKER COM A DATA RECUPERADA
				//txtCadastroDespesaData.setText(parametros.getString("data"));
				Long valor = despesa.getValor().longValue();
				txtCadastroDespesaValor.setText(valor.toString());		
			}
				
			}				
				
		//txtCadastrarDespesaDataAtual.setText(dataAtual());
	}
	
	protected Dialog onCreateDialog (int id){
		if (id == 0){
			
			Calendar calendar = Calendar.getInstance();
			
			year = calendar.get(Calendar.YEAR);
			
			month = calendar.get(Calendar.MONTH);
			
			day = calendar.get(Calendar.DAY_OF_MONTH);
			
			DatePickerDialog datePicker = new DatePickerDialog(this, mDateSet, year, month, day);
			
			return datePicker;
		}
		
		return null;
	}

	DatePickerDialog.OnDateSetListener mDateSet = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker arg0, int year, int month, int day) {
			// TODO Auto-generated method stub
			
			String str = new String();
			
			str = Mask.setLeftPadding(String.valueOf(day), '0', 2)+"/"+Mask.setLeftPadding(String.valueOf(month+1),'0', 2)+"/"+year;
			
			buttonDatePickerCadastroDespesaData.setText(str);
		}
	};
	
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

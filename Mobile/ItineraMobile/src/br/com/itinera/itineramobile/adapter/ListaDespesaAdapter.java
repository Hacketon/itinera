package br.com.itinera.itineramobile.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import br.com.itinera.itineramobile.CadastroDespesa;
import br.com.itinera.itineramobile.R;
import br.com.itinera.itineramobile.banco.DatabaseHelper;
import br.com.itinera.itineramobile.bean.Despesa;
import br.com.itinera.itineramobile.util.Moeda;

public class ListaDespesaAdapter extends ArrayAdapter<Despesa> {

	private LayoutInflater inflater;
	private Context context;
	private Despesa despesa;
	private int posicaoLista;
	
	public ListaDespesaAdapter(Context context, List<Despesa> listaDespesa){
		super(context, R.layout.item_lista_despesa, listaDespesa);
		this.context = context;
		
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	} 

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if(convertView == null){
			convertView  = inflater.inflate(R.layout.item_lista_despesa, null);
			holder = new ViewHolder();
			holder.txtFornecedor = (TextView)convertView.findViewById(R.id.txtItemFornecedor);
			holder.txtData = (TextView)convertView.findViewById(R.id.txtItemData);
			holder.txtValor = (TextView)convertView.findViewById(R.id.txtItemValor);
			holder.btnExcluir = (Button)convertView.findViewById(R.id.btnExcluir);
			holder.btnAlterar = (Button)convertView.findViewById(R.id.btnAlterar);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		Despesa d = getItem(position);
		despesa = d;
		holder.txtFornecedor.setText(d.getNomeFornecedor());
		holder.txtData.setText(d.getData());
		holder.txtValor.setText(Moeda.mascaraDinheiro(d.getValor(), Moeda.DINHEIRO_REAL));
		
		holder.btnExcluir.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("TESTE_ONCLI", "CLICK EXCLUIR");
				//UTILIZAR A CAMADA DE MODELO PARA EXCLUIR O ITEM SELECIONADO
				DatabaseHelper banco = new DatabaseHelper(context);
				try {
					
					banco.delete(despesa);
					banco.close();
					notifyDataSetChanged();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					banco.close();
					e.printStackTrace();
				}
				//FAZER UM REFRESH NO ARRAYLIST
			}
		});
		
		holder.btnAlterar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("TESTE_ONCLI", "CLICK ALTERAR");
				//CRIAR UM BUNDLE
				Bundle parametros = new Bundle();
				parametros.putInt("codigoDespesa", despesa.getCodigoDespesa());
				parametros.putLong("codigoUsuario", despesa.getCodigoUsuario());
				parametros.putString("nomeFornecedor", despesa.getNomeFornecedor());
				parametros.putString("numeroDocumento", despesa.getNumeroDocumento());
				parametros.putLong("valor", despesa.getValor().longValue());
				parametros.putString("data", despesa.getData());
				parametros.putString("tipoDespesa", despesa.getTipoDespesa());
				parametros.putLong("_id",despesa.get_id());
				
				//parametros.putInt("codigo", codigoUsuario);
				//parametros.putString("nome", nomeUsuario);
				
				//CHAMAR A TELA DE DESPESA
				Intent i = new Intent(context, CadastroDespesa.class);
				i.putExtras(parametros);
				context.startActivity(i);	
				//ALTERAR NA TELA DE DESPESA SE RECEBER UM BUNDLE ELE POPULAR OS CAMPOS
			}
		});
		
		return convertView;
	}
	
	
	
	static class ViewHolder{
		TextView txtFornecedor;
		TextView txtData;
		TextView txtValor;
		Button btnAlterar;
		Button btnExcluir;
	}
	
	
}

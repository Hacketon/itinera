package br.com.itinera.itineramobile.adapter;

import java.util.List;

import br.com.itinera.itineramobile.R;
import br.com.itinera.itineramobile.bean.Despesa;
import br.com.itinera.itineramobile.util.Moeda;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ListaDespesaAdapter extends ArrayAdapter<Despesa> {

	private LayoutInflater inflater;
	
	public ListaDespesaAdapter(Activity activity, List<Despesa> listaDespesa){
		super(activity, R.layout.item_lista_despesa, listaDespesa);
		this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		Despesa d = getItem(position);
		holder.txtFornecedor.setText(d.getNomeFornecedor());
		holder.txtData.setText(d.getData());
		holder.txtValor.setText(Moeda.mascaraDinheiro(d.getValor(), Moeda.DINHEIRO_REAL));
		
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

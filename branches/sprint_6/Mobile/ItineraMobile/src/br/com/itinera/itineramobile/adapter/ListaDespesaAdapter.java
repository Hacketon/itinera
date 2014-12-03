package br.com.itinera.itineramobile.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.com.itinera.itineramobile.R;
import br.com.itinera.itineramobile.bean.Despesa;
import br.com.itinera.itineramobile.util.Moeda;

public class ListaDespesaAdapter extends ArrayAdapter<Despesa> {

	//private LayoutInflater inflater;
	private Context context;
	private List<Despesa> listaDespesas;
	private LayoutInflater inflater;
	
	public ListaDespesaAdapter(Context context, List<Despesa> listaDespesa){
		super(context, R.layout.item_lista_despesa, listaDespesa);
		this.context = context;
		this.listaDespesas = listaDespesa;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	} 
	
	
	@Override
	public void notifyDataSetChanged(){
		super.notifyDataSetChanged();
	}
	
	//Remover item da lista
    public void remove(final Despesa item) {
        this.listaDespesas.remove(item);
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		View view = convertView;
		
		if(view == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view  = inflater.inflate(R.layout.item_lista_despesa, parent,false);
			holder = new ViewHolder();
			holder.txtFornecedor = (TextView)view.findViewById(R.id.txtItemFornecedor);
			holder.txtData = (TextView)view.findViewById(R.id.txtItemData);
			holder.txtValor = (TextView)view.findViewById(R.id.txtItemValor);
			
			view.setTag(holder);
		}else{
			holder = (ViewHolder) view.getTag();
		}
		
		Despesa d = getItem(position);
		holder.txtFornecedor.setText(d.getNomeFornecedor());
		holder.txtData.setText(d.getData());
		holder.txtValor.setText(Moeda.mascaraDinheiro(d.getValor(), Moeda.DINHEIRO_REAL));
		
		return view;
	}

	static class ViewHolder{
		TextView txtFornecedor;
		TextView txtData;
		TextView txtValor;
//		Button btnExcluir;
	//	Button btnAlterar;
		
	}
	
	
}

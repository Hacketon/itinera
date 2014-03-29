package br.com.itinera.itineramobile.tarefa;

import java.util.ArrayList;
import java.util.List;

import br.com.itinera.itineramobile.Login;
import br.com.itinera.itineramobile.PesquisarDespesa;
import br.com.itinera.itineramobile.banco.DatabaseHelper;
import br.com.itinera.itineramobile.bean.Despesa;

import android.os.AsyncTask;

public class PesquisaDespesaTarefa extends AsyncTask<String, List<Despesa>, String> {

	private PesquisarDespesa activity;
	private String campo;
	private String pesquisa;
	private List<Despesa> listaDespesa;
	
	public PesquisaDespesaTarefa(PesquisarDespesa activity, String campo, String pesquisa){
		this.activity = activity;
		this.campo = campo;
		this.pesquisa = pesquisa;
	}
	
	@Override
	protected String doInBackground(String... arg0) {
		// Pesquisa de despesas em segundo plano
		listaDespesa = new DatabaseHelper(activity).pesquisarPorCampo(campo, pesquisa);
		return "";
	}
	
	@Override
	protected void onPostExecute(String result) {
		if(listaDespesa == null){
			listaDespesa = new ArrayList<Despesa>();
		}
		
		activity.montarListaAposProcessamento(listaDespesa);
	}
	
	

}

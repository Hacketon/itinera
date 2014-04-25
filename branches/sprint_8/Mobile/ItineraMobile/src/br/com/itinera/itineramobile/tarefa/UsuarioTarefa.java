package br.com.itinera.itineramobile.tarefa;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import br.com.itinera.itineramobile.Login;
import br.com.itinera.itineramobile.bean.Usuario;
import br.com.itinera.itineramobile.config.Configuracao;
import br.com.itinera.itineramobile.converter.UsuarioConverter;
import br.com.itinera.itineramobile.util.HTTPUtil;

public class UsuarioTarefa extends AsyncTask<String, String, String> {

	private String retornoValidacao = "";
	private Login activity;
	private Usuario usuario;
	
	public UsuarioTarefa(Login activity){
		this.activity = activity;
	}
	
	private String logar(String usuario, String senha){
		try {
		String url = Configuracao.URL_BASE + Configuracao.LOGAR + usuario;
		
			String jsonResponse = HTTPUtil.doGet(url);
			JSONObject objJson = new  JSONObject(jsonResponse);
			Usuario usuarioRetorno = UsuarioConverter.converter(objJson);
			
			if(usuarioRetorno.isAtivo()){
				if(usuarioRetorno.getSenha().equals(criptografarSenha(senha))){
					retornoValidacao = "ok";
					this.usuario = usuarioRetorno;
				}else{
					retornoValidacao = "Senha inválida";
				}
			}else{
				retornoValidacao = "Usuário inativo";
			}
			
		} catch (Exception e) {
			retornoValidacao = "Usuário ou senha inválido!";
		}
		return retornoValidacao;
	}

	@Override
	protected String doInBackground(String... params) {
		String login = params[0];
		String senha = params[1];
		
		return logar(login, senha);
	}
	
	
	
	@Override
	protected void onPostExecute(String result) {
		activity.acaoAposLogin(result, usuario);
	}

	private String criptografarSenha(String senhaDigitada){
		//Encrypt encriptador = new Encrypt(senhaDigitada,"MD5","UTF-8");
		return "gBd1NKDJmn42RbUvICekiw==";
	}
	
}

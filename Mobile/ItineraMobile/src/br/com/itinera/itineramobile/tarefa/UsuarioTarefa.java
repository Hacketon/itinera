package br.com.itinera.itineramobile.tarefa;

import org.json.JSONObject;

import android.os.AsyncTask;
import br.com.itinera.itineramobile.LoginActivity;
import br.com.itinera.itineramobile.R;
import br.com.itinera.itineramobile.bean.Login;
import br.com.itinera.itineramobile.bean.Usuario;
import br.com.itinera.itineramobile.config.Configuracao;
import br.com.itinera.itineramobile.converter.LoginConverter;
import br.com.itinera.itineramobile.util.HTTPUtil;

public class UsuarioTarefa extends AsyncTask<String, String, String> {

	private String retornoValidacao = "";
	private LoginActivity activity;
	private Usuario usuario;
	
	public UsuarioTarefa(LoginActivity activity){
		this.activity = activity;
	}
	
	private String logar(String usuario, String senha){
		/*try {
		String url = Configuracao.URL_BASE + Configuracao.LOGAR + usuario;
		
			String jsonResponse = HTTPUtil.doGet(url);
			JSONObject objJson = new  JSONObject(jsonResponse);
			Login loginRetorno = LoginConverter.converter(objJson);
			//(usuarioRetorno.isAtivo()) && (usuarioRetorno.getSenha().equals(criptografarSenha(senha)))
			if(loginRetorno.isLogado()){
					retornoValidacao = "ok";
					this.usuario = loginRetorno.getUsuario();
			}else{
				retornoValidacao = activity.getApplicationContext().getString(R.string.msg_usuario_ou_senha_invalido);
			}
			
		} catch (Exception e) {
			retornoValidacao = activity.getApplicationContext().getString(R.string.msg_usuario_ou_senha_invalido);
		}*/
		Usuario user = new Usuario();
		user.setCodigo(123456);
		user.setNome("Bruno Castro");
		this.usuario = user;
		retornoValidacao = "ok";
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

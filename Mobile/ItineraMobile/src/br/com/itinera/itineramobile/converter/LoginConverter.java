package br.com.itinera.itineramobile.converter;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.itinera.itineramobile.bean.Login;
import br.com.itinera.itineramobile.bean.Usuario;

public class LoginConverter {
	
	public static Login converter(JSONObject obj) throws JSONException{
	Login u = new Login();
	
	Usuario user = new Usuario();
	
	user.setAtivo(obj.getBoolean("ativo"));
	user.setCodigo(obj.getInt("idUsuario"));
	user.setLogin(obj.getString("login"));
	user.setNome(obj.getString("nome"));
	user.setSenha(obj.getString("senha"));
	
	u.setUsuario(user);
	//retirar apos logar estiver conectando webservice
	u.setLogado(true);
	
	return u;
}

}

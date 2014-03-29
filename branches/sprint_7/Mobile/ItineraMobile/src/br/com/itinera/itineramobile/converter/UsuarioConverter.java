package br.com.itinera.itineramobile.converter;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.itinera.itineramobile.bean.Usuario;

public class UsuarioConverter {

	public static Usuario converter(JSONObject obj) throws JSONException{
		Usuario u = new Usuario();
		
		u.setAtivo(obj.getBoolean("ativo"));
		u.setCodigo(obj.getInt("idUsuario"));
		u.setLogin(obj.getString("login"));
		u.setNome(obj.getString("nome"));
		u.setSenha(obj.getString("senha"));
		
		return u;
	}
	
}

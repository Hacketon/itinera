package br.com.itinera.itineramobile.bean;

public class Usuario {

	private int codigo;
	private String login;
	private String senha;
	private String nome;
	private boolean ativo;
	
	public int getCodigo(){return codigo;}
	public String getLogin(){return login;}
	public String getSenha(){return senha;}
	public String getNome(){return nome;}
	public boolean isAtivo(){return ativo;}
	
	public void setCodigo(int codigo){this.codigo = codigo;}
	public void setLogin(String login){this.login = login;}
	public void setSenha(String senha){this.senha = senha;}
	public void setNome(String nome){this.nome = nome;}
	public void setAtivo(boolean ativo){this.ativo = ativo;}	
}

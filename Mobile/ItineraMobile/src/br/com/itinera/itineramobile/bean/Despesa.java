package br.com.itinera.itineramobile.bean;

import java.io.Serializable;
import java.util.Date;

public class Despesa implements Serializable{

	private long _id;
	private int codigoDespesa;
	private int codigoUsuario;
	private String nomeFornecedor;
	private String numeroDocumento;
	private Double valor;
	private String data;
	private String tipoDespesa;
	
	public int getCodigoDespesa(){return codigoDespesa;}
	public int getCodigoUsuario(){return codigoUsuario;}
	public String getNomeFornecedor(){return nomeFornecedor;}
	public String getNumeroDocumento(){return numeroDocumento;}
	public Double getValor(){return valor;}
	public String getData(){return data;}
	public String getTipoDespesa(){return tipoDespesa;}
	public long get_id() { return _id;	}
	
	public void setCodigoDespesa(int codigoDespesa){this.codigoDespesa = codigoDespesa;}
	public void setCodigoUsuario(int codigoUsuario){this.codigoUsuario = codigoUsuario;}
	public void setNomeFornecedor(String nomeFornecedor){this.nomeFornecedor = nomeFornecedor;}
	public void setNumeroDocumento(String numeroDocumento){this.numeroDocumento = numeroDocumento;}
	public void setValor(Double valor){this.valor = valor;}
	public void setData(String data){this.data = data;}
	public void setTipoDespesa(String tipoDespesa){this.tipoDespesa = tipoDespesa;}
	public void set_id(long _id) { this._id = _id; }
	
}

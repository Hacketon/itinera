
package br.com.itinera.modelo;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author lesena
 */
@Embeddable
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "cep")
    private String cep;
    
    @Column(name = "tipo_logradouro")
    private String tipoLogradouro;
    
    @Column(name = "nome_logradouro")
    private String nomeLogradouro;
    
    @Column(name = "numero")
    private BigInteger numero;
    
    @Column(name = "complemento")
    private String complemento;
    
    @Column(name = "bairro")
    private String bairro;
    
    public Endereco() {
        this.tipoLogradouro = "Avenida";
    }

    public Endereco(String cep, String tipoLogradouro, String nomeLogradouro, BigInteger numero, String complemento, String bairro) {
        this.cep = cep;
        this.tipoLogradouro = tipoLogradouro;
        this.nomeLogradouro = nomeLogradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
    }


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    public BigInteger getNumero() {
        return numero;
    }

    public void setNumero(BigInteger numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cep != null ? cep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ( ((this.cep == null && other.cep != null) || (this.cep != null && !this.cep.equals(other.cep)))
             && ((this.tipoLogradouro == null && other.tipoLogradouro != null) || (this.tipoLogradouro != null && !this.tipoLogradouro.equals(other.tipoLogradouro)) ) 
             && ((this.nomeLogradouro == null && other.nomeLogradouro != null) || (this.nomeLogradouro != null && !this.nomeLogradouro.equals(other.nomeLogradouro)) )
             && ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero)) ) 
             && ((this.complemento == null && other.complemento != null) || (this.complemento != null && !this.complemento.equals(other.complemento)) ) 
             && ((this.bairro == null && other.bairro != null) || (this.bairro != null && !this.bairro.equals(other.bairro)) ) 
//             && ((this.idMunicipio == null && other.idMunicipio != null) || (this.idMunicipio != null && !this.idMunicipio.equals(other.idMunicipio)) ) 
                ){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tipoLogradouro + " " + this.nomeLogradouro + ", " + this.getNumero() + " - " +
               ((this.complemento.isEmpty())?"":this.complemento + " - ") + this.bairro;
    }
    
}

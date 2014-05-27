/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bruno-note
 */
@Entity
@Table(name = "telefone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Telefone.findAll", query = "SELECT t FROM Telefone t"),
    @NamedQuery(name = "Telefone.findByTelefoneId", query = "SELECT t FROM Telefone t WHERE t.telefoneId = :telefoneId"),
    @NamedQuery(name = "Telefone.findByTelefoneNumero", query = "SELECT t FROM Telefone t WHERE t.telefoneNumero = :telefoneNumero"),
    @NamedQuery(name = "Telefone.findByTelefoneTipo", query = "SELECT t FROM Telefone t WHERE t.telefoneTipo = :telefoneTipo"),
    @NamedQuery(name = "Telefone.findByTelefoneDescricao", query = "SELECT t FROM Telefone t WHERE t.telefoneDescricao = :telefoneDescricao"),
    @NamedQuery(name = "Telefone.findByTelefoneRamal", query = "SELECT t FROM Telefone t WHERE t.telefoneRamal = :telefoneRamal")})
public class Telefone implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "telefone_id")
    @SequenceGenerator(name = "Telefone_Generator", schema = "public", sequenceName = "seq_telefone", allocationSize=1 )
    @GeneratedValue(generator = "Telefone_Generator", strategy = GenerationType.SEQUENCE )
    private BigDecimal telefoneId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "telefone_numero")
    private String telefoneNumero;
    //@Basic(optional = false)
    //@NotNull
    @Column(name = "telefone_tipo")
    private char telefoneTipo;
    @Size(max = 200)
    @Column(name = "telefone_descricao")
    private String telefoneDescricao;
    @Column(name = "telefone_ramal")
    private BigInteger telefoneRamal;

    public Telefone() {
    }

    public Telefone(BigDecimal telefoneId) {
        this.telefoneId = telefoneId;
    }

    public Telefone(BigDecimal telefoneId, String telefoneNumero, char telefoneTipo) {
        this.telefoneId = telefoneId;
        this.telefoneNumero = telefoneNumero;
        this.telefoneTipo = telefoneTipo;
    }

    public BigDecimal getTelefoneId() {
        return telefoneId;
    }

    public void setTelefoneId(BigDecimal telefoneId) {
        this.telefoneId = telefoneId;
    }

    public String getTelefoneNumero() {
        return telefoneNumero;
    }

    public void setTelefoneNumero(String telefoneNumero) {
        this.telefoneNumero = telefoneNumero;
    }

    public char getTelefoneTipo() {
        return telefoneTipo;
    }

    public void setTelefoneTipo(char telefoneTipo) {
        this.telefoneTipo = telefoneTipo;
    }

    public String getTelefoneDescricao() {
        return telefoneDescricao;
    }

    public void setTelefoneDescricao(String telefoneDescricao) {
        this.telefoneDescricao = telefoneDescricao;
    }

    public BigInteger getTelefoneRamal() {
        return telefoneRamal;
    }

    public void setTelefoneRamal(BigInteger telefoneRamal) {
        this.telefoneRamal = telefoneRamal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (telefoneId != null ? telefoneId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefone)) {
            return false;
        }
        Telefone other = (Telefone) object;
        if ((this.telefoneId == null && other.telefoneId != null) || (this.telefoneId != null && !this.telefoneId.equals(other.telefoneId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Telefone[ telefoneId=" + telefoneId + " ]";
    }
    
}

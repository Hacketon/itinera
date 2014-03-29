/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lesena
 */
@Entity
@Table(name = "rota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rota.findAll", query = "SELECT r FROM Rota r"),
    @NamedQuery(name = "Rota.findByIdRota", query = "SELECT r FROM Rota r WHERE r.idRota = :idRota"),
    @NamedQuery(name = "Rota.findByCodigoRota", query = "SELECT r FROM Rota r WHERE r.codigoRota = :codigoRota"),
    @NamedQuery(name = "Rota.findByDescricao", query = "SELECT r FROM Rota r WHERE r.descricao = :descricao"),
    @NamedQuery(name = "Rota.findByDistancia", query = "SELECT r FROM Rota r WHERE r.distancia = :distancia"),
    @NamedQuery(name = "Rota.findByAtivo", query = "SELECT r FROM Rota r WHERE r.ativo = :ativo")})
public class Rota implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rota")
     //Anotations referentes à sequence
    @SequenceGenerator(name="Rota_Generator", sequenceName="seq_rota", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Rota_Generator")
    //fim anotations Sequence
    private BigDecimal idRota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo_rota")
    private String codigoRota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "distancia")
    private BigInteger distancia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;
    @JoinColumn(name = "tipo_rota", referencedColumnName = "id_tipo_rota")
    @ManyToOne(optional = false)
    private TipoRota tipoRota;

    public Rota() {
    }

    public Rota(BigDecimal idRota) {
        this.idRota = idRota;
    }

    public Rota(BigDecimal idRota, String codigoRota, String descricao, BigInteger distancia, boolean ativo) {
        this.idRota = idRota;
        this.codigoRota = codigoRota;
        this.descricao = descricao;
        this.distancia = distancia;
        this.ativo = ativo;
    }

    public BigDecimal getIdRota() {
        return idRota;
    }

    public void setIdRota(BigDecimal idRota) {
        this.idRota = idRota;
    }

    public String getCodigoRota() {
        return codigoRota;
    }

    public void setCodigoRota(String codigoRota) {
        this.codigoRota = codigoRota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigInteger getDistancia() {
        return distancia;
    }

    public void setDistancia(BigInteger distancia) {
        this.distancia = distancia;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public TipoRota getTipoRota() {
        return tipoRota;
    }

    public void setTipoRota(TipoRota tipoRota) {
        this.tipoRota = tipoRota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRota != null ? idRota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rota)) {
            return false;
        }
        Rota other = (Rota) object;
        if ((this.idRota == null && other.idRota != null) || (this.idRota != null && !this.idRota.equals(other.idRota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Rota[ idRota=" + idRota + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ponto_coleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PontoColeta.findAll", query = "SELECT p FROM PontoColeta p"),
    @NamedQuery(name = "PontoColeta.findByIdPontoColeta", query = "SELECT p FROM PontoColeta p WHERE p.idPontoColeta = :idPontoColeta"),
    @NamedQuery(name = "PontoColeta.findByDescricao", query = "SELECT p FROM PontoColeta p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "PontoColeta.findByLatitude", query = "SELECT p FROM PontoColeta p WHERE p.latitude = :latitude"),
    @NamedQuery(name = "PontoColeta.findByLongitude", query = "SELECT p FROM PontoColeta p WHERE p.longitude = :longitude"),
    @NamedQuery(name = "PontoColeta.findByAtivo", query = "SELECT p FROM PontoColeta p WHERE p.ativo = :ativo"),
    @NamedQuery(name = "PontoColeta.findByEmpresa",  query = "SELECT p FROM PontoColeta p WHERE p.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "PontoColeta.findByEndereco", query = "SELECT p FROM PontoColeta p WHERE p.idEndereco = :idEndereco")})
public class PontoColeta implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_ponto_coleta")
     //Anotations referentes à sequence
    @SequenceGenerator(name="PontoColeta_Generator", sequenceName="seq_ponto_coleta", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PontoColeta_Generator")
    //fim anotations Sequence
    private BigDecimal idPontoColeta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 20)
    @Column(name = "latitude")
    private String latitude;
    @Size(max = 20)
    @Column(name = "longitude")
    private String longitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
    @ManyToOne(optional = false)
    private Endereco idEndereco;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne
    private Empresa idEmpresa;

    public PontoColeta() {
    }

    public PontoColeta(BigDecimal idPontoColeta) {
        this.idPontoColeta = idPontoColeta;
    }

    public PontoColeta(BigDecimal idPontoColeta, String descricao, boolean ativo) {
        this.idPontoColeta = idPontoColeta;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public BigDecimal getIdPontoColeta() {
        return idPontoColeta;
    }

    public void setIdPontoColeta(BigDecimal idPontoColeta) {
        this.idPontoColeta = idPontoColeta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Endereco getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Endereco idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPontoColeta != null ? idPontoColeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PontoColeta)) {
            return false;
        }
        PontoColeta other = (PontoColeta) object;
        if ((this.idPontoColeta == null && other.idPontoColeta != null) || (this.idPontoColeta != null && !this.idPontoColeta.equals(other.idPontoColeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.PontoColeta[ idPontoColeta=" + idPontoColeta + " ]";
    }
    
}

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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lesena
 */
@Entity
@Table(name = "municipio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m"),
    @NamedQuery(name = "Municipio.findByIdMunicipio", query = "SELECT m FROM Municipio m WHERE m.idMunicipio = :idMunicipio"),
    @NamedQuery(name = "Municipio.findByCodigoMunicipio", query = "SELECT m FROM Municipio m WHERE m.codigoMunicipio = :codigoMunicipio"),
    @NamedQuery(name = "Municipio.findByNomeMunicipio", query = "SELECT m FROM Municipio m WHERE m.nomeMunicipio like :nomeMunicipio"),
    @NamedQuery(name = "Municipio.findByDddMunicipio", query = "SELECT m FROM Municipio m WHERE m.dddMunicipio = :dddMunicipio"),
    @NamedQuery(name = "Municipio.findByLikeName",query="SELECT m FROM Municipio m WHERE m.nomeMunicipio like :nomeMunicipio or m.codigoEstado.siglaEstado like :nomeMunicipio")})
public class Municipio implements Serializable {
    @OneToMany(mappedBy = "idMunicipio")
    private List<Motorista> motoristaList;
    @OneToMany(mappedBy = "idMunicipio")
    private List<Empresa> empresaList;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_municipio")
    private BigDecimal idMunicipio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_municipio")
    private BigInteger codigoMunicipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nome_municipio")
    private String nomeMunicipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "ddd_municipio")
    private String dddMunicipio;
    @JoinColumn(name = "codigo_estado", referencedColumnName = "codigo_estado")
    @ManyToOne(optional = false)
    private Estado codigoEstado;

    public Municipio() {
        codigoEstado = new Estado();
    }

    public Municipio(BigDecimal idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Municipio(BigDecimal idMunicipio, BigInteger codigoMunicipio, String nomeMunicipio, String dddMunicipio) {
        this.idMunicipio = idMunicipio;
        this.codigoMunicipio = codigoMunicipio;
        this.nomeMunicipio = nomeMunicipio;
        this.dddMunicipio = dddMunicipio;
    }

    public BigDecimal getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(BigDecimal idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public BigInteger getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(BigInteger codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public String getDddMunicipio() {
        return dddMunicipio;
    }

    public void setDddMunicipio(String dddMunicipio) {
        this.dddMunicipio = dddMunicipio;
    }

    public Estado getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(Estado codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMunicipio != null ? idMunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.idMunicipio == null && other.idMunicipio != null) || (this.idMunicipio != null && !this.idMunicipio.equals(other.idMunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nomeMunicipio + " - " + this.codigoEstado;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @XmlTransient
    public List<Motorista> getMotoristaList() {
        return motoristaList;
    }

    public void setMotoristaList(List<Motorista> motoristaList) {
        this.motoristaList = motoristaList;
    }
    
}

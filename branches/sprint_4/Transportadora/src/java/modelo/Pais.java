/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "pais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p"),
    @NamedQuery(name = "Pais.findBySiglaPais", query = "SELECT p FROM Pais p WHERE p.siglaPais = :siglaPais"),
    @NamedQuery(name = "Pais.findByCodigoPais", query = "SELECT p FROM Pais p WHERE p.codigoPais = :codigoPais"),
    @NamedQuery(name = "Pais.findByNomePais", query = "SELECT p FROM Pais p WHERE p.nomePais = :nomePais"),
    @NamedQuery(name = "Pais.findByIdPais", query = "SELECT p FROM Pais p WHERE p.idPais = :idPais"),
    @NamedQuery(name = "Pais.findByDdiPais", query = "SELECT p FROM Pais p WHERE p.ddiPais = :ddiPais")})
public class Pais implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "sigla_pais")
    private String siglaPais;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_pais")
    private BigInteger codigoPais;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nome_pais")
    private String nomePais;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pais")
    private BigDecimal idPais;
    @Size(max = 3)
    @Column(name = "ddi_pais")
    private String ddiPais;
    @OneToMany(mappedBy = "idPais")
    private Collection<Estado> estadoCollection;

    public Pais() {
    }

    public Pais(BigDecimal idPais) {
        this.idPais = idPais;
    }

    public Pais(BigDecimal idPais, String siglaPais, BigInteger codigoPais, String nomePais) {
        this.idPais = idPais;
        this.siglaPais = siglaPais;
        this.codigoPais = codigoPais;
        this.nomePais = nomePais;
    }

    public String getSiglaPais() {
        return siglaPais;
    }

    public void setSiglaPais(String siglaPais) {
        this.siglaPais = siglaPais;
    }

    public BigInteger getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(BigInteger codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getNomePais() {
        return nomePais;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }

    public BigDecimal getIdPais() {
        return idPais;
    }

    public void setIdPais(BigDecimal idPais) {
        this.idPais = idPais;
    }

    public String getDdiPais() {
        return ddiPais;
    }

    public void setDdiPais(String ddiPais) {
        this.ddiPais = ddiPais;
    }

    @XmlTransient
    public Collection<Estado> getEstadoCollection() {
        return estadoCollection;
    }

    public void setEstadoCollection(Collection<Estado> estadoCollection) {
        this.estadoCollection = estadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPais != null ? idPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.idPais == null && other.idPais != null) || (this.idPais != null && !this.idPais.equals(other.idPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Pais[ idPais=" + idPais + " ]";
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itinera.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author cobra
 */
@Entity
@Table(name = "tipo_rota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRota.findAll", query = "SELECT t FROM TipoRota t ORDER BY t.idTipoRota DESC"),
    @NamedQuery(name = "TipoRota.findByIdTipoRota", query = "SELECT t FROM TipoRota t WHERE t.idTipoRota = :idTipoRota"),
    @NamedQuery(name = "TipoRota.findByDescricao", query = "SELECT t FROM TipoRota t WHERE t.descricao = :descricao")})
public class TipoRota implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoRota")
    private List<Rota> rotaList;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_rota")
     //Anotations referentes à sequence
    @SequenceGenerator(name="Tipo_Rota_Generator", sequenceName="seq_tipo_rota", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Tipo_Rota_Generator")
    //fim anotations Sequence
    private BigDecimal idTipoRota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descricao",unique=true)
    private String descricao;

    public TipoRota() {
    }

    public TipoRota(BigDecimal idTipoRota) {
        this.idTipoRota = idTipoRota;
    }

    public TipoRota(BigDecimal idTipoRota, String descricao) {
        this.idTipoRota = idTipoRota;
        this.descricao = descricao;
    }

    public BigDecimal getIdTipoRota() {
        return idTipoRota;
    }

    public void setIdTipoRota(BigDecimal idTipoRota) {
        this.idTipoRota = idTipoRota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoRota != null ? idTipoRota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRota)) {
            return false;
        }
        TipoRota other = (TipoRota) object;
        if ((this.idTipoRota == null && other.idTipoRota != null) || (this.idTipoRota != null && !this.idTipoRota.equals(other.idTipoRota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TipoRota[ idTipoRota=" + idTipoRota + " ]";
    }

    @XmlTransient
    public List<Rota> getRotaList() {
        return rotaList;
    }

    public void setRotaList(List<Rota> rotaList) {
        this.rotaList = rotaList;
    }
    
}

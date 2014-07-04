
package br.com.itinera.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "plano_contas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanoContas.findAll", query = "SELECT p FROM PlanoContas p"),
    @NamedQuery(name = "PlanoContas.findByPlanoContasId", query = "SELECT p FROM PlanoContas p WHERE p.planoContasId = :planoContasId"),
    @NamedQuery(name = "PlanoContas.findByDescricao", query = "SELECT p FROM PlanoContas p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "PlanoContas.findByTipoCombustivel", query = "SELECT p FROM PlanoContas p WHERE p.tipoCombustivel = :tipoCombustivel")})
public class PlanoContas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name="plano_contas_sequence", sequenceName = "seq_plano_contas", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plano_contas_sequence")
    @Column(name = "plano_contas_id")
    private BigDecimal planoContasId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_combustivel")
    private boolean tipoCombustivel;

    public PlanoContas() {
    }

    public PlanoContas(BigDecimal id, String descricao, Boolean tipoCombustivel) {
        this.planoContasId = id;
        this.descricao = descricao;
        this.tipoCombustivel = tipoCombustivel;
    }

    public BigDecimal getPlanoContasId() {
        return planoContasId;
    }

    public void setPlanoContasId(BigDecimal planoContasId) {
        this.planoContasId = planoContasId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(boolean tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planoContasId != null ? planoContasId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PlanoContas)) {
            return false;
        }
        PlanoContas other = (PlanoContas) object;
        if ((this.planoContasId == null && other.planoContasId != null) || (this.planoContasId != null && !this.planoContasId.equals(other.planoContasId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.itinera.modelo.PlanoContas[ planoContasId=" + planoContasId + " ]";
    }
    
}

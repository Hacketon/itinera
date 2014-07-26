
package br.com.itinera.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "despesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Despesa.findAll", query = "SELECT d FROM Despesa d"),
    @NamedQuery(name = "Despesa.findByIdDespesa", query = "SELECT d FROM Despesa d WHERE d.idDespesa = :idDespesa"),
    @NamedQuery(name = "Despesa.findByStatus", query = "SELECT d FROM Despesa d WHERE d.status = :status")})
public class Despesa implements Serializable {
    private static final long serialVersionUID = 1L;
//    private Collection<Despesa> despesaCollection;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "despesa_id")
    @SequenceGenerator(name = "Despesa_Generator",sequenceName = "seq_despesa", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Despesa_Generator")
    private BigDecimal idDespesa;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "num_docto")
    private String numDocto;

    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "local")
    private String local;

    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;

    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade")
    private BigDecimal quantidade;

    @Column(name = "hodometro")
    private BigInteger hodometro;

    @Basic(optional = false)
    @Column(name = "status")
    private String status;

    @Size(max = 250)
    @Column(name = "observacao")
    private String observacao;

    @JoinColumn(name = "motorista", referencedColumnName = "motorista_id")
    @ManyToOne(optional = false)
    private Motorista motorista;

    @JoinColumn(name = "veiculo", referencedColumnName = "id_veiculo")
    @ManyToOne(optional = false)
    private Veiculo veiculo;
    
    @JoinColumn(name = "fornecedor", referencedColumnName = "id_empresa")
    @ManyToOne(optional = false)
    private Empresa fornecedor;

    @JoinColumn(name = "plano_contas", referencedColumnName = "plano_contas_id")
    @ManyToOne(optional = false)
    private PlanoContas planoContas;

    public Despesa() {
    }

    public Despesa(BigDecimal idDespesa) {
        this.idDespesa = idDespesa;
    }

    public Despesa(BigDecimal idDespesa, String numDocto, Date data, Motorista motorista, PlanoContas planoContas, String local, BigDecimal valor, BigDecimal quantidade, String status) {
        this.idDespesa = idDespesa;
        this.numDocto = numDocto;
        this.data = data;
        this.motorista = motorista;
        this.planoContas = planoContas;
        this.local = local;
        this.valor = valor;
        this.quantidade = quantidade;
        this.status = status;
    }

    public BigDecimal getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(BigDecimal idDespesa) {
        this.idDespesa = idDespesa;
    }

    public String getNumDocto() {
        return numDocto;
    }

    public void setNumDocto(String numDocto) {
        this.numDocto = numDocto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public PlanoContas getPlanoContas() {
        return planoContas;
    }

    public void setPlanoContas(PlanoContas planoContas) {
        this.planoContas = planoContas;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigInteger getHodometro() {
        return hodometro;
    }

    public void setHodometro(BigInteger hodometro) {
        this.hodometro = hodometro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Empresa getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Empresa fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDespesa != null ? idDespesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Despesa)) {
            return false;
        }
        Despesa other = (Despesa) object;
        if ((this.idDespesa == null && other.idDespesa != null) || (this.idDespesa != null && !this.idDespesa.equals(other.idDespesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Despesa[ idDespesa=" + idDespesa + " ]";
    }
    
//    @XmlTransient
//    public Collection<Despesa> getVeiculoCollection() {
//        return despesaCollection;
//    }
//
//    public void setVeiculoCollection(Collection<Despesa> despesaCollection) {
//        this.despesaCollection = despesaCollection;
//    }
    
}

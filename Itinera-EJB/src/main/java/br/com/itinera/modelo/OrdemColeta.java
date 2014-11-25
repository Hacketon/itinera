
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lesena
 */
@Entity
@Table(name = "ordem_coleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdemColeta.findAll", query = "SELECT o FROM OrdemColeta o"),
    @NamedQuery(name = "OrdemColeta.findByOrdemColetaId", query = "SELECT o FROM OrdemColeta o WHERE o.ordemColetaId = :ordemColetaId"),
    @NamedQuery(name = "OrdemColeta.findByDataOrdemColeta", query = "SELECT o FROM OrdemColeta o WHERE o.dataOrdemColeta = :dataOrdemColeta"),
//    @NamedQuery(name = "OrdemColeta.findByRota", query = "SELECT o FROM OrdemColeta o WHERE o.rota like :rota"),
    @NamedQuery(name = "OrdemColeta.findByValorUnitario", query = "SELECT o FROM OrdemColeta o WHERE o.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "OrdemColeta.findByQuantidade", query = "SELECT o FROM OrdemColeta o WHERE o.quantidade = :quantidade"),
    @NamedQuery(name = "OrdemColeta.findByValorTotal", query = "SELECT o FROM OrdemColeta o WHERE o.valorTotal = :valorTotal"),
    @NamedQuery(name = "OrdemColeta.findByNumeroNota", query = "SELECT o FROM OrdemColeta o WHERE o.numeroNota = :numeroNota"),
    @NamedQuery(name = "OrdemColeta.findByPeriodoInicioFimNF", query = "SELECT o FROM OrdemColeta o WHERE o.numeroNota = :numeroNota and o.dataOrdemColeta >= :inicio and o.dataOrdemColeta <= :fim"),
    @NamedQuery(name = "OrdemColeta.findByPeriodoInicioNF", query = "SELECT o FROM OrdemColeta o WHERE o.numeroNota = :numeroNota and o.dataOrdemColeta >= :inicio"),
    @NamedQuery(name = "OrdemColeta.findByPeriodoFimNF", query = "SELECT o FROM OrdemColeta o WHERE o.numeroNota = :numeroNota and o.dataOrdemColeta <= :fim"),
    @NamedQuery(name = "OrdemColeta.findByPeriodoInicio", query = "SELECT o FROM OrdemColeta o WHERE o.dataOrdemColeta >= :inicio"),
    @NamedQuery(name = "OrdemColeta.findByPeriodoFim", query = "SELECT o FROM OrdemColeta o WHERE o.dataOrdemColeta <= :fim"),
    @NamedQuery(name = "OrdemColeta.findByPeriodo", query = "SELECT o FROM OrdemColeta o WHERE o.dataOrdemColeta >= :inicio and o.dataOrdemColeta <= :fim"),
    @NamedQuery(name = "OrdemColeta.findByDataEmissaoNf", query = "SELECT o FROM OrdemColeta o WHERE o.dataEmissaoNf like :dataEmissaoNf")})
public class OrdemColeta implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ordem_coleta_id")
     //Anotations referentes à sequence
    @SequenceGenerator(name="Ordem_Coleta_Generator", sequenceName="seq_ordem_coleta", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Ordem_Coleta_Generator")
    //fim anotations Sequence
    private BigDecimal ordemColetaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_ordem_coleta")
    @Temporal(TemporalType.DATE)
    private Date dataOrdemColeta;
    
    @NotNull
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;
    
    @NotNull
    @Column(name = "quantidade")
    private BigInteger quantidade;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    
    @NotNull
    @Column(name = "distancia")
    private BigDecimal distancia;
    
    @Column(name = "numero_nota")
    private BigInteger numeroNota;
    
    @Column(name = "data_emissao_nf")
    @Temporal(TemporalType.DATE)
    private Date dataEmissaoNf;
    
    @JoinColumn(name = "veiculo_id", referencedColumnName = "id_veiculo")
    @ManyToOne(optional = false)
    private Veiculo veiculoId;
    
    @JoinColumn(name = "motorista_id", referencedColumnName = "motorista_id")
    @ManyToOne(optional = false)
    private Motorista motoristaId;
    
    @JoinColumn(name = "destinatario_id", referencedColumnName = "id_empresa")
    @ManyToOne(optional = false)
    private Empresa destinatarioId;
    
    @JoinColumn(name = "remetente_id", referencedColumnName = "id_empresa")
    @ManyToOne(optional = false)
    
    private Empresa remetenteId;
    
    @Transient
    private String rota;

    public OrdemColeta() {
        this.dataOrdemColeta = new Date();
        this.remetenteId = new Empresa();
        this.destinatarioId = new Empresa();
    }

    public OrdemColeta(BigDecimal ordemColetaId) {
        this.ordemColetaId = ordemColetaId;
        this.dataOrdemColeta = new Date();
        this.remetenteId = new Empresa();
        this.destinatarioId = new Empresa();
    }

    public OrdemColeta(BigDecimal ordemColetaId, Date dataOrdemColeta, BigDecimal valorUnitario, BigInteger quantidade, BigDecimal valorTotal) {
        this.ordemColetaId = ordemColetaId;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.dataOrdemColeta = new Date();
        this.remetenteId = new Empresa();
        this.destinatarioId = new Empresa();
    }

    public BigDecimal getOrdemColetaId() {
        return ordemColetaId;
    }

    public void setOrdemColetaId(BigDecimal ordemColetaId) {
        this.ordemColetaId = ordemColetaId;
    }

    public Date getDataOrdemColeta() {
        return dataOrdemColeta;
    }

    public void setDataOrdemColeta(Date dataOrdemColeta) {
        this.dataOrdemColeta = dataOrdemColeta;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigInteger getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigInteger quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getDistancia() {
        return distancia;
    }

    public void setDistancia(BigDecimal distancia) {
        this.distancia = distancia;
    }
    
    public BigInteger getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(BigInteger numeroNota) {
        this.numeroNota = numeroNota;
    }

    public Date getDataEmissaoNf() {
        return dataEmissaoNf;
    }

    public void setDataEmissaoNf(Date dataEmissaoNf) {
        this.dataEmissaoNf = dataEmissaoNf;
    }

    public Veiculo getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Veiculo veiculoId) {
        this.veiculoId = veiculoId;
    }

    public Motorista getMotoristaId() {
        return motoristaId;
    }

    public void setMotoristaId(Motorista motoristaId) {
        this.motoristaId = motoristaId;
    }

    public Empresa getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(Empresa destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public Empresa getRemetenteId() {
        return remetenteId;
    }

    public void setRemetenteId(Empresa remetenteId) {
        this.remetenteId = remetenteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordemColetaId != null ? ordemColetaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdemColeta)) {
            return false;
        }
        OrdemColeta other = (OrdemColeta) object;
        if ((this.ordemColetaId == null && other.ordemColetaId != null) || (this.ordemColetaId != null && !this.ordemColetaId.equals(other.ordemColetaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.itinera.modelo.OrdemColeta[ ordemColetaId=" + ordemColetaId + " ]";
    }
    
    public String getRota(){
        rota = "";
        rota += (this.remetenteId.toString() == null)?"":this.remetenteId.getIdMunicipio().getNomeMunicipio() + " - ";
        rota += (this.destinatarioId.toString() == null)?"":this.destinatarioId.getIdMunicipio().getNomeMunicipio();            
        return rota;
    }
    
    
}

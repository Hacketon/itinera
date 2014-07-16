package br.com.itinera.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bruno-note
 */
@Entity
@Table(name = "veiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Veiculo.findAll", query = "SELECT v FROM Veiculo v Order by v.idVeiculo DESC"),
    @NamedQuery(name = "Veiculo.findByIdVeiculo", query = "SELECT v FROM Veiculo v WHERE v.idVeiculo = :idVeiculo"),
    @NamedQuery(name = "Veiculo.findByPlacaVeiculo", query = "SELECT v FROM Veiculo v WHERE v.placaVeiculo like :placaVeiculo"),
    @NamedQuery(name = "Veiculo.findByNumeroVeiculo", query = "SELECT v FROM Veiculo v WHERE v.numeroVeiculo = :numeroVeiculo"),
    @NamedQuery(name = "Veiculo.findByMarcaVeiculo", query = "SELECT v FROM Veiculo v WHERE v.marcaVeiculo = :marcaVeiculo"),
    @NamedQuery(name = "Veiculo.findByModeloVeiculo", query = "SELECT v FROM Veiculo v WHERE v.modeloVeiculo = :modeloVeiculo"),
    @NamedQuery(name = "Veiculo.findByAnoFabricacao", query = "SELECT v FROM Veiculo v WHERE v.anoFabricacao = :anoFabricacao"),
    @NamedQuery(name = "Veiculo.findByAnoModelo", query = "SELECT v FROM Veiculo v WHERE v.anoModelo = :anoModelo"),
    @NamedQuery(name = "Veiculo.findByChassi", query = "SELECT v FROM Veiculo v WHERE v.chassi = :chassi"),
    @NamedQuery(name = "Veiculo.findByNumeroRenavan", query = "SELECT v FROM Veiculo v WHERE v.numeroRenavan = :numeroRenavan"),
    @NamedQuery(name = "Veiculo.findByExercicioAtual", query = "SELECT v FROM Veiculo v WHERE v.exercicioAtual = :exercicioAtual"),
    @NamedQuery(name = "Veiculo.findByCidadeVeiculo", query = "SELECT v FROM Veiculo v WHERE v.cidadeVeiculo = :cidadeVeiculo"),
    @NamedQuery(name = "Veiculo.findByUfVeiculo", query = "SELECT v FROM Veiculo v WHERE v.ufVeiculo = :ufVeiculo"),
    @NamedQuery(name = "Veiculo.findByTipoCombustivel", query = "SELECT v FROM Veiculo v WHERE v.tipoCombustivel = :tipoCombustivel"),
    @NamedQuery(name = "Veiculo.findByCorPredominante", query = "SELECT v FROM Veiculo v WHERE v.corPredominante = :corPredominante"),
    @NamedQuery(name = "Veiculo.findByQtdeEixos", query = "SELECT v FROM Veiculo v WHERE v.qtdeEixos = :qtdeEixos"),
    @NamedQuery(name = "Veiculo.findByKmAtual", query = "SELECT v FROM Veiculo v WHERE v.kmAtual = :kmAtual"),
    @NamedQuery(name = "Veiculo.findByMediaKmLitro", query = "SELECT v FROM Veiculo v WHERE v.mediaKmLitro = :mediaKmLitro"),
    @NamedQuery(name = "Veiculo.findByLarguraVeiculo", query = "SELECT v FROM Veiculo v WHERE v.larguraVeiculo = :larguraVeiculo"),
    @NamedQuery(name = "Veiculo.findByComprimentoVeiculo", query = "SELECT v FROM Veiculo v WHERE v.comprimentoVeiculo = :comprimentoVeiculo"),
    @NamedQuery(name = "Veiculo.findByAlturaVeiculo", query = "SELECT v FROM Veiculo v WHERE v.alturaVeiculo = :alturaVeiculo"),
    @NamedQuery(name = "Veiculo.findByPbt", query = "SELECT v FROM Veiculo v WHERE v.pbt = :pbt"),
    @NamedQuery(name = "Veiculo.findByObservacao", query = "SELECT v FROM Veiculo v WHERE v.observacao = :observacao"),
    @NamedQuery(name = "Veiculo.findByAtivo", query = "SELECT v FROM Veiculo v WHERE v.ativo = :ativo")})
public class Veiculo implements Serializable {
    @Column(name = "data_inclusao")
    @Temporal(TemporalType.DATE)
    private Date dataInclusao;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    //Annotations referentes à sequence
    @SequenceGenerator(name="Veiculo_Generator", sequenceName="seq_veiculo", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Veiculo_Generator")
    @Column(name = "id_veiculo")
    private BigDecimal idVeiculo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "placa_veiculo")
    private String placaVeiculo;
    
    @Size(max = 10)
    @Column(name = "numero_veiculo")
    private String numeroVeiculo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "marca_veiculo")
    private String marcaVeiculo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "modelo_veiculo")
    private String modeloVeiculo;
    
    @Column(name = "ano_fabricacao")
    private Short anoFabricacao;
    
    @Column(name = "ano_modelo")
    private Short anoModelo;
    
    @Size(max = 30)
    @Column(name = "chassi")
    private String chassi;
    
    @Size(max = 20)
    @Column(name = "numero_renavan")
    private String numeroRenavan;
    
    @Column(name = "exercicio_atual")
    private Short exercicioAtual;
    
    @Size(max = 100)
    @Column(name = "cidade_veiculo")
    private String cidadeVeiculo;
    
    @Size(max = 2)
    @Column(name = "uf_veiculo")
    private String ufVeiculo;
    
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "tipo_combustivel")
    private String tipoCombustivel;
    
    @Size(max = 50)
    @Column(name = "cor_predominante")
    private String corPredominante;
    
    @Basic(optional = false)
    @Column(name = "qtde_eixos")
    private BigInteger qtdeEixos;
    
    @Basic(optional = false)
    @Column(name = "km_atual")
    private BigDecimal kmAtual;
    
    @Basic(optional = false)
    @Column(name = "media_km_litro")
    private BigDecimal mediaKmLitro;
    
    @Basic(optional = false)
    @Column(name = "largura_veiculo")
    private BigDecimal larguraVeiculo;
    
    @Basic(optional = false)
    @Column(name = "comprimento_veiculo")
    private BigDecimal comprimentoVeiculo;
   
    @Basic(optional = false)
    @Column(name = "altura_veiculo")
    private BigDecimal alturaVeiculo;
    
    @Basic(optional = false)
    @Column(name = "pbt")
    private BigDecimal pbt;
    
    @Size(max = 250)
    @Column(name = "observacao")
    private String observacao;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;
    
    @JoinColumn(name = "id_categoria_veiculo", referencedColumnName = "id_categoria_veiculo")
    @ManyToOne(optional = false)
    private CategoriaVeiculo idCategoriaVeiculo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veiculoId")
    private List<OrdemColeta> ordemColetaList;
    
    public Veiculo() {
    }

    public Veiculo(BigDecimal idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Veiculo(BigDecimal idVeiculo, String placaVeiculo, String marcaVeiculo, String modeloVeiculo, String tipoCombustivel, BigInteger qtdeEixos, BigDecimal kmAtual, BigDecimal mediaKmLitro, BigDecimal larguraVeiculo, BigDecimal comprimentoVeiculo, BigDecimal alturaVeiculo, BigDecimal pbt, boolean ativo) {
        this.idVeiculo = idVeiculo;
        this.placaVeiculo = placaVeiculo;
        this.marcaVeiculo = marcaVeiculo;
        this.modeloVeiculo = modeloVeiculo;
        this.tipoCombustivel = tipoCombustivel;
        this.qtdeEixos = qtdeEixos;
        this.kmAtual = kmAtual;
        this.mediaKmLitro = mediaKmLitro;
        this.larguraVeiculo = larguraVeiculo;
        this.comprimentoVeiculo = comprimentoVeiculo;
        this.alturaVeiculo = alturaVeiculo;
        this.pbt = pbt;
        this.ativo = ativo;
    }

    public BigDecimal getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(BigDecimal idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getNumeroVeiculo() {
        return numeroVeiculo;
    }

    public void setNumeroVeiculo(String numeroVeiculo) {
        this.numeroVeiculo = numeroVeiculo;
    }

    public String getMarcaVeiculo() {
        return marcaVeiculo;
    }

    public void setMarcaVeiculo(String marcaVeiculo) {
        this.marcaVeiculo = marcaVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public Short getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Short anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Short getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Short anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getNumeroRenavan() {
        return numeroRenavan;
    }

    public void setNumeroRenavan(String numeroRenavan) {
        this.numeroRenavan = numeroRenavan;
    }

    public Short getExercicioAtual() {
        return exercicioAtual;
    }

    public void setExercicioAtual(Short exercicioAtual) {
        this.exercicioAtual = exercicioAtual;
    }

    public String getCidadeVeiculo() {
        return cidadeVeiculo;
    }

    public void setCidadeVeiculo(String cidadeVeiculo) {
        this.cidadeVeiculo = cidadeVeiculo;
    }

    public String getUfVeiculo() {
        return ufVeiculo;
    }

    public void setUfVeiculo(String ufVeiculo) {
        this.ufVeiculo = ufVeiculo;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public String getCorPredominante() {
        return corPredominante;
    }

    public void setCorPredominante(String corPredominante) {
        this.corPredominante = corPredominante;
    }

    public BigInteger getQtdeEixos() {
        return qtdeEixos;
    }

    public void setQtdeEixos(BigInteger qtdeEixos) {
        this.qtdeEixos = qtdeEixos;
    }

    public BigDecimal getKmAtual() {
        return kmAtual;
    }

    public void setKmAtual(BigDecimal kmAtual) {
        this.kmAtual = kmAtual;
    }

    public BigDecimal getMediaKmLitro() {
        return mediaKmLitro;
    }

    public void setMediaKmLitro(BigDecimal mediaKmLitro) {
        this.mediaKmLitro = mediaKmLitro;
    }

    public BigDecimal getLarguraVeiculo() {
        return larguraVeiculo;
    }

    public void setLarguraVeiculo(BigDecimal larguraVeiculo) {
        this.larguraVeiculo = larguraVeiculo;
    }

    public BigDecimal getComprimentoVeiculo() {
        return comprimentoVeiculo;
    }

    public void setComprimentoVeiculo(BigDecimal comprimentoVeiculo) {
        this.comprimentoVeiculo = comprimentoVeiculo;
    }

    public BigDecimal getAlturaVeiculo() {
        return alturaVeiculo;
    }

    public void setAlturaVeiculo(BigDecimal alturaVeiculo) {
        this.alturaVeiculo = alturaVeiculo;
    }

    public BigDecimal getPbt() {
        return pbt;
    }

    public void setPbt(BigDecimal pbt) {
        this.pbt = pbt;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public CategoriaVeiculo getIdCategoriaVeiculo() {
        return idCategoriaVeiculo;
    }

    public void setIdCategoriaVeiculo(CategoriaVeiculo idCategoriaVeiculo) {
        this.idCategoriaVeiculo = idCategoriaVeiculo;
    }
    
    @XmlTransient 
    public List<OrdemColeta> getOrdemColetaList() {
        return ordemColetaList;
    }
    
    public void setOrdemColetaList(List<OrdemColeta> ordemColetaList) {
        this.ordemColetaList = ordemColetaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVeiculo != null ? idVeiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Veiculo)) {
            return false;
        }
        Veiculo other = (Veiculo) object;
        if ((this.idVeiculo == null && other.idVeiculo != null) || (this.idVeiculo != null && !this.idVeiculo.equals(other.idVeiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getPlacaVeiculo() + " - " + this.getMarcaVeiculo() + "/" + this.getModeloVeiculo();
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    
}
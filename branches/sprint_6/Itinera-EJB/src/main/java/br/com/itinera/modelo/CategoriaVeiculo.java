package br.com.itinera.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
 * @author LeticiaSena
 */
@Entity
@Table(name = "categoria_veiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaVeiculo.findAll", query = "SELECT c FROM CategoriaVeiculo c order by c.idCategoriaVeiculo DESC"),
    @NamedQuery(name = "CategoriaVeiculo.findByIdCategoriaVeiculo", query = "SELECT c FROM CategoriaVeiculo c WHERE c.idCategoriaVeiculo = :idCategoriaVeiculo"),
    @NamedQuery(name = "CategoriaVeiculo.findByDescricaoCategoria", query = "SELECT c FROM CategoriaVeiculo c WHERE c.descricaoCategoria = :descricaoCategoria")})
public class CategoriaVeiculo implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoriaVeiculo")
    private Collection<Veiculo> veiculoCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    //Anotations referentes à sequence
    @SequenceGenerator(name="Categoria_Veiculo_Generator", sequenceName="seq_categoria_veiculo", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Categoria_Veiculo_Generator")
    //fim anotations Sequence
    @Column(name = "id_categoria_veiculo")
    private BigDecimal idCategoriaVeiculo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descricao_categoria",unique=true)
    private String descricaoCategoria;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "reboque_semireboque")
    private boolean reboqueSemiReboque;
    
    public CategoriaVeiculo() {
    }

    public CategoriaVeiculo(BigDecimal idCategoriaVeiculo) {
        this.idCategoriaVeiculo = idCategoriaVeiculo;
    }

    public CategoriaVeiculo(BigDecimal idCategoriaVeiculo, String descricaoCategoria) {
        this.idCategoriaVeiculo = idCategoriaVeiculo;
        this.descricaoCategoria = descricaoCategoria;
    }

    public BigDecimal getIdCategoriaVeiculo() {
        return idCategoriaVeiculo;
    }

    public void setIdCategoriaVeiculo(BigDecimal idCategoriaVeiculo) {
        this.idCategoriaVeiculo = idCategoriaVeiculo;
    }

    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }
    
        /**
     * @return the reboqueSemiReboque
     */
    public boolean isReboqueSemiReboque() {
        return reboqueSemiReboque;
    }

    /**
     * @param reboqueSemiReboque the reboqueSemiReboque to set
     */
    public void setReboqueSemiReboque(boolean reboqueSemiReboque) {
        this.reboqueSemiReboque = reboqueSemiReboque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaVeiculo != null ? idCategoriaVeiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CategoriaVeiculo)) {
            return false;
        }
        CategoriaVeiculo other = (CategoriaVeiculo) object;
        if ((this.idCategoriaVeiculo == null && other.idCategoriaVeiculo != null) || (this.idCategoriaVeiculo != null && !this.idCategoriaVeiculo.equals(other.idCategoriaVeiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.descricaoCategoria;
    }

    @XmlTransient
    public Collection<Veiculo> getVeiculoCollection() {
        return veiculoCollection;
    }

    public void setVeiculoCollection(Collection<Veiculo> veiculoCollection) {
        this.veiculoCollection = veiculoCollection;
    }
    
}
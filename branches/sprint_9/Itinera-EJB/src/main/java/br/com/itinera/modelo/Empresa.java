
package br.com.itinera.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
 * @author lesena
 */
@Entity
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByIdEmpresa", query = "SELECT e FROM Empresa e WHERE e.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "Empresa.findByCnpj", query = "SELECT e FROM Empresa e WHERE e.cnpj = :cnpj"),
    @NamedQuery(name = "Empresa.findByIe", query = "SELECT e FROM Empresa e WHERE e.ie = :ie"),
    @NamedQuery(name = "Empresa.findByNomeFantasia", query = "SELECT e FROM Empresa e WHERE e.nomeFantasia = :nomeFantasia"),
    @NamedQuery(name = "Empresa.findByRazaoSocial", query = "SELECT e FROM Empresa e WHERE e.razaoSocial = :razaoSocial"),
    @NamedQuery(name = "Empresa.findByTipo", query = "SELECT e FROM Empresa e WHERE e.tipo = :tipo")})
public class Empresa implements Serializable {
    @ManyToMany( cascade = CascadeType.ALL)
    @JoinTable( name = "contato_emp_email", 
                joinColumns = @JoinColumn(name = "empresa_id"),
                inverseJoinColumns = @JoinColumn(name = "email_id")              )
    private List<Email> emailList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private List<EmpresaResponsavel> empresaResponsavelList;
    @ManyToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "contato_emp_tel", 
                joinColumns = @JoinColumn(name = "empresa_id"),
                inverseJoinColumns = @JoinColumn(name = "telefone_id")              )
    private List<Telefone> telefoneList;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    //Anotations referentes à sequence
    @SequenceGenerator(name="Empresa_Generator", sequenceName="seq_empresa", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Empresa_Generator")
    //fim anotations Sequence
    @Column(name = "id_empresa")
    private BigDecimal idEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 19, max = 20)
    @Column(name = "cnpj")
    private String cnpj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ie")
    private String ie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome_fantasia")
    private String nomeFantasia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "razao_social")
    private String razaoSocial;
    @Column(name = "tipo")
    private Character tipo;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(optional = false)
    private Municipio idMunicipio;
    
    @Embedded
    private Endereco endereco;

    public Empresa() {
        endereco = new Endereco();
        idMunicipio = new Municipio();
        this.empresaResponsavelList = new ArrayList<EmpresaResponsavel>();
        this.telefoneList = new ArrayList<Telefone>();
        this.emailList = new ArrayList<Email>();
    }

    public Empresa(BigDecimal idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Empresa(BigDecimal idEmpresa, String cnpj, String ie, String nomeFantasia, String razaoSocial) {
        this.idEmpresa = idEmpresa;
        this.cnpj = cnpj;
        this.ie = ie;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
    }

    public BigDecimal getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(BigDecimal idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }
    
    
    public Municipio getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipio idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nomeFantasia;
    }

    @XmlTransient
    public List<Email> getEmpresaEmailList() {
        return emailList;
    }

    public void setEmpresaEmailList(List<Email> emailList) {
        this.emailList = emailList;
    }

    @XmlTransient
    public List<EmpresaResponsavel> getEmpresaResponsavelList() {
        return empresaResponsavelList;
    }

    public void setEmpresaResponsavelList(List<EmpresaResponsavel> empresaResponsavelList) {
        this.empresaResponsavelList = empresaResponsavelList;
    }

    @XmlTransient
    public List<Telefone> getEmpresaTelefoneList() {
        return telefoneList;
    }

    public void setEmpresaTelefoneList(List<Telefone> telefoneList) {
        this.telefoneList = telefoneList;
    }
    
}

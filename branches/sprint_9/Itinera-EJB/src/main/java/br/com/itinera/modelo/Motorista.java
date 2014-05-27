/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
 * @author lesena
 */
@Entity
@Table(name = "motorista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Motorista.findAll", query = "SELECT m FROM Motorista m"),
    @NamedQuery(name = "Motorista.findByMotoristaId", query = "SELECT m FROM Motorista m WHERE m.motoristaId = :motoristaId"),
    @NamedQuery(name = "Motorista.findByCpf", query = "SELECT m FROM Motorista m WHERE m.cpf = :cpf"),
    @NamedQuery(name = "Motorista.findByNome", query = "SELECT m FROM Motorista m WHERE m.nome = :nome"),
    @NamedQuery(name = "Motorista.findByRg", query = "SELECT m FROM Motorista m WHERE m.rg = :rg"),
    @NamedQuery(name = "Motorista.findByRgOrgaoEmissor", query = "SELECT m FROM Motorista m WHERE m.rgOrgaoEmissor = :rgOrgaoEmissor"),
    @NamedQuery(name = "Motorista.findByRgDataEmissao", query = "SELECT m FROM Motorista m WHERE m.rgDataEmissao = :rgDataEmissao"),
    @NamedQuery(name = "Motorista.findByNomePai", query = "SELECT m FROM Motorista m WHERE m.nomePai = :nomePai"),
    @NamedQuery(name = "Motorista.findByNomeMae", query = "SELECT m FROM Motorista m WHERE m.nomeMae = :nomeMae"),
    @NamedQuery(name = "Motorista.findByEstadoCivil", query = "SELECT m FROM Motorista m WHERE m.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "Motorista.findByDataNascimento", query = "SELECT m FROM Motorista m WHERE m.dataNascimento = :dataNascimento"),
    @NamedQuery(name = "Motorista.findByLocalNascimento", query = "SELECT m FROM Motorista m WHERE m.localNascimento = :localNascimento"),
    @NamedQuery(name = "Motorista.findByBanco", query = "SELECT m FROM Motorista m WHERE m.banco = :banco"),
    @NamedQuery(name = "Motorista.findByAgencia", query = "SELECT m FROM Motorista m WHERE m.agencia = :agencia"),
    @NamedQuery(name = "Motorista.findByConta", query = "SELECT m FROM Motorista m WHERE m.conta = :conta"),
    @NamedQuery(name = "Motorista.findByTipoLogradouro", query = "SELECT m FROM Motorista m WHERE m.tipoLogradouro = :tipoLogradouro"),
    @NamedQuery(name = "Motorista.findByLogradouro", query = "SELECT m FROM Motorista m WHERE m.logradouro = :logradouro"),
    @NamedQuery(name = "Motorista.findByCep", query = "SELECT m FROM Motorista m WHERE m.cep = :cep"),
    @NamedQuery(name = "Motorista.findByNumero", query = "SELECT m FROM Motorista m WHERE m.numero = :numero"),
    @NamedQuery(name = "Motorista.findByComplemento", query = "SELECT m FROM Motorista m WHERE m.complemento = :complemento"),
    @NamedQuery(name = "Motorista.findByBairro", query = "SELECT m FROM Motorista m WHERE m.bairro = :bairro"),
    @NamedQuery(name = "Motorista.findByConjugeNome", query = "SELECT m FROM Motorista m WHERE m.conjugeNome = :conjugeNome"),
    @NamedQuery(name = "Motorista.findByDependentes", query = "SELECT m FROM Motorista m WHERE m.dependentes = :dependentes")})
public class Motorista implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "motorista_id")
    //Anotations referentes à sequence
    @SequenceGenerator(name="Motorista_Generator", sequenceName="seq_motorista", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Motorista_Generator")
    //fim anotations Sequence
    private BigDecimal motoristaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome")
    private String nome;
    @Size(max = 20)
    @Column(name = "rg")
    private String rg;
    @Size(max = 10)
    @Column(name = "rg_orgao_emissor")
    private String rgOrgaoEmissor;
    @Column(name = "rg_data_emissao")
    @Temporal(TemporalType.DATE)
    private Date rgDataEmissao;
    @Size(max = 100)
    @Column(name = "nome_pai")
    private String nomePai;
    @Size(max = 100)
    @Column(name = "nome_mae")
    private String nomeMae;
    @Column(name = "estado_civil")
    private Character estadoCivil;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Size(max = 40)
    @Column(name = "local_nascimento")
    private String localNascimento;
    @Column(name = "banco")
    private Short banco;
    @Column(name = "agencia")
    private Short agencia;
    @Size(max = 20)
    @Column(name = "conta")
    private String conta;
    @Size(max = 20)
    @Column(name = "tipo_logradouro")
    private String tipoLogradouro;
    @Size(max = 100)
    @Column(name = "logradouro")
    private String logradouro;
    @Size(max = 9)
    @Column(name = "cep")
    private String cep;
    @Column(name = "numero")
    private BigInteger numero;
    @Size(max = 50)
    @Column(name = "complemento")
    private String complemento;
    @Size(max = 100)
    @Column(name = "bairro")
    private String bairro;
    @Size(max = 100)
    @Column(name = "conjuge_nome")
    private String conjugeNome;
    @Column(name = "dependentes")
    private BigInteger dependentes;
    @JoinColumn(name = "municipio", referencedColumnName = "id_municipio")
    @ManyToOne
    private Municipio municipio;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "contato_mot_tel", 
                joinColumns = @JoinColumn(name = "motorista_id"),
                inverseJoinColumns = @JoinColumn(name = "telefone_id")              )
    private List<Telefone> telefoneList;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "contato_mot_email", 
                joinColumns = @JoinColumn(name = "motorista_id"),
                inverseJoinColumns = @JoinColumn(name = "email_id")              )
    private List<Email> emailList;

    public Motorista() {
    }

    public Motorista(BigDecimal motoristaId) {
        this.motoristaId = motoristaId;
    }

    public Motorista(BigDecimal motoristaId, String cpf, String nome) {
        this.motoristaId = motoristaId;
        this.cpf = cpf;
        this.nome = nome;
    }

    public BigDecimal getMotoristaId() {
        return motoristaId;
    }

    public void setMotoristaId(BigDecimal motoristaId) {
        this.motoristaId = motoristaId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRgOrgaoEmissor() {
        return rgOrgaoEmissor;
    }

    public void setRgOrgaoEmissor(String rgOrgaoEmissor) {
        this.rgOrgaoEmissor = rgOrgaoEmissor;
    }

    public Date getRgDataEmissao() {
        return rgDataEmissao;
    }

    public void setRgDataEmissao(Date rgDataEmissao) {
        this.rgDataEmissao = rgDataEmissao;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public Character getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Character estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getLocalNascimento() {
        return localNascimento;
    }

    public void setLocalNascimento(String localNascimento) {
        this.localNascimento = localNascimento;
    }

    public Short getBanco() {
        return banco;
    }

    public void setBanco(Short banco) {
        this.banco = banco;
    }

    public Short getAgencia() {
        return agencia;
    }

    public void setAgencia(Short agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public BigInteger getNumero() {
        return numero;
    }

    public void setNumero(BigInteger numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getConjugeNome() {
        return conjugeNome;
    }

    public void setConjugeNome(String conjugeNome) {
        this.conjugeNome = conjugeNome;
    }

    public BigInteger getDependentes() {
        return dependentes;
    }

    public void setDependentes(BigInteger dependentes) {
        this.dependentes = dependentes;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (motoristaId != null ? motoristaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motorista)) {
            return false;
        }
        Motorista other = (Motorista) object;
        if ((this.motoristaId == null && other.motoristaId != null) || (this.motoristaId != null && !this.motoristaId.equals(other.motoristaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.itinera.modelo.Motorista[ motoristaId=" + motoristaId + " ]";
    }

    @XmlTransient
    public List<Email> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<Email> emailList) {
        this.emailList = emailList;
    }

    @XmlTransient
    public List<Telefone> getTelefoneList() {
        return telefoneList;
    }

    public void setTelefoneList(List<Telefone> telefonelList) {
        this.telefoneList = telefonelList;
    }
    
}

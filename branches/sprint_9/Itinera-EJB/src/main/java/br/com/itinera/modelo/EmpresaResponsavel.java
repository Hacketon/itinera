/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.itinera.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lesena
 */
@Entity
@Table(name = "empresa_responsavel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpresaResponsavel.findAll", query = "SELECT e FROM EmpresaResponsavel e"),
    @NamedQuery(name = "EmpresaResponsavel.findByIdEmpresaResponsavel", query = "SELECT e FROM EmpresaResponsavel e WHERE e.idEmpresaResponsavel = :idEmpresaResponsavel"),
    @NamedQuery(name = "EmpresaResponsavel.findByNome", query = "SELECT e FROM EmpresaResponsavel e WHERE e.nome = :nome"),
    @NamedQuery(name = "EmpresaResponsavel.findByCargo", query = "SELECT e FROM EmpresaResponsavel e WHERE e.cargo = :cargo")})
public class EmpresaResponsavel implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name="Empresa_Responsavel_Generator", sequenceName="seq_empresa_responsavel", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Empresa_Responsavel_Generator")
    @Column(name = "id_empresa_responsavel")
    private BigDecimal idEmpresaResponsavel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cargo")
    private String cargo;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne(optional = false)
    private Empresa idEmpresa;

    public EmpresaResponsavel() {
    }

    public EmpresaResponsavel(BigDecimal idEmpresaResponsavel) {
        this.idEmpresaResponsavel = idEmpresaResponsavel;
    }

    public EmpresaResponsavel(BigDecimal idEmpresaResponsavel, String nome, String cargo) {
        this.idEmpresaResponsavel = idEmpresaResponsavel;
        this.nome = nome;
        this.cargo = cargo;
    }

    public BigDecimal getIdEmpresaResponsavel() {
        return idEmpresaResponsavel;
    }

    public void setIdEmpresaResponsavel(BigDecimal idEmpresaResponsavel) {
        this.idEmpresaResponsavel = idEmpresaResponsavel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresaResponsavel != null ? idEmpresaResponsavel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpresaResponsavel)) {
            return false;
        }
        EmpresaResponsavel other = (EmpresaResponsavel) object;
        if ((this.idEmpresaResponsavel == null && other.idEmpresaResponsavel != null) || (this.idEmpresaResponsavel != null && !this.idEmpresaResponsavel.equals(other.idEmpresaResponsavel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Responsavel id:" + idEmpresaResponsavel + "\n" + 
                "Nome: " + nome + "\n" + 
                "Cargo:" + cargo +"\n";
    }
    
}

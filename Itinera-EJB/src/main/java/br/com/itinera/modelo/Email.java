/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
 * @author bruno-note
 */
@Entity
@Table(name = "email")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e"),
    @NamedQuery(name = "Email.findByEmailId", query = "SELECT e FROM Email e WHERE e.emailId = :emailId"),
    @NamedQuery(name = "Email.findByEmailEndereco", query = "SELECT e FROM Email e WHERE e.emailEndereco = :emailEndereco"),
    @NamedQuery(name = "Email.findByEmailDescricao", query = "SELECT e FROM Email e WHERE e.emailDescricao = :emailDescricao")})
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "Email_Generator", schema = "public", sequenceName = "seq_email", allocationSize=1 )
    @GeneratedValue(generator = "Email_Generator", strategy = GenerationType.SEQUENCE )
    @Column(name = "email_id")
    private BigDecimal emailId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email_endereco")
    private String emailEndereco;
    @Size(max = 200)
    @Column(name = "email_descricao")
    private String emailDescricao;

    public Email() {
    }

    public Email(BigDecimal emailId) {
        this.emailId = emailId;
    }

    public Email(BigDecimal emailId, String emailEndereco) {
        this.emailId = emailId;
        this.emailEndereco = emailEndereco;
    }

    public BigDecimal getEmailId() {
        return emailId;
    }

    public void setEmailId(BigDecimal emailId) {
        this.emailId = emailId;
    }

    public String getEmailEndereco() {
        return emailEndereco;
    }

    public void setEmailEndereco(String emailEndereco) {
        this.emailEndereco = emailEndereco;
    }

    public String getEmailDescricao() {
        return emailDescricao;
    }

    public void setEmailDescricao(String emailDescricao) {
        this.emailDescricao = emailDescricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emailId != null ? emailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Email)) {
            return false;
        }
        Email other = (Email) object;
        if ((this.emailId == null && other.emailId != null) || (this.emailId != null && !this.emailId.equals(other.emailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Email[ emailId=" + emailId + " ]";
    }
    
}

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lesena
 */
@Entity
@Table(name = "contato_mot_email")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContatoMotEmail.findAll", query = "SELECT c FROM ContatoMotEmail c"),
    @NamedQuery(name = "ContatoMotEmail.findByContatoMotEmailId", query = "SELECT c FROM ContatoMotEmail c WHERE c.contatoMotEmailId = :contatoMotEmailId")})
public class ContatoMotEmail implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "contato_mot_email_id")
    //Anotations referentes à sequence
    @SequenceGenerator(name="Motorista_Email_Generator", sequenceName="seq_contato_mot_email_id", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Motorista_Email_Generator")
    //fim anotations Sequence
    private BigDecimal contatoMotEmailId;
    @JoinColumn(name = "motorista_id", referencedColumnName = "motorista_id")
    @ManyToOne(optional = false)
    private Motorista motoristaId;
    @JoinColumn(name = "email_id", referencedColumnName = "email_id")
    @ManyToOne(optional = false)
    private Email emailId;

    public ContatoMotEmail() {
    }

    public ContatoMotEmail(BigDecimal contatoMotEmailId) {
        this.contatoMotEmailId = contatoMotEmailId;
    }

    public BigDecimal getContatoMotEmailId() {
        return contatoMotEmailId;
    }

    public void setContatoMotEmailId(BigDecimal contatoMotEmailId) {
        this.contatoMotEmailId = contatoMotEmailId;
    }

    public Motorista getMotoristaId() {
        return motoristaId;
    }

    public void setMotoristaId(Motorista motoristaId) {
        this.motoristaId = motoristaId;
    }

    public Email getEmailId() {
        return emailId;
    }

    public void setEmailId(Email emailId) {
        this.emailId = emailId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contatoMotEmailId != null ? contatoMotEmailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContatoMotEmail)) {
            return false;
        }
        ContatoMotEmail other = (ContatoMotEmail) object;
        if ((this.contatoMotEmailId == null && other.contatoMotEmailId != null) || (this.contatoMotEmailId != null && !this.contatoMotEmailId.equals(other.contatoMotEmailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.itinera.modelo.ContatoMotEmail[ contatoMotEmailId=" + contatoMotEmailId + " ]";
    }
    
}

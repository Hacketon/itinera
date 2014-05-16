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
@Table(name = "contato_mot_tel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContatoMotTel.findAll", query = "SELECT c FROM ContatoMotTel c"),
    @NamedQuery(name = "ContatoMotTel.findByContatoMotTelId", query = "SELECT c FROM ContatoMotTel c WHERE c.contatoMotTelId = :contatoMotTelId")})
public class ContatoMotTel implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "contato_mot_tel_id")
     //Anotations referentes à sequence
    @SequenceGenerator(name="Motorista_Tel_Generator", sequenceName="seq_contato_mot_tel_id", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Motorista_Tel_Generator")
    //fim anotations Sequence
    private BigDecimal contatoMotTelId;
    @JoinColumn(name = "telefone_id", referencedColumnName = "telefone_id")
    @ManyToOne(optional = false)
    private Telefone telefoneId;
    @JoinColumn(name = "motorista_id", referencedColumnName = "motorista_id")
    @ManyToOne(optional = false)
    private Motorista motoristaId;

    public ContatoMotTel() {
    }

    public ContatoMotTel(BigDecimal contatoMotTelId) {
        this.contatoMotTelId = contatoMotTelId;
    }

    public BigDecimal getContatoMotTelId() {
        return contatoMotTelId;
    }

    public void setContatoMotTelId(BigDecimal contatoMotTelId) {
        this.contatoMotTelId = contatoMotTelId;
    }

    public Telefone getTelefoneId() {
        return telefoneId;
    }

    public void setTelefoneId(Telefone telefoneId) {
        this.telefoneId = telefoneId;
    }

    public Motorista getMotoristaId() {
        return motoristaId;
    }

    public void setMotoristaId(Motorista motoristaId) {
        this.motoristaId = motoristaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contatoMotTelId != null ? contatoMotTelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContatoMotTel)) {
            return false;
        }
        ContatoMotTel other = (ContatoMotTel) object;
        if ((this.contatoMotTelId == null && other.contatoMotTelId != null) || (this.contatoMotTelId != null && !this.contatoMotTelId.equals(other.contatoMotTelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.itinera.modelo.ContatoMotTel[ contatoMotTelId=" + contatoMotTelId + " ]";
    }
    
}

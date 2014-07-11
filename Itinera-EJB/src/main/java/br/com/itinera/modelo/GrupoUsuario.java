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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lesena
 */
@Entity
@Table(name = "grupo_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoUsuario.findAll", query = "SELECT g FROM GrupoUsuario g"),
    @NamedQuery(name = "GrupoUsuario.findByGrupoUsuarioId", query = "SELECT g FROM GrupoUsuario g WHERE g.grupoUsuarioId = :grupoUsuarioId")})
public class GrupoUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "grupo_usuario_id")
    private BigDecimal grupoUsuarioId;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuarioId;
    @JoinColumn(name = "grupo_id", referencedColumnName = "grupo_id")
    @ManyToOne(optional = false)
    private Grupo grupoId;

    public GrupoUsuario() {
    }

    public GrupoUsuario(BigDecimal grupoUsuarioId) {
        this.grupoUsuarioId = grupoUsuarioId;
    }

    public BigDecimal getGrupoUsuarioId() {
        return grupoUsuarioId;
    }

    public void setGrupoUsuarioId(BigDecimal grupoUsuarioId) {
        this.grupoUsuarioId = grupoUsuarioId;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Grupo getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Grupo grupoId) {
        this.grupoId = grupoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grupoUsuarioId != null ? grupoUsuarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoUsuario)) {
            return false;
        }
        GrupoUsuario other = (GrupoUsuario) object;
        if ((this.grupoUsuarioId == null && other.grupoUsuarioId != null) || (this.grupoUsuarioId != null && !this.grupoUsuarioId.equals(other.grupoUsuarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.itinera.modelo.GrupoUsuario[ grupoUsuarioId=" + grupoUsuarioId + " ]";
    }
    
}

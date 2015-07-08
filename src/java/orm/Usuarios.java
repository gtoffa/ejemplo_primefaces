/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package orm;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gtoffa
 */
@Entity
@Table(name = "USUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByPkIdUsu", query = "SELECT u FROM Usuarios u WHERE u.pkIdUsu = :pkIdUsu"),
    @NamedQuery(name = "Usuarios.findByUsu", query = "SELECT u FROM Usuarios u WHERE u.usu = :usu"),
    @NamedQuery(name = "Usuarios.findByPass", query = "SELECT u FROM Usuarios u WHERE u.pass = :pass")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_ID_USU")
    private Integer pkIdUsu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USU")
    private String usu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PASS")
    private String pass;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdUsu")
    private Collection<Ingresos> ingresosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdUsu")
    private Collection<Gastos> gastosCollection;

    public Usuarios() {
    }

    public Usuarios(Integer pkIdUsu) {
        this.pkIdUsu = pkIdUsu;
    }

    public Usuarios(Integer pkIdUsu, String usu, String pass) {
        this.pkIdUsu = pkIdUsu;
        this.usu = usu;
        this.pass = pass;
    }

    public Integer getPkIdUsu() {
        return pkIdUsu;
    }

    public void setPkIdUsu(Integer pkIdUsu) {
        this.pkIdUsu = pkIdUsu;
    }

    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @XmlTransient
    public Collection<Ingresos> getIngresosCollection() {
        return ingresosCollection;
    }

    public void setIngresosCollection(Collection<Ingresos> ingresosCollection) {
        this.ingresosCollection = ingresosCollection;
    }

    @XmlTransient
    public Collection<Gastos> getGastosCollection() {
        return gastosCollection;
    }

    public void setGastosCollection(Collection<Gastos> gastosCollection) {
        this.gastosCollection = gastosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdUsu != null ? pkIdUsu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.pkIdUsu == null && other.pkIdUsu != null) || (this.pkIdUsu != null && !this.pkIdUsu.equals(other.pkIdUsu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "orm.Usuarios[ pkIdUsu=" + pkIdUsu + " ]";
    }
    
}

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
@Table(name = "CATEGORIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorias.findAll", query = "SELECT c FROM Categorias c"),
    @NamedQuery(name = "Categorias.findByPkIdCat", query = "SELECT c FROM Categorias c WHERE c.pkIdCat = :pkIdCat"),
    @NamedQuery(name = "Categorias.findByCat", query = "SELECT c FROM Categorias c WHERE c.cat = :cat")})
public class Categorias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_ID_CAT")
    private Integer pkIdCat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CAT")
    private String cat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdCat")
    private Collection<Gastos> gastosCollection;

    public Categorias() {
    }

    public Categorias(Integer pkIdCat) {
        this.pkIdCat = pkIdCat;
    }

    public Categorias(Integer pkIdCat, String cat) {
        this.pkIdCat = pkIdCat;
        this.cat = cat;
    }

    public Integer getPkIdCat() {
        return pkIdCat;
    }

    public void setPkIdCat(Integer pkIdCat) {
        this.pkIdCat = pkIdCat;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
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
        hash += (pkIdCat != null ? pkIdCat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorias)) {
            return false;
        }
        Categorias other = (Categorias) object;
        if ((this.pkIdCat == null && other.pkIdCat != null) || (this.pkIdCat != null && !this.pkIdCat.equals(other.pkIdCat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "orm.Categorias[ pkIdCat=" + pkIdCat + " ]";
    }
    
}

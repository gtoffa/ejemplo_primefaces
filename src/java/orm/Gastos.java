/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package orm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gtoffa
 */
@Entity
@Table(name = "GASTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gastos.findAll", query = "SELECT g FROM Gastos g"),
    @NamedQuery(name = "Gastos.findByPkIdGas", query = "SELECT g FROM Gastos g WHERE g.pkIdGas = :pkIdGas"),
    @NamedQuery(name = "Gastos.findByFec", query = "SELECT g FROM Gastos g WHERE g.fec = :fec"),
    @NamedQuery(name = "Gastos.findByGas", query = "SELECT g FROM Gastos g WHERE g.gas = :gas"),
    @NamedQuery(name = "Gastos.findByVal", query = "SELECT g FROM Gastos g WHERE g.val = :val")})
public class Gastos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_ID_GAS")
    private Integer pkIdGas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FEC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "GAS")
    private String gas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VAL")
    private BigDecimal val;
    @JoinColumn(name = "FK_ID_USU", referencedColumnName = "PK_ID_USU")
    @ManyToOne(optional = false)
    private Usuarios fkIdUsu;
    @JoinColumn(name = "FK_ID_CAT", referencedColumnName = "PK_ID_CAT")
    @ManyToOne(optional = false)
    private Categorias fkIdCat;

    public Gastos() {
    }

    public Gastos(Integer pkIdGas) {
        this.pkIdGas = pkIdGas;
    }

    public Gastos(Integer pkIdGas, Date fec, String gas) {
        this.pkIdGas = pkIdGas;
        this.fec = fec;
        this.gas = gas;
    }

    public Integer getPkIdGas() {
        return pkIdGas;
    }

    public void setPkIdGas(Integer pkIdGas) {
        this.pkIdGas = pkIdGas;
    }

    public Date getFec() {
        return fec;
    }

    public void setFec(Date fec) {
        this.fec = fec;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public BigDecimal getVal() {
        return val;
    }

    public void setVal(BigDecimal val) {
        this.val = val;
    }

    public Usuarios getFkIdUsu() {
        return fkIdUsu;
    }

    public void setFkIdUsu(Usuarios fkIdUsu) {
        this.fkIdUsu = fkIdUsu;
    }

    public Categorias getFkIdCat() {
        return fkIdCat;
    }

    public void setFkIdCat(Categorias fkIdCat) {
        this.fkIdCat = fkIdCat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdGas != null ? pkIdGas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gastos)) {
            return false;
        }
        Gastos other = (Gastos) object;
        if ((this.pkIdGas == null && other.pkIdGas != null) || (this.pkIdGas != null && !this.pkIdGas.equals(other.pkIdGas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "orm.Gastos[ pkIdGas=" + pkIdGas + " ]";
    }
    
}

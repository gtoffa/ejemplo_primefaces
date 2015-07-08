/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import orm.Ingresos;

/**
 *
 * @author gtoffa
 */
@Stateless
public class ingresosDao {

    @PersistenceContext
    EntityManager em;

    public List<Ingresos> select_filtro_(String desc, Date fecdesde, Date fechasta) {
        Query q = em.createQuery("Select i From Ingresos i Where i.ing like :descripcion and (i.fec >= :fecdes AND i.fec <= :fechas) ");
        q.setParameter("descripcion", "%" + desc + "%");
        q.setParameter("fecdes", fecdesde);
        q.setParameter("fechas", fechasta);

        return q.getResultList();
    }
    
    //ABM
    public void insertIngresos(Ingresos in){
        em.persist(in);
    }
    
    public void updateIngresos(Ingresos in){
        em.merge(in);
    }
    
    public void delteIngresos(Ingresos in){
        em.remove(em.merge(in));
        
    }

}

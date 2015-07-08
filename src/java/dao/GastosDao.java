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
import orm.Gastos;

/**
 *
 * @author gtoffa
 */
@Stateless
public class GastosDao {

    @PersistenceContext
    EntityManager em;

    public List<Gastos> selectGastos(){
        Query q = em.createQuery("Select G From Gastos G");
        return q.getResultList();
    }
    
    public List<Gastos> selectGastos(String desc){
        Query q = em.createQuery("Select g From Gastos g Where g.gas like :descripcion");
        q.setParameter("descripcion", "%"+desc+"%");
        return q.getResultList();
    }
    
  
    
    public List<Gastos> selectGastos_filtro(String desc,String cate, Date fecdesde, Date fechasta){
        Query q = em.createQuery("Select g From Gastos g Where g.gas like :descripcion and g.fkIdCat.cat LIKE :cate and (g.fec >= :fecdes AND g.fec <= :fechas) Order By g.pkIdGas Desc");
        q.setParameter("descripcion", "%"+desc+"%");
        q.setParameter("cate", "%"+cate+"%");
        q.setParameter("fecdes", fecdesde);
        q.setParameter("fechas", fechasta);
        return q.getResultList();
    }
    
   
    
 
     
    public Gastos selectGastoporID(int id){
        Query q = em.createQuery("Select g from Gastos g Where g.pkIdGas = :id");
        q.setParameter("id", id);
        return (Gastos)q.getSingleResult();
    } 
    
    
    //ABM
    public void insertGasto(Gastos g){
        em.persist(g);
    }
    
    public void updateGasto(Gastos g){
        em.merge(g);
    }
    
    public void delteGasto(Gastos g){
        em.remove(em.merge(g));
        
    }
}

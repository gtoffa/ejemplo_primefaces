/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import orm.Categorias;

/**
 *
 * @author gtoffa
 */
@Stateless
public class CategoriasDao {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    EntityManager em;
    
    public List<Categorias> selectCategoria(){
        Query q = em.createQuery("Select c From Cateogrias c");
        return q.getResultList();
               
    }
    
    public List<String> selectNombreCategorias(){
        Query q = em.createQuery("Select c.cat From Categorias c");
        return q.getResultList();
    }
    
    
    public Categorias selectCategoriaPorNombre(String nom){
        Query q = em.createQuery("Select c From Categorias c Where c.cat Like :nom");
        q.setParameter("nom", nom);
        return(Categorias) q.getSingleResult();
        
    }
}

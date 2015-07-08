/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import orm.Usuarios;

/**
 *
 * @author gtoffa
 */
@Stateless
public class UsuariosDao {
     @PersistenceContext
    EntityManager em;
     
     
     public Usuarios selectUsuarioPornombre(String nom){
         Query q;
         q = em.createQuery("Select u From Usuarios u where u.usu Like :nom");
         q.setParameter("nom", nom);
         return (Usuarios)q.getSingleResult();
     }
     
     public List<String> selectSoloNombres(){
         Query q ;
         q = em.createQuery("Select u.usu From Usuarios u");
         return q.getResultList();
         
     }
   
}

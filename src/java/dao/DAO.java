/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import logica.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Rodrigo_Rivera
 */

/**
 * Clase WRAPPED para la implementacion de los dao.
 * @author esmeralda
 * @param <E> - Tabla asociada.
 */
public class DAO<E> {

    // atributos de la clase
    private  Session session;
    private String tabla;
    private String id;

    //coonstructor para que abra la session
    private DAO(){
        session = HibernateUtil.getSessionFactory().openSession();
    }
    
    
    /**
     * buscar una tabla un id 
     * @param tabla
     * @param id 
     */
    
    public DAO(String tabla, String id) {
        this();
        this.tabla = tabla;
        this.id = id;
    }
    
    /**
     * regresa una lista de la tabla 
     * @return 
     */
    public List<E> getAll(){
        List<E> l = null;// la lista que va regresar
        String hql; // la consulta 
        Query query; // el objeto para la consulta
        try{
            session.getTransaction().begin(); // abrir la session
            hql = "from " + tabla; //la consuta de la tabla 
            query = session.createQuery(hql);  // l
            if(!query.list().isEmpty()){ // si existe el
                l = (List<E>)query.list();
            }  
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
            throw e;
        }
        return l;
    }
    
    /**
     * busca un elemento por 
     * @param atributos
     * @param valores
     * @return 
     */
    public List<E> buscar(String[] atributos,String[] valores){
        List<E> l = null;
        String hql;
        Query query; 
        try{
            session.getTransaction().begin(); // inicia una session 
             // hace la consulta 
            hql = "from " + tabla + " where " + atributos[0] + " = '" + valores[0] + "'";
            
            // revisa la si los atrutos
            for(int i = 1; i < atributos.length; i++){
                hql +=  " and " + atributos[i] + "= '" + valores[i] + "'";
            }
            query = session.createQuery(hql);
            if(!query.list().isEmpty()){
                l = (List<E>)query.list();
            }  
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
            throw e;
        }
        return l;
    }
    
    /**
     * busca por atributo la tabla 
     * @param atributo
     * @param valor
     * @return 
     */
    public List<E> buscarPorAtributo(String atributo,String valor){
        List<E> list = null;
        String[] atributos,valores;
        atributos = new String[1];
        valores = new String[1];
        atributos[0] = atributo;
        valores[0] = valor;
        try{
            list = this.buscar(atributos, valores);
        }catch(Exception e){
            throw e;
        }
        return list;
    }
    
    /**
     * revisa si un elemntos se encuantra
     * @param atributos
     * @param valores
     * @return 
     */
    public boolean verificarExistencia(String atributos[], String valores[]){ 
        List<E> l = buscar(atributos, valores);
        if(l == null){
            return false;
        }
        return !l.isEmpty();
    }
    
    /**
     * inserta un elemento 
     * @param obj 
     */
    public void insertar (E obj){
        try {
            session.getTransaction().begin();
            session.save(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
    
    /**
     * 
     * @param obj 
     */
    public void actualizar (E obj){
        try {
            session.getTransaction().begin();
            session.update(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
    
    /**
     * elimana un bojeto 
     * @param obj 
     */
    public void borrar (E obj){
        try {
            session.getTransaction().begin();
            session.delete(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
    
    /**
     * obtine un bojeto por el id 
     * @param id
     * @return 
     */
    public E obtenerPorID(int id){
        E obj = null;
        List<E> list = null;
        String[] atributos = new String[1];
        String[] valores = new String[1];
        atributos[0] = this.id;
        valores[0] = "" + id;
        try{
            list = buscar(atributos, valores);
            if(!list.isEmpty()){
                obj = list.get(0);
            }
        }catch(Exception e){
            throw e;
        }
        return obj;
    }
    
}


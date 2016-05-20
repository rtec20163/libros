/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Libro;

/**
 *
 * @author Rodrigo_Rivera
 */
public class daoLibro {
    
      
    DAO dao;
    public daoLibro() {
    
        dao = new DAO("Libro","id_usuario");
    }
    
       public void insertar (Libro obj){
        try {
            dao.insertar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void actualizar (Libro obj){
        try {
            dao.actualizar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void borrar (Libro obj){
        try {
            dao.borrar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Libro obtenerPorID(int id){
        Libro obj;
        try{
            obj = (Libro) dao.obtenerPorID(id);
        }catch(Exception e){
            throw e;
        }
        return obj;
    }
}

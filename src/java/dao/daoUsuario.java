/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Usuario;

/**
 *
 * @author Rodrigo_Rivera
 */
public class daoUsuario {
    
    private DAO<Usuario> dao;

    /**
     * inicializar consttructor con el nombre de la tabla y el id 
     */
    public daoUsuario() {
    
        dao = new DAO("Usuario","id_usuario");
    }
    
       public void insertar (Usuario obj){
        try {
            dao.insertar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void actualizar (Usuario obj){
        try {
            dao.actualizar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void borrar (Usuario obj){
        try {
            dao.borrar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Usuario obtenerPorID(int id){
        Usuario obj;
        try{
            obj = dao.obtenerPorID(id);
        }catch(Exception e){
            throw e;
        }
        return obj;
    }
    
    public boolean existeCorreo(String correo){
        String[] atributos,valores;
        boolean existe;
        atributos = new String[1];
        valores = new String[1];
        atributos[0] = "u_correo";
        valores[0] = correo;
        try{
            existe = dao.verificarExistencia(atributos, valores);
        }catch(Exception e){
            throw e;
        }
        return existe;
    }
    
    /**
     * busca un usuario por su correo y su contraseña 
     * @param obj
     * @return 
     */
    public Usuario verificarUsuario(Usuario obj){
        String[] atributos,valores;
        List<Usuario> list;
        Usuario aux = null;
        atributos = new String[2];
        valores = new String[2];
        atributos[0] = "u_correo";
        valores[0] = obj.getUCorreo();
        atributos[1] = "u_contrasenha";
        valores[1] = obj.getUContrasenha();
        try{
            list = dao.buscar(atributos, valores);
            if(list != null){
                if(!list.isEmpty()){
                    aux = list.get(0);
                }
            }
        }catch(Exception e){
            throw e;
        }
        return aux;
    }
    
}

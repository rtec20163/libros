/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.daoUsuario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.Usuario;

/**
 *
 * @author Rodrigo_Rivera
 */
@ManagedBean
@RequestScoped
public class beanUsuario {
    
    /**
     * tenemos que poner los atributos de la tabla 
     * lo que vallamos a insertar deben estos tener getter y setter 
     * para que en el xhtml podamos usarlos 
     */

    private String nombre;
    private String apellido;
    private String telefono;
    private String contrasenha;
    private String correo;
    private Integer calificaficacion;
    private Integer numeroCalificacion;
    // vamos hacer la conexion de crear el objeto he  insertarlo
    private final daoUsuario daou;
    //varibale de request  y el de mensajes 
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage message;

    /**
     * constructor que debe de inicializar el request 
     * y el dao 
     */
    public beanUsuario() {
        daou = new daoUsuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    /**
     * registra a un profesor  crea el objeto usuario
     * y llena con set los campos y al final con el dao que inicializamos 
     * inserta en la base de datos
     * @return 
     */
    public String regitrar(){
        Usuario u ;
        
        u= new Usuario();
        u.setUNombre(nombre);
        u.setUApellido(apellido);
        u.setUContrasenha(contrasenha);
        
        
        daou.insertar(u);
        
        return "";
    
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getCalificaficacion() {
        return calificaficacion;
    }

    public void setCalificaficacion(Integer calificaficacion) {
        this.calificaficacion = calificaficacion;
    }

    public Integer getNumeroCalificacion() {
        return numeroCalificacion;
    }

    public void setNumeroCalificacion(Integer numeroCalificacion) {
        this.numeroCalificacion = numeroCalificacion;
    }

    public FacesMessage getMessage() {
        return message;
    }

    public void setMessage(FacesMessage message) {
        this.message = message;
    }
    
    
}

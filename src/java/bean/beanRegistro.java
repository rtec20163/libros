/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.daoUsuario;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class beanRegistro {

    /**
     * tenemos que poner los atributos de la tabla lo que vallamos a insertar
     * deben estos tener getter y setter para que en el xhtml podamos usarlos
     */
    private String nombre;
    private String apellido;
    private String telefono;
    private String contrasenha;
    private String contrasenha2;
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
     * constructor que debe de inicializar el request y el dao
     */
    public beanRegistro() {
        daou = new daoUsuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    /**
     * registra a un profesor crea el objeto usuario y llena con set los campos
     * y al final con el dao que inicializamos inserta en la base de datos
     *
     * @return
     */
    public String regitrar() {

        String vCorreo, vPass, vTelefono, vNombre, vApellido;

        /**
         * verifica correoo y pass
         */
//        vCorreo = validarCorreo(this.correo, true);
        vNombre = validarNombre(this.nombre);
        vApellido = validarApellido(this.apellido);
        vPass = validarContrasenha(contrasenha, contrasenha2);
        vTelefono = validarTelefono(telefono);
        vCorreo = validarCorreo(vPass);

        System.out.println("---------------validar nombre:  " + vNombre + " \nvalidar apellido: " + vApellido
                + "\ncontraseña " + vPass + "\ntelefono: " + vTelefono);

////        vPass = validarContrasenha(contrasenha, contrasenha2);
        if (!vNombre.equals("exitoN")) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, vNombre, null);
            faceContext.addMessage(null, message);
        } else if (!vApellido.equals("exitoA")) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, vApellido + "errorApellido", null);
            faceContext.addMessage(null, message);
        } else if (!vTelefono.equals("exitoT")) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, vTelefono, null);
            faceContext.addMessage(null, message);
        } else if (!vPass.equals("exitoP")) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, vPass, null);
            faceContext.addMessage(null, message);
        } else if (!vCorreo.equals("exitoC")) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, vCorreo, null);
            faceContext.addMessage(null, message);
        } else {

            System.out.println("------ paso por todos los filtros");
            Usuario u;

            u = new Usuario();
            u.setUNombre(nombre);
            u.setUApellido(apellido);
            u.setUTelefono(telefono);
            u.setUCorreo(correo);
            u.setUContrasenha(contrasenha);

            daou.insertar(u);
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "registro con exito", null);
            faceContext.addMessage(null, message);
            return  beanPaginas.INDEX;
        }
        return "exito";
    }

    /**
     * verifica si las contrseñas son iguales y tener un tamaño mayor a 6 y
     * menor a 20 no vacias
     *
     * @param c1
     * @param c2
     * @return
     */
    public static String validarContrasenha(String c1, String c2) {

        if (c1 == null || c2 == null) {
            return "Contraseña vacia.";
        } else if (!c1.equals(c2)) {
            return "Las contraseñas ingresadas son distintas.";
        } else if (c1.length() < 6 || c1.length() > 20) {
            return "Las contraseña debe tener entre 6 y 20 caracteres.";
        }

        return "exitoP";
    }

    /**
     * validar el correo que no se vacio existe
     *
     * @param c
     * @param tabla
     * @return
     */
    public static String validarCorreo(String c) {

        daoUsuario daoU = new daoUsuario(); //crea un alumnodao para validar
        String aux;
        if (c == null) { // si es vacaio 
            return "Correo vacio.";
        }

        if (daoU.existeCorreo(c)) {
            return "el correo ya existe";
        }

        return "exitoC";
    }

    /**
     * validar nombre por que no sean letra que no sea vacia
     *
     * @return
     */
    public String validarNombre(String vNombre) {

        if (vNombre.trim().length() <= 3) {
            return "el nombre tiene que ser mayor a a 3 caracteres";
        }

        Pattern exp = Pattern.compile("^[a-zA-Z]+$");

        Matcher mat = exp.matcher(vNombre);

        if (!mat.find()) {
            return "nombre invalido";

        }

        return "exitoN";
    }

    /**
     * verifica el correo que no sea vacio que sean letras
     *
     * @return
     */
    public String validarApellido(String vApellido) {

        if (vApellido.trim().length() <= 3) {
            return "el nombre tiene que ser mayor a a 3 caracteres";
        }

        Pattern exp = Pattern.compile("^[a-zA-Z]+$");

        Matcher mat = exp.matcher(vApellido);

        if (!mat.find()) {
            return "apellido invalido";
        }
        return "exitoA";
    }

    /**
     * verifica si el telfono son numeros y no es vacio
     *
     * @return
     */
    public String validarTelefono(String vTelefono) {

        if (vTelefono.trim().length() <= 6) {
            return "el telefono tiene que ser mayor a a 6 caracteres";
        }

        Pattern exp = Pattern.compile("^[0-9]+$");

        Matcher mat = exp.matcher(vTelefono);

        if (!mat.find()) {
            return "telefono invalido";

        }

        return "exitoT";
    }

    /**
     * metodos get y sett
     *
     * @return
     */
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

    public String getContrasenha2() {
        return contrasenha2;
    }

    public void setContrasenha2(String contrasenha2) {
        this.contrasenha2 = contrasenha2;
    }

}

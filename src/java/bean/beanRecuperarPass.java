/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UsuarioDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import logica.email;
import modelo.Usuario;

/**
 *
 * @author Rodrigo_Rivera
 */
@ManagedBean
@RequestScoped
public class beanRecuperarPass {
    private String correo;

    private final UsuarioDao daou;
    //varibale de request  y el de mensajes 
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage message;

    /**
     * constructor que debe de inicializar el request y el dao
     */
    public beanRecuperarPass() {
        daou = new UsuarioDao();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    public String recuperar() {

        String corre = "1@ciencias.unam.mx";
        Usuario p1, p2;
        p1 = new Usuario();
        p1.setUCorreo(corre);

        p2 = daou.validarCorreo(p1);

        System.out.println("existe = ");
        return p2.getUContrasenha();
    }

    public String recuperarC() {

        Usuario p1, p2;
        p1 = new Usuario();
        p1.setUCorreo(correo);
        String vCorreo;
        vCorreo = validarCorreo(correo);

        if (!vCorreo.equals("exitoC")) {

            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, vCorreo, null);
            faceContext.addMessage(null, message);
            return beanIndex.RECUPERAR_PASS;
        } else {
            String pass;
            p2 = daou.validarCorreo(p1);
            

            System.out.println("enviando correo");
            pass = p2.getUContrasenha();
            System.out.println("la contraseña : "+pass);
            
            email e = new email();
            e.correo(correo, pass);
            System.out.println("correo enviado");

            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "se ha enviado el pass a tu correo", null);
            faceContext.addMessage(null, message);
            return beanIndex.INDEX;
        }
       
    }

    

     public String validarCorreo(String c) {

        UsuarioDao daoU = new UsuarioDao(); //crea un alumnodao para validar
        String aux;
        if (c == null) { // si es vacaio 
            return "Correo vacio.";
        }

        if (daoU.existeCorreo(c)) {
            return "exitoC";
        }

        return "correo no existe";
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}

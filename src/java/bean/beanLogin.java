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
public class beanLogin {

    private final daoUsuario daoU;
    private String correo;
    private String contrasenha;

    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage message;

    public beanLogin() {
        daoU = new daoUsuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }
    
    /**
     * 
     * @return 
     */
    public String login(){
        Usuario a1,a2;
       
        a1 = new Usuario();
        
        a1.setUCorreo(correo);
        a1.setUContrasenha(contrasenha);
        try {
            a2 = daoU.verificarUsuario(a1);
            if(a2 != null){
                httpServletRequest.getSession().setAttribute("id", a2.getIdUsuario());
                httpServletRequest.getSession().setAttribute("sesionNombre", a2.getUNombre());
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Otorgado.", null);
                faceContext.addMessage(null, message);
                return beanPaginas.HOME;
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrecto.", null);
                faceContext.addMessage(null, message);
            }            
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getLocalizedMessage(), null);
            faceContext.addMessage(null, message);
        }
        
       return beanPaginas.INDEX; // si hay fallo
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

}

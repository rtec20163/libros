/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.LibroDao;
import dao.UsuarioDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Libro;
import modelo.Usuario;

/**
 *
 * @author Rodrigo_Rivera
 */
@ManagedBean
@RequestScoped
public class libro {

    private String titulo;
    private String autor;
    private final int id;
    private final UsuarioDao daoU;
    private final LibroDao daoL;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage message;

    public libro() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        daoU = new UsuarioDao();
        daoL = new LibroDao();
        id = (int) httpServletRequest.getSession().getAttribute("id");
    }

    public void publicar(){
        
        Libro libro;
        Usuario usuario;
        
    
            System.out.println("");
            usuario = daoU.obtenerPorID(id);
            System.out.println("construyendo libro con el usuario: "+usuario.getUNombre());
            libro = new Libro();
            libro.setLAutor(autor);
            System.out.println("el autor "+autor);
            libro.setLTitulo(titulo);
            System.out.println("con el titulo: "+titulo);
            System.out.println("con el usuario: "+usuario.getUCorreo());
            libro.setUsuario(usuario);
            System.out.println(" apunto de guardar ");
            System.out.println("el libroe es vacio : "+libro.getLAutor());
            System.out.println("titulo:  "+libro.getLTitulo());
            System.out.println("nombre user:  "+libro.getUsuario().getUCorreo());
           
            daoL.insertar(libro);
            System.out.println("guardado");
            
            
       
    
    }
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    
    

}

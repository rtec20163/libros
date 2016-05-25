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
public class beanLibro {

    private int idLibro;
    private String LTitulo;
    private String LAutor;
    private String LEditorial;
    private String LIsbn;
    private String LAnho;
    private Integer NEdicion;
    private Integer LEvalucionContenido;
    private Integer LEvaluacionRedaccion;
    private String LResehna;
    private String LPablasClave;
    private String LFoto;
    private String LMapaUbicacion;
    private Boolean LOferta;
    private final int id;
    private final UsuarioDao daoU;
    private final LibroDao daoL;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage message;

    public beanLibro() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        daoU = new UsuarioDao();
        daoL = new LibroDao();
        id = (int) httpServletRequest.getSession().getAttribute("id");
    }

    public String guardarLibro() {
        Usuario u;
        Libro libro;

        System.out.println("el id : " + id);
        try {
            System.out.println("el usuaio antes : ");

            u = daoU.obtenerPorID(id);
            System.out.println("el usuaio: " + u);

            libro = new Libro();
            libro.setLTitulo(LTitulo);
//            libro.setLAutor(LAutor);
//            libro.setLEditorial(LEditorial);
            System.out.println("pasa por autor");
//            libro.setLIsbn(LIsbn);
//            libro.setLAnho(LAnho);
//            libro.setNEdicion(NEdicion);
//            libro.setLEvalucionContenido(LEvalucionContenido);
//            libro.setLEvaluacionRedaccion(LEvaluacionRedaccion);
//            libro.setLResehna(LResehna);
//            libro.setLPablasClave(LPablasClave);
//            libro.setLFoto(LFoto);
//            libro.setLMapaUbicacion(LMapaUbicacion);
            libro.setUsuario(u);
            System.out.println("pasa");
            daoL.insertar(libro);
            System.out.println("libro publicado");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actividad publicada exitosamente.", null);
            faceContext.addMessage(null, message);
            return beanIndex.INICIO;

        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error no pudiste publicar.", null);
            faceContext.addMessage(null, message);
            return beanIndex.PUBLICAR_LIBRO; //si no se pudo hacer la publicacion "cambiar"
        }

    }

    public String editarLibro() {
        return "";
    }

    public String eliminarLibro() {
        Libro libro;

        try {
            libro = daoL.obtenerPorID(idLibro);
            daoL.borrar(libro);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "libro elminado correctamente", null);
            faceContext.addMessage(null, message);
            return beanIndex.INICIO;
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getLocalizedMessage(), null);
            faceContext.addMessage(null, message);
            return beanIndex.NUEVA_PUBLICACION;
        }

    }
    
    public String verPublicaciones(){
    return "";
    }

    public String getLTitulo() {
        return LTitulo;
    }

    public void setLTitulo(String LTitulo) {
        this.LTitulo = LTitulo;
    }

    public String getLAutor() {
        return LAutor;
    }

    public void setLAutor(String LAutor) {
        this.LAutor = LAutor;
    }

    public String getLEditorial() {
        return LEditorial;
    }

    public void setLEditorial(String LEditorial) {
        this.LEditorial = LEditorial;
    }

    public String getLIsbn() {
        return LIsbn;
    }

    public void setLIsbn(String LIsbn) {
        this.LIsbn = LIsbn;
    }

    public String getLAnho() {
        return LAnho;
    }

    public void setLAnho(String LAnho) {
        this.LAnho = LAnho;
    }

    public Integer getNEdicion() {
        return NEdicion;
    }

    public void setNEdicion(Integer NEdicion) {
        this.NEdicion = NEdicion;
    }

    public Integer getLEvalucionContenido() {
        return LEvalucionContenido;
    }

    public void setLEvalucionContenido(Integer LEvalucionContenido) {
        this.LEvalucionContenido = LEvalucionContenido;
    }

    public Integer getLEvaluacionRedaccion() {
        return LEvaluacionRedaccion;
    }

    public void setLEvaluacionRedaccion(Integer LEvaluacionRedaccion) {
        this.LEvaluacionRedaccion = LEvaluacionRedaccion;
    }

    public String getLResehna() {
        return LResehna;
    }

    public void setLResehna(String LResehna) {
        this.LResehna = LResehna;
    }

    public String getLPablasClave() {
        return LPablasClave;
    }

    public void setLPablasClave(String LPablasClave) {
        this.LPablasClave = LPablasClave;
    }

    public String getLFoto() {
        return LFoto;
    }

    public void setLFoto(String LFoto) {
        this.LFoto = LFoto;
    }

    public String getLMapaUbicacion() {
        return LMapaUbicacion;
    }

    public void setLMapaUbicacion(String LMapaUbicacion) {
        this.LMapaUbicacion = LMapaUbicacion;
    }

    public Boolean getLOferta() {
        return LOferta;
    }

    public void setLOferta(Boolean LOferta) {
        this.LOferta = LOferta;
    }

}

package bean;

import dao.LibroDao;
import dao.UsuarioDao;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.DraggableMarkersView;
import modelo.Libro;
import modelo.Usuario;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 *
 */
@ManagedBean
@RequestScoped
public class beanPublicacion {

    private final String EDICION_INVALIDA = "No se ha definido la edición.";
    private final String EVALCONT_INVALIDA = "No se ha definido la evaluación del contenido.";
    private final String EVALREDAC_INVALIDA = "No se ha definido la evaluación de la redacción.";

    private final int id;
    private Libro libro;
    private String titulo;
    private String autor;
    private String editorial;
    private String isbn;
    private String anho;
    private int edicion;
    private String sEdicion;
    private int evalCont;
    private String sEvalCont;
    private int evalRedac;
    private String sEvalRedac;
    private String resenha;
    private String palabrasClaves;
    private Double latitud;
    private Double longitud;
    private final LibroDao dao;
    private final UsuarioDao daoP;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage message;

    private DraggableMarkersView draggableMarkersView;

    public beanPublicacion() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        dao = new LibroDao();
        daoP = new UsuarioDao();
        sEdicion = EDICION_INVALIDA;
        sEvalCont = EVALCONT_INVALIDA;
        sEvalRedac = EVALREDAC_INVALIDA;
        id = (int) httpServletRequest.getSession().getAttribute("id");
        definirActividad();
    }

    private void definirActividad() {
        int idA;
        try {
            if (libro == null) {
                idA = (int) httpServletRequest.getSession().getAttribute("sesionActividad");
                libro = dao.obtenerPorID(idA);
                titulo = libro.getLTitulo();
                autor = libro.getLAutor();
                editorial = libro.getLEditorial();
                isbn = libro.getLIsbn();
                anho = libro.getLAnho();
                sEdicion = "" + edicion;
                sEvalCont = "" + evalCont;
                sEvalRedac = "" + evalRedac;
                resenha = libro.getLResehna();
                palabrasClaves = libro.getLPablasClave();
                latitud = libro.getLLatitud();
                longitud = libro.getLLongitud();
            }
        } catch (Exception e) {

        }
    }

    public String guardarPublicacion() {
        String error;
        Usuario usuario;
        error = checkCampos();
        try {
            if (error.equals("")) {

                usuario = daoP.obtenerPorID(id);
                libro = new Libro();
                libro.setLTitulo(titulo);
                libro.setLAutor(autor);
                libro.setLEditorial(editorial);
                libro.setLIsbn(isbn);
                libro.setLAnho(anho);
                libro.setNEdicion(edicion);
                libro.setLEvalucionContenido(evalCont);
                libro.setLEvaluacionRedaccion(evalRedac);
                libro.setLResehna(resenha);
                libro.setLPablasClave(palabrasClaves);
                libro.setLLatitud(latitud);
                libro.setLLongitud(longitud);
                libro.setUsuario(usuario);
                dao.insertar(libro);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Libro publicado exitosamente.", null);
                faceContext.addMessage(null, message);
                faceContext.getExternalContext().getFlash().setKeepMessages(true);
                return beanIndex.INICIO();
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, null);
                faceContext.addMessage(null, message);
                faceContext.getExternalContext().getFlash().setKeepMessages(true);
                return beanIndex.PUBLICAR();
            }
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getLocalizedMessage(), null);
            faceContext.addMessage(null, message);
            faceContext.getExternalContext().getFlash().setKeepMessages(true);
            return beanIndex.PUBLICAR();
        }
    }

    public List<Libro> mostrarMisPublicaciones(int id) {
        List<Libro> resultado;
        try {
            resultado = dao.obtenerPorUsuario(id);
        } catch (Exception e) {
            resultado = new LinkedList<>();
        }
        return resultado;
    }

    
    public MapModel mostrarMarcadores(int id) {
        MapModel marcadores = new DefaultMapModel();
        List<Libro> resultado;
        try {
            resultado = dao.obtenerPorUsuario(id);
            for(Libro librotemp : resultado){
                marcadores.addOverlay(new Marker(new LatLng(librotemp.getLLatitud(), librotemp.getLLongitud()), librotemp.getLTitulo()));
            }
        } catch (Exception e) {
            resultado = new LinkedList<>();
        }
        return marcadores;
    }

    
    public List<Libro> mostrarPublicaciones() {
        List<Libro> resultado;
        try {
            resultado = dao.obtenerLista();
        } catch (Exception e) {
            resultado = new LinkedList<>();
        }
        return resultado;
    }

    private String checkCampos() {
        try {
            evalCont = Integer.parseInt(sEvalCont);
            evalRedac = Integer.parseInt(sEvalRedac);
            edicion = Integer.parseInt(sEdicion);
            if (evalCont < 1 || evalCont > 10) {
                return "Evaluación de contenido no valida.(Debe ser entre 1 y  10)";
            }
            if (evalRedac < 1 || evalRedac > 10) {
                return "Evaluación de redacción no valida.(Debe ser entre 1 y  10)";
            }
            if (edicion < 1) {
                return "Edición no valida.";
            }
            if (titulo.isEmpty()) {
                return "Titulo vacio.";
            }
            if (autor.isEmpty()) {
                return "Autor vacio.";
            }
            if (isbn.isEmpty()) {
                return "Isbn vacio.";
            }
            if (editorial.isEmpty()) {
                return "Editorial vacia.";
            }
            if (anho.isEmpty()) {
                return "Año vacio.";
            }
            if (resenha.isEmpty()) {
                return "Reseña vacia.";
            }
            if (palabrasClaves.isEmpty()) {
                return "Palabras Claves vacias.";
            }
        } catch (Exception e) {

        }
        return "";
    }

    public Libro getActividad() {
        return libro;
    }

    public void setActividad(Libro libro) {
        this.libro = libro;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public String getsEdicion() {
        return sEdicion;
    }

    public void setsEdicion(String sEdicion) {
        this.sEdicion = sEdicion;
    }

    public int getEvalCont() {
        return evalCont;
    }

    public void setEvalCont(int evalCont) {
        this.evalCont = evalCont;
    }

    public String getsEvalCont() {
        return sEvalCont;
    }

    public void setsEvalCont(String sEvalCont) {
        this.sEvalCont = sEvalCont;
    }

    public int getEvalRedac() {
        return evalRedac;
    }

    public void setEvalRedac(int evalRedac) {
        this.evalRedac = evalRedac;
    }

    public String getsEvalRedac() {
        return sEvalRedac;
    }

    public void setsEvalRedac(String sEvalRedac) {
        this.sEvalRedac = sEvalRedac;
    }

    public String getResenha() {
        return resenha;
    }

    public void setResenha(String reseña) {
        this.resenha = reseña;
    }

    public String getPalabrasClaves() {
        return palabrasClaves;
    }

    public void setPalabrasClaves(String palabrasClaves) {
        this.palabrasClaves = palabrasClaves;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public void dameLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public FacesMessage getMessage() {
        return message;
    }

    public void setMessage(FacesMessage message) {
        this.message = message;
    }

    public DraggableMarkersView getDraggableMarkersView() {
        return draggableMarkersView;
    }

    public void setDraggableMarkersView(DraggableMarkersView draggableMarkersView) {
        this.draggableMarkersView = draggableMarkersView;
    }

}

package bean;

import dao.UsuarioDao;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Usuario;

/**
 *
 * @author esmeralda
 */
@ManagedBean
@RequestScoped
public class beanRegistro {

    private String nombre;
    private String apellido;
    private String contrasenha;
    private String contrasenha2;
    private String correo;
    private String telefono;
    private final UsuarioDao daoA;

    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage message;

    public beanRegistro() {
        daoA = new UsuarioDao();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

//    
    public String registrarUsuario() {
        Usuario a;
        String errorCont, errorNombre, errorCorreo, errorApellido, errorTelefono;
        errorCont = validarContrasenha(contrasenha, contrasenha2);
        errorNombre = validarNombre(nombre);
        errorApellido = validarNombre(apellido);
        //errorCorreo = validarCorreo(correo, true);
        errorTelefono = validarTelefono(telefono);
        if (!errorCont.equals("")) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorCont, null);
            faceContext.addMessage(null, message);
        } else if (!errorNombre.equals("")) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorNombre, null);
            faceContext.addMessage(null, message);
        } else if (!errorApellido.equals("")) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorApellido, null);
            faceContext.addMessage(null, message);
        } else if (!errorTelefono.equals("")) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorTelefono, null);
            faceContext.addMessage(null, message);
        } else /*if(!errorCorreo.equals("")){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorCorreo, null);
            faceContext.addMessage(null, message);
        }else
         */ {
            a = new Usuario();
            a.setUContrasenha(contrasenha);
            a.setUNombre(nombre);
            a.setUApellido(apellido);
            a.setUCorreo(correo);
            a.setUTelefono(telefono);
            try {
                System.out.println(a.getIdUsuario());
                daoA.insertar(a);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuenta creda correctamente.", null);
                faceContext.addMessage(null, message);
                return beanIndex.INDEX;
            } catch (Exception e) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getLocalizedMessage(), null);
                faceContext.addMessage(null, message);
            }
        }
        return beanIndex.REGISTRAR;
    }

    public static String validarTelefono(String telefono) {
        if (telefono.length() != 10) {
            return "Longitud del teléfono incorrecta";
        }
        return "";
    }

    public static String validarContrasenha(String c1, String c2) {
        if (c1 == null || c2 == null) {
            return "Contraseña vacia.";
        } else if (!c1.equals(c2)) {
            return "Las contraseñas ingresadas son distintas.";
        } else if (c1.length() < 6 || c1.length() > 20) {
            return "Las contraseña debe tener entre 6 y 20 caracteres.";
        }

        return "";
    }

    public static String validarNombre(String n) {

        if (n.trim().length() <= 3 || n.trim().length() > 20) {
            return "el nombre tiene que ser mayor a a 3 caracteres";
        }

        Pattern exp = Pattern.compile("^[a-zA-Z]+$");

        Matcher mat = exp.matcher(n);

        if (!mat.find()) {
            return "nombre invalido";

        }

       
        return "";
    }

//    public static String validarCorreo(String c, boolean tabla) {
//        UsuarioDao daoA;
//        String aux;
//        if (c == null) {
//            return "Correo vacio.";
//        }
//        if (!c.endsWith(DOMINIO)) {
//            return "El correo debe de ser del dominio de @ciencias.unam.mx";
//        }
//        aux = c.substring(0, c.length() - DOMINIO.length());
//        aux = borrar(aux, LETRAS);
//        aux = borrar(aux, NUMEROS);
//        if (!aux.equals("")) {
//            return "El correo contiene caracteres invalidos.";
//        }
//        if (tabla) {
//            daoA = new UsuarioDao();
//            if (daoA.existeCorreo(c)) {
//                return "El correo ya existe en la base de datos.";
//            }
//        }
//        return "";
//    }

    public static String borrar(String borrada, String b) {
        String aux = new String(borrada);
        String sub;
        for (int i = 0; i < b.length(); i++) {
            sub = b.substring(i, i + 1);
            aux = aux.replace(sub, "");
        }
        return aux;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getContrasenha2() {
        return contrasenha2;
    }

    public void setContrasenha2(String contrasenha2) {
        this.contrasenha2 = contrasenha2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
}

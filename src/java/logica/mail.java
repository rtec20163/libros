/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.Usuario;

/**
 *
 * @author luis
 */
public class mail {
    public void correoPass(String correo  , String contra) {

        System.out.println("Antes de Enviar");
        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "soporte.prestamo.libros.riesgo@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correo));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(correo));
            message.setSubject("[Préstamo de Libros]Recuperación de contraseña");
            message.setText("Tu contraseña es:\n "+ contra +"\n Atentamente Soporte de Préstamo de Libros");

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            //soporte.prestamo.libros.riesgo@gmail.com
            //riesgo12345
            t.connect("soporte.prestamo.libros.riesgo@gmail.com", "riesgo12345");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Enviado");

    }
    private void correoInforme(String correo , Usuario usuario) {

        System.out.println("Antes de Enviar");
        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "soporte.prestamo.libros.riesgo@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correo));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(correo));
            message.setSubject("[Préstamo de Libros]Informacion de usuario");
            message.setText("informacion de el usuario : "+usuario.getUNombre()+" "+usuario.getUApellido()+"\n"
                    + "su telefono: "+usuario.getUTelefono()+"\n"
                    + "y su correo: "+usuario.getUCorreo()+"\n"
                    + "ponerse de acuerdo para el intercambio de libro");
           

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            //soporte.prestamo.libros.riesgo@gmail.com
            //riesgo12345
            t.connect("soporte.prestamo.libros.riesgo@gmail.com", "riesgo12345");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Enviado");

    }
     public void correoInformar(Usuario u1, Usuario u2) {
         String correo1 = u1.getUCorreo();
         String correo2 = u2.getUCorreo();
         
         correoInforme(correo1, u2);
         correoInforme(correo2, u1);
          

    }
}

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

/**
 *
 * @author Rodrigo_Rivera
 */
public class email {
    public void correo(String correo  , String pass) {

        System.out.println("antes de enviar");
        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "soporte.libros.rt@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correo));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(correo));
            message.setSubject("[soporte]contraseña");
            message.setText("tu contraseña es: "+pass);

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            //soporte.libros.rt@gmail.com
            //riesgotec
            t.connect("soporte.libros.rt@gmail.com", "riesgotec");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("enviado");

    }
}

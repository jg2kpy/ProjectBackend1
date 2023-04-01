package py.com.progweb.prueba.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUitls {
    private static Session session;

    public static Session getSession() {
        if (session == null) {
            String user = System.getenv("user");
            String pass = System.getenv("pass");

            System.out.println(user);
            System.out.println(pass);

            // Create a static session object that can be reused across requests
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host", "smtp.office365.com");
            properties.setProperty("mail.smtp.port", "587");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.auth", "true");


            session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);
                }
            });
        }
        return session;
    }

    public static void enviarCorreo(String from, String to, String subject, String body){
        Session session = getSession();
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

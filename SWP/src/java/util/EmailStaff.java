/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Date;
import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.activation.DataContentHandler;
import jakarta.activation.DataSource;

/**
 *
 * @author Gigabyte
 */
public class EmailStaff {

    //Email: thangvd189003@gmail.com
    //Password: xhhv txku vhba hqon
    static final String from = "thangvd189003@gmail.com";
    static final String password = "xhhv txku vhba hqon";

    public boolean sendEmail(String to, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);


        try {

            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            msg.setFrom(from);

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            msg.setSubject("Password to login");

            msg.setContent("Your password to login Novena page: " + content, "text/html");

            Transport.send(msg);
             System.out.println("Email sent successfully");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error message: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        EmailStaff e = new EmailStaff();

        System.out.println(e.sendEmail("thangvu230304@gmail.com", "l56PBUNR"));
    }
}

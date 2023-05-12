package kill.me.palas.config;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    public MailSender(){};
    public void Send (String to, String course, String name){
        String from = "killmepalas@gmail.com";
        String host = "smtp.gmail.com";
        String smtpPort = "465";

        Properties properties = new Properties();
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port",smtpPort);
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session = Session.getInstance(
                properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,"nqloekcucvegvmgs");
                    }
                }
        );

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Поздравляем с окончанием курса!");
            message.setText("Команда Learnability от всей души поздравляет Вас с окончанием учебного курса '" + course + "', " + name + "! Сертификат будет торжественно вручён Вам в скором времени.");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package my.project.remember.me.remember_me.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import my.project.remember.me.remember_me.Model.Evento;

@Service
public class EmailService {

    @Autowired
    JavaMailSender mailSender;

    @Value("${app.domain}")
    private String appDomain;

    public void sendAlertEmail(Evento evento) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(evento.getUserEmail());
            helper.setSubject("Promemoria: " + evento.getTitle());
            String link = appDomain + "/event/updateStatus/" + evento.getToken();

            String html = String.format(
                    """
                            <html>
                                <body>
                                    <h1>%s</h1>
                                    <h3>Questo è un promemoria per ricordardarti di:</h3>
                                    <p>%s</p>
                                    <p>Clicca <a href="%s">qui</a> per segnare l'evento come completato.</p>
                                </body>
                            </html>

                    """, evento.getTitle(), evento.getDescription(),link);
            helper.setText(html, true);
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

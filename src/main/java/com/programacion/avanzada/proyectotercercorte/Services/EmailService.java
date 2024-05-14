package com.programacion.avanzada.proyectotercercorte.Services;

import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.resend.Resend;


@Service
public class EmailService {

    @Value("${resend.api-key}")
    private String apiKey;

    public void sendEmail(String to, String subject, String body) {
        Resend resend = new Resend(apiKey);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("johann.rojasu@utadeo.edu.co")
                .to(to)
                .subject(subject)
                .html(body)
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
            System.out.println("Email sent successfully: " + data.getId());
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailWithAttachment(String to, String subject, String body, String attachmentUrl) {
        Resend resend = new Resend(apiKey);

        String htmlBody = body + "<br><br><a href=\"" + attachmentUrl + "\">Descargar desprendible de pago</a>";

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("johann.rojasu@utadeo.edu.co")
                .to(to)
                .subject(subject)
                .html(htmlBody)
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
            System.out.println("Email sent successfully: " + data.getId());
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }
}
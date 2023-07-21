package com.a2r.immobilierdz.email;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;


@Service
@RequiredArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailService.class);


    private final JavaMailSender javaMailSender;

    @Override
    @Async
    public void send(String to, String email) {

        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage,
              "utf-8");

              mimeMessageHelper.setText(email, true);
              mimeMessageHelper.setTo(to);
              mimeMessageHelper.setSubject("Confirmez votre e-mail");
              mimeMessageHelper.setFrom("ImmobilierDZ@gmail.com");
              javaMailSender.send(mimeMessage);
        } catch (javax.mail.MessagingException e) {
            LOGGER.error("failed to send email" , e);
            throw new IllegalStateException("failed to send mail");
        }

    }
}

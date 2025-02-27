package com.ga5000.api.ecommerce.service.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService{

    @Value("${spring.mail.username}")
    private String senderEmail;

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendRecoverPasswordEmail(String email, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(email);
        message.setSubject("Recover Password");
        message.setText(text);
        sendEmail(message);

    }

    private void sendEmail(SimpleMailMessage message){
        javaMailSender.send(message);
    }
}

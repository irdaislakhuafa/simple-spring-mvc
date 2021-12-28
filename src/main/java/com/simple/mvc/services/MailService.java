package com.simple.mvc.services;

import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    private static final String myEmail = "irdhaislakhuafa@gmail.com";

    public Boolean sendEmail(String to, String subject, String text) {
        SimpleMailMessage eMailMessage = new SimpleMailMessage();
        Date date = new Date();
        Boolean sendStatus = false;
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            eMailMessage.setFrom(myEmail);
            eMailMessage.setTo(to);
            eMailMessage.setSubject(subject);
            eMailMessage.setSentDate(date);
            // eMailMessage.setText(text);

            helper = new MimeMessageHelper(message, true);
            helper.setFrom(myEmail);
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(message);
            sendStatus = true;
        } catch (Exception e) {
            e.printStackTrace();
            sendStatus = false;
        }
        return sendStatus;
    }
}

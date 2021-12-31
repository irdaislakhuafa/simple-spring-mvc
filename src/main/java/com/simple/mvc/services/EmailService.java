package com.simple.mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Async
    public Boolean sendEmail(String from, String to, String subject, String text) {
        Boolean sendStatus = false;
        try {
            // new Thread(() -> {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage() {
                {
                    setFrom(from);
                    setTo(to);
                    setSubject(subject);
                    setText(text);
                }
            };
            System.err.println(simpleMailMessage);
            mailSender.send(simpleMailMessage);
            // }).start();
            sendStatus = true;
        } catch (Exception e) {
            e.printStackTrace();
            sendStatus = false;
        }
        return sendStatus;
    }
}

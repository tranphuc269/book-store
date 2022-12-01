package com.bookstore.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendGmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("phuc260900@gmail.com");
        message.setTo("meliodasss2000@gmail.com");
        message.setText("This is body");
        message.setSubject("subject");
        mailSender.send(message);
        System.out.println("Mail Send...");
    }

    @Override
    public void sendNotificationToApp() {

    }
}
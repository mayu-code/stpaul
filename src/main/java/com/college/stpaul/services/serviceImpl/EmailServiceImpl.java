package com.college.stpaul.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import com.college.stpaul.services.serviceInterface.EmailService;

import jakarta.mail.internet.MimeMessage;

public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Async("taskExecutor")
    @Transactional
    public void sendSimpleEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    @Override
    @Async("taskExecutor")
    @Transactional
    public void sendHtmlEmail(String to, String subject, String body) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message ,true);
            helper.setTo(to);
            helper.setSubject(subject);
            message.setContent(body,"text/html");
            mailSender.send(message);

        }catch(Exception e){
            return;
        }
    }
    
}

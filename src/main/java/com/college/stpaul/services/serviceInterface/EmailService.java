package com.college.stpaul.services.serviceInterface;

public interface EmailService {
    void sendSimpleEmail(String to,String subject,String body);
    void sendHtmlEmail(String to,String subject,String body);
}

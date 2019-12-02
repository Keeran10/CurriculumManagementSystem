package com.soen490.cms.Services;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Log4j2
@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendPackageByMail (int packageId){
        sendEmail();
        return true;
    }

    void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
       // msg.setTo("to_1@gmail.com", "to_2@gmail.com", "to_3@yahoo.com");
        msg.setTo("georgebarsem@gmail.com");
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);
        System.out.println("EMAIL SENT");

    }
}

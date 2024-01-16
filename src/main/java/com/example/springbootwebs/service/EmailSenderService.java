package com.example.springbootwebs.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSenderService {
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    EntityManager entityManager;

    public void sendMail(String toEmail,String subject,String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kameshkabil9@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

        System.out.println("Mail Sent successfully...");
    }


    @Scheduled(cron = "0 * 17 * * ?")
    public void cronJobschedule(){
        System.out.println("cron job!");
        String getAllEmailToDB = "SELECT u.email FROM User u";
        List<String> emailList = entityManager.createQuery(getAllEmailToDB,String.class)
                .getResultList();

        for (String email : emailList){
            sendMail(email,"K@KMedia🎶📻🎶",
                    "Watch Jallikattu 🐃🐃🐃 Live On 🔊 K@KMedia 📻");
        }
    }
}

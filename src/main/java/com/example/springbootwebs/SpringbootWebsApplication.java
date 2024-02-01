package com.example.springbootwebs;

import com.example.springbootwebs.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class SpringbootWebsApplication {

//	@Autowired
//	EmailSenderService emailSenderService;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebsApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void sendMail() {
//		emailSenderService.sendMail("kameshkabil9@gmail.com",
//				"KKMedia🎶📻🎶",
//				"Watch Jallikattu 🐃🐃🐃 Live On 🔊 KKMedia 📻");
//	}
}

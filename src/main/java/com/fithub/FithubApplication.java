package com.fithub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fithub.util.JavaMail;

@SpringBootApplication
public class FithubApplication {

	public static void main(String[] args) {
		SpringApplication.run(FithubApplication.class, args);
		
		JavaMail mail = new JavaMail();
		mail.SendMail();
	}

}

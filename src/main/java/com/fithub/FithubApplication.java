package com.fithub;

import java.io.UnsupportedEncodingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fithub.util.JavaMail;

@SpringBootApplication
public class FithubApplication {

	public static void main(String[] args) throws UnsupportedEncodingException {
		SpringApplication.run(FithubApplication.class, args);
		
	}

}

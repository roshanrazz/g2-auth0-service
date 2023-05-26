package com.pms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.pms.service.impl.AuthServiceImpl;

@SpringBootApplication
@EnableAspectJAutoProxy 
@CrossOrigin(origins="*")
public class Auth0Application implements CommandLineRunner{
	
	@Autowired
	AuthServiceImpl authService;

	public static void main(String[] args) throws UnirestException {
		SpringApplication.run(Auth0Application.class, args);		
	
	}
	
	@Override
    public void run(String... args) throws Exception {
        System.out.println("In CommandLineRunnerImpl ");       
        authService.addUsersInfo();

    }

}

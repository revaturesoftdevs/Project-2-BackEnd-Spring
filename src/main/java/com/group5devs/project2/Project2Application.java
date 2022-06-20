package com.group5devs.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.group5devs.project2.service.ManagerService;
import com.group5devs.project2.service.ManagerServiceImpl;

@SpringBootApplication
public class Project2Application {
	
//	@Autowired
//	private ManagerServiceImpl managerService;

	public static void main(String[] args) {
		SpringApplication.run(Project2Application.class, args);
	}
	
//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail() {
//		managerService.sendSimpleEmail("devarianil415@gmail.com", "approved", "your staus changed to approved!");
//	}

}

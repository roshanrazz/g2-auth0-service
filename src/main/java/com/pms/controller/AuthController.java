package com.pms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.pms.entity.Admin;
import com.pms.entity.Doctor;
import com.pms.entity.Nurse;
import com.pms.service.AuthService;

@RequestMapping("/api/v1")
@RestController
public class AuthController {
	
	@Autowired
	AuthService service;
	
	
	@GetMapping("/pms/nurses")
	public ResponseEntity<?> getNurses() throws UnirestException {
		
		List<Nurse> list = service.getNurseDetails();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
		}
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/pms/admin")
	public ResponseEntity<?> getAdmin() throws UnirestException {
		
		List<Admin> list = service.getAdminDetails();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
		}
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/pms/doctors")
	public ResponseEntity<?> getDoctors() throws UnirestException {
		
		List<Doctor> list = service.getDoctorDetails();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
		}
		return ResponseEntity.ok().body(list);
	}
		
	


}

package com.pms.service;

import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.pms.entity.Admin;
import com.pms.entity.Doctor;
import com.pms.entity.Nurse;

public interface AuthService {
	
	public void addAdmin(List<Admin> admin);
	public void addNurse(List<Nurse> nurse);
	public void addDoctor(List<Doctor> doctor);
	public List<Nurse> getNurseDetails()throws UnirestException;
	public List<Admin> getAdminDetails()throws UnirestException;
	public List<Doctor> getDoctorDetails()throws UnirestException;
	public void addUsersInfo() throws UnirestException; 
	


}

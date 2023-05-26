package com.pms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.pms.entity.Admin;
import com.pms.entity.Doctor;
import com.pms.entity.Nurse;
import com.pms.repository.AdminRepository;
import com.pms.repository.DoctorRepository;
import com.pms.repository.NurseRepository;
import com.pms.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	NurseRepository nurseRepo;

	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	DoctorRepository doctorRepo;

	@Override
	public void addAdmin(List<Admin> adminList) {
		adminRepo.saveAll(adminList);

	}
	@Override
	public void addDoctor(List<Doctor> doctorList) {
		doctorRepo.saveAll(doctorList);
	}

	@Override
	public void addNurse(List<Nurse> nurseList) {
		nurseRepo.saveAll(nurseList);
	}
	
	@Override
	public List<Doctor> getDoctorDetails() throws UnirestException {
		
		List<Doctor> list = doctorRepo.findAll();
		if(list != null) {
			return list;
		}

		return null;
	}
	

	@Override
	public List<Nurse> getNurseDetails() throws UnirestException {
		List<Nurse> list = nurseRepo.findAll();
		if(list != null) {
			return list;
		}

		return null;
	}

	@Override
	public List<Admin> getAdminDetails() throws UnirestException {
		
		List<Admin> list = adminRepo.findAll();
		if(list != null) {
			return list;
		}

		return null;
	}

	// ---------------- Get the token --------------------------------------------

	public static String getToken() throws UnirestException {

		HttpResponse<String> response = Unirest.post("https://dev-7vcuci4tdeykfzss.us.auth0.com/oauth/token")
				.header("content-type", "application/json")
				.body("{\"client_id\":\"D4SKE5uz2tw92xACR5ZrKA2DUQpeNRA8\",\"client_secret\":\"TxUXhIK-f4EDrAzO1lLgOsSCt0866twNXSkZ4MsqjRhL2I6jNipg2AtnefWF9dhS\",\"audience\":\"https://dev-7vcuci4tdeykfzss.us.auth0.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
				.asString();

		System.out.println(response);
		JsonNode jsonNode = null;
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			jsonNode = objectMapper.readTree(response.getBody());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		String accessToken = jsonNode.get("access_token").textValue();
		String tokenType = jsonNode.get("token_type").textValue();
		String token = tokenType + " " + accessToken;
		System.out.println(token);

		return token;
	}
	

	// ---------- Get users ------------------------

	public void addUsersInfo() throws UnirestException {

		String token = getToken();
		ArrayList<JsonNode> l = new ArrayList<>();

		List<Admin> adminList = new ArrayList<>();

		List<Nurse> nurseList = new ArrayList<>();
		
		List<Doctor> doctorList = new ArrayList<>();

		HttpResponse<String> response = Unirest.get("https://dev-7vcuci4tdeykfzss.us.auth0.com/api/v2/users")
				.header("authorization", token).asString();

		JsonNode jsonNode = null;
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			jsonNode = objectMapper.readTree(response.getBody());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < jsonNode.size(); i++) {

			l.add(jsonNode.get(i));
			Admin adm = new Admin();
			Nurse nurs = new Nurse();
			Doctor doct = new Doctor();

			if (jsonNode.get(i).get("user_metadata") != null) {

				/*
				 * System.out.print(jsonNode.get(i).get("name")); System.out.print("\t");
				 * System.out.print(jsonNode.get(i).get("email")); System.out.print("\t");
				 * System.out.print(jsonNode.get(i).get("user_metadata").get("role"));
				 * System.out.print("\t");
				 * 
				 * System.out.println(jsonNode.get(i).get("user_metadata").get("contact"));
				 * 
				 * System.out.println(i);
				 */
				String role = jsonNode.get(i).get("user_metadata").get("role").textValue();
				//System.out.println(role);

				if (role.equalsIgnoreCase("admin")) {

					adm.setName(jsonNode.get(i).get("name").textValue());
					adm.setEmail(jsonNode.get(i).get("email").textValue());
					adm.setContact(jsonNode.get(i).get("user_metadata").get("contact").textValue());
					adminList.add(adm);

				}

				if (role.equalsIgnoreCase("nurse")) {

					nurs.setName(jsonNode.get(i).get("name").textValue());
					nurs.setEmail(jsonNode.get(i).get("email").textValue());
					nurs.setContact(jsonNode.get(i).get("user_metadata").get("contact").textValue());
					nurseList.add(nurs);

				}

				if (role.equalsIgnoreCase("doctor")) {
					doct.setName(jsonNode.get(i).get("name").textValue());
					doct.setEmail(jsonNode.get(i).get("email").textValue());
					doct.setContact(jsonNode.get(i).get("user_metadata").get("contact").textValue());
					doctorList.add(doct);

				}
			}
		}

		addNurse(nurseList);
		addDoctor(doctorList);
		addAdmin(adminList);
		
	}

}

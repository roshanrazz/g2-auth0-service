package com.pms.entity;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin {
	
	@Id
	@GeneratedValue(generator = CustomIdGenerator.GENERATOR_NAME)
	@GenericGenerator(name = CustomIdGenerator.GENERATOR_NAME, strategy = "com.pms.entity.CustomIdGenerator", parameters = {
	@Parameter(name = CustomIdGenerator.PREFIX_PARAM, value = "AD00") })
	@Column(name = "admin_id")
	private String adminId;
	@Column(unique=true)
	private String email;
	private String contact;	
	@Column(name="nurse_name")	
	private String name;
	
	

}

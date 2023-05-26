package com.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String> {


}

package com.pms.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.entity.Nurse;

public interface NurseRepository extends JpaRepository<Nurse, String> {

	
}

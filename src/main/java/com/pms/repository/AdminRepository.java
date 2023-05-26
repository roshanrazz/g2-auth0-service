package com.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{

}

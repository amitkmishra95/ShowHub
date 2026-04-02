package com.project.BTS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BTS.entity.Admin;


public interface AdminRepository extends JpaRepository<Admin,Long> {
	
	Optional<Admin> findByEmail(String email);
	boolean existsByEmail(String email);

}


package com.project.BTS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BTS.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	Optional<User> findByEmail(String email);
//	Optional findById(Long userId);
	boolean existsByEmail(String email);
	

}

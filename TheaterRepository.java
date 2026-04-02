package com.project.BTS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BTS.entity.User;
import com.project.BTS.entity.Theater;

public interface TheaterRepository extends JpaRepository<Theater,Long> {
	
	List<Theater> findByCityId(Long city_Id);
	
	List<Theater> findByAdminId(Long adminId);

}

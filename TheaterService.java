package com.project.BTS.service;
import com.project.BTS.exception.*;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import lombok.Builder;

import com.project.BTS.dto.LoginRequest;
import com.project.BTS.dto.TheaterRequest;
import com.project.BTS.dto.UserRequest;
import com.project.BTS.entity.User;
import com.project.BTS.entity.Admin;
import com.project.BTS.entity.City;
import com.project.BTS.entity.Theater;
import com.project.BTS.repository.AdminRepository;
import com.project.BTS.repository.CityRepository;
import com.project.BTS.repository.TheaterRepository;
import com.project.BTS.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TheaterService {
	
private final TheaterRepository theatreRepository;
private final AdminRepository adminRepository;
private final CityRepository cityRepository;
private final CityService cityService;

//public Theater addTheater(TheaterRequest req)
//{
//	City city = cityService.getCityById(req.getCitId());
//	Theater t = Theater.builder()
//			.name(req.getName())
//			.address(req.getAddress())
//			.city(city)
//			.build();
//	
//	return theatreRepository.save(t);
//			
//}
public Theater addTheater(Theater theater, Long adminId, Long cityId) {

    Admin admin = adminRepository.findById(adminId)
            .orElseThrow(() -> new RuntimeException("Admin not found"));

    City city = cityRepository.findById(cityId)
            .orElseThrow(() -> new RuntimeException("City not found"));

    theater.setAdmin(admin);
    theater.setCity(city);

    return theatreRepository.save(theater);
}
public List<Theater> getAllTheatre(){
	return theatreRepository.findAll();
}

public Theater getTheatreById(Long id)
{
	return theatreRepository.findById(id)
			.orElseThrow(()->new RuntimeException("Theatre not found with id : "+id));
}

public List<Theater> getTheatreByCity(Long cityId){
	return theatreRepository.findByCityId(cityId);
}

public List<Theater> getTheatreByAdmin(Long adminId){
	return theatreRepository.findByAdminId(adminId);
}

//public @Nullable Object getTheatreById() {
//	// TODO Auto-generated method stub
//	return null;
//}

}

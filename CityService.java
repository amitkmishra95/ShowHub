package com.project.BTS.service;
import com.project.BTS.exception.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.Builder;

import com.project.BTS.dto.LoginRequest;
import com.project.BTS.dto.UserRequest;
import com.project.BTS.entity.User;
import com.project.BTS.entity.City;
import com.project.BTS.repository.CityRepository;
import com.project.BTS.repository.TheaterRepository;
import com.project.BTS.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {
	

//private final  CityRepository cityRepository;
//
//public City addCity(City city)
//{
//	return cityRepository.save(city);
//}
//
//public List<City> getAllCities()
//{
//	return cityRepository.findAll();
//}
//
//public City getCityById(Long id)
//{
//	return cityRepository.findById(id)
//			.orElseThrow(()->new RuntimeException("City not found with id: "+id));
//}
	
	 @Autowired
	    private CityRepository cityRepository;

	    public City saveCity(City city) {
	        return cityRepository.save(city);
	    }

	    public List<City> getAllCities() {
	        return cityRepository.findAll();
	    }

	    public City getCityById(Long id) {
	        return cityRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("City not found"));
	    }
}

package com.project.BTS.service;

import org.springframework.stereotype.Service;

import com.project.BTS.dto.LoginRequest;
import com.project.BTS.entity.Admin;
import com.project.BTS.entity.Movie;
import com.project.BTS.entity.Screen;
import com.project.BTS.entity.Show;
import com.project.BTS.entity.Theater;
import com.project.BTS.entity.User;
import com.project.BTS.repository.AdminRepository;
import com.project.BTS.repository.MovieRepository;
import com.project.BTS.repository.ScreenRepository;
import com.project.BTS.repository.ShowRepository;
import com.project.BTS.repository.TheaterRepository;

@Service
public class AdminService {
	
	private final AdminRepository adminRepository;
	
	 private final MovieRepository movieRepository;
	    private final TheaterRepository theaterRepository;
	    private final ScreenRepository screenRepository;
	    private final ShowRepository showRepository;
	    
	    public AdminService(AdminRepository adminRepository,
	    		MovieRepository  movieRepository,
                TheaterRepository theaterRepository,
                ScreenRepository screenRepository,
                ShowRepository showRepository) {
	    	
	    	 this.adminRepository = adminRepository;
			this.movieRepository = movieRepository;
	         this.theaterRepository = theaterRepository;
	         this.screenRepository = screenRepository;
	         this.showRepository = showRepository;
	    }
	    public Admin register(Admin admin) {
	        // In real apps, hash password using BCrypt
	        return adminRepository.save(admin);
	    }

	    public Admin login(LoginRequest req)
	    {
	    	Admin admin = adminRepository.findByEmail(req.getEmail())
	    			.orElseThrow(()->new RuntimeException("Admin not found with given email: "+req.getEmail()));
	    	
	    	if(!admin.getPassword().equals(req.getPassword()))
	    	{
	    		throw new RuntimeException("Invalid Password");
	    	}
	    	return admin;

	    }
	    
	    public Movie addMovie(Movie movie) {
	        return movieRepository.save(movie);
	    }

	    public Theater addTheater(Theater theater) {
	        return theaterRepository.save(theater);
	    }

	 
	    public Screen addScreen(Screen screen) {
	        return screenRepository.save(screen);
	    }

	    public Show addShow(Show show) {
	        return showRepository.save(show);
	    }
	    
	    public Admin getAdminById(Long adminId) {
	        return adminRepository.findById(adminId)
	                .orElseThrow(() -> new RuntimeException("Admin not found"));
	    }
}

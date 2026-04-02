package com.project.BTS.service;
import com.project.BTS.exception.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import lombok.Builder;

import com.project.BTS.dto.LoginRequest;
import com.project.BTS.dto.ShowRequest;
import com.project.BTS.dto.UserRequest;
import com.project.BTS.entity.Movie;
import com.project.BTS.entity.Screen;
import com.project.BTS.entity.Seat;
import com.project.BTS.entity.User;
import com.project.BTS.entity.City;
import com.project.BTS.entity.Show;
import com.project.BTS.entity.ShowSeat;
import com.project.BTS.entity.Theater;
import com.project.BTS.repository.CityRepository;
import com.project.BTS.repository.MovieRepository;
import com.project.BTS.repository.ScreenRepository;
import com.project.BTS.repository.SeatRepository;
import com.project.BTS.repository.ShowRepository;
import com.project.BTS.repository.ShowSeatRepository;
import com.project.BTS.repository.TheaterRepository;
import com.project.BTS.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;
    private final SeatRepository seatRepository;
    private final ShowSeatRepository showSeatRepository;
    

//    public Show addShow(ShowRequest req) {
//
//        Movie movie = movieService.getMovieById(req.getMovieId());
//        Screen screen = screenService.getScreenById(req.getScreenId());
//
//        Show show = Show.builder()
//                .movie(movie)
//                .screen(screen)
//                .showDate(req.getShowDate())
//                .startTime(req.getStartTime())
//                .endTime(req.getEndTime())
//                .ticketPrice(req.getTicketPrice())
//                .build();
//
//        return showRepository.save(show);
//    }
    public Show createShow(ShowRequest showRequest) {

        Screen screen = screenRepository.findById(showRequest.getScreenId())
                .orElseThrow(() -> new RuntimeException("Screen not found"));

       
//        if (!screen.getTheatre().getAdmin().getId().equals(adminId)) {
//            throw new RuntimeException("Unauthorized");
//        }

        Movie movie = movieRepository.findById(showRequest.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Show show = new Show();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setShowDate(showRequest.getShowDate());
        show.setStartTime(showRequest.getStartTime());
        show.setEndTime(showRequest.getEndTime());


        Show savedShow = showRepository.save(show);

        generateShowSeats(savedShow, screen, showRequest);

        return savedShow;
    }

    private void generateShowSeats(Show show, Screen screen, ShowRequest req ) {

        List<Seat> seats = seatRepository.findByScreenId(screen.getId());

        List<ShowSeat> showSeats = new ArrayList<>();

        for (Seat seat : seats) {

            double price;

            switch (seat.getSeatType()) {
                case "VIP":
                    price = req.getVipPrice() ;
                    break;
                case "PREMIUM":
                    price = req.getPremiumPrice();
                    break;
                default:
                    price = req.getRegularPrice();
            }
            ShowSeat ss = new ShowSeat();
           ss.setSeat(seat);
           ss.setShow(show);
           ss.setPrice(price);
           ss.setStatus("AVAILABLE");

            showSeats.add(ss);
        }

        showSeatRepository.saveAll(showSeats);
    }
    
    public List<Show> getAllShow()
    {
    	return showRepository.findAll();
    }
    
    public Show getShowById(Long id)
    {
    	return showRepository.findById(id)
    			.orElseThrow(()->new RuntimeException("Show not found with id : "+id));
    }
    
    public List<Show> getShowByMovie(Long movieId)
    {
    	return showRepository.findByMovieId(movieId);
    }
    
    public List<Show> getShowByMovieAndDate(Long movieId, LocalDate date)
    {
    	return showRepository.findByMovieIdAndShowDate(movieId,date);
    }
    
    public List<Show> getShowByScreen(Long screenId)
    {
    	return showRepository.findByScreenId(screenId);
    }

//	public List<Show> getShowByScreenId(Long id) {
//	
//		return showRepository.
//	}
}


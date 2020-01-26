package com.anixe.quiz.service;

import com.anixe.quiz.domain.Booking;
import com.anixe.quiz.domain.Hotel;
import com.anixe.quiz.domain.HotelResponse;
import com.anixe.quiz.repositories.BookingRepository;
import com.anixe.quiz.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private BookingRepository bookingRepository;


    public Hotel createAndUpdate(Hotel hotel) {

        return hotelRepository.save(hotel);
    }

    public List<Hotel> findAllHotel(){
        return hotelRepository.findAll();
    }

    public Optional<Hotel> findById(Integer id){
        return hotelRepository.findById(id);
    }


    public void deleteHotel(Hotel hotel) {
        hotelRepository.delete(hotel);
    }

    public Set<HotelResponse> findByCustomerSurname(String surname){
        return  hotelRepository.findByCustomerSurname(surname);

    }



}

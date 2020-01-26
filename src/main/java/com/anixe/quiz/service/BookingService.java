package com.anixe.quiz.service;

import com.anixe.quiz.domain.Booking;
import com.anixe.quiz.domain.Hotel;
import com.anixe.quiz.domain.HotelResponse;
import com.anixe.quiz.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;


    public List<Booking> findByHotelId(Integer hotelId) {

        return bookingRepository.findByHotelId(hotelId);
    }

    public Booking createOrUpdate(Booking booking){
       return bookingRepository.save(booking);
    }

    public Optional<Booking> findByBookingId(Integer id){
        return bookingRepository.findById(id);
    }

    public void delete(Booking booking){
        bookingRepository.delete(booking);
    }


}

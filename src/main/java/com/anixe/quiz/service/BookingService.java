package com.anixe.quiz.service;

import com.anixe.quiz.domain.Booking;
import com.anixe.quiz.domain.Hotel;
import com.anixe.quiz.repositories.BookingRepository;
import com.anixe.quiz.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public List<Booking> findByHotelId(Integer hotelId) {

        return bookingRepository.findByHotelId(hotelId);
    }
    public Booking save(Booking booking){
        Hotel hotel ;
        if(booking.getHotel().getId() == null){
            hotel = hotelRepository.save(booking.getHotel());
        } else {
            Optional<Hotel> existHotel = hotelRepository.findById(booking.getHotel().getId());
            if(!existHotel.isPresent()){
                hotel = booking.getHotel();
                hotel.setId(null);
                hotel = hotelRepository.save(hotel);

            } else {
                hotel = existHotel.get();
            }

        }

        booking.setHotel(hotel);

        return bookingRepository.save(booking);
    }

    public Optional<Booking> findByBookingId(Integer id){
        return bookingRepository.findById(id);
    }

    public void delete(Booking booking){
        bookingRepository.delete(booking);
    }


}

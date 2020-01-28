package com.anixe.quiz.service;

import com.anixe.quiz.domain.Booking;
import com.anixe.quiz.domain.Hotel;
import com.anixe.quiz.response.BookingTotalAmount;
import com.anixe.quiz.response.HotelResponse;
import com.anixe.quiz.repositories.BookingRepository;
import com.anixe.quiz.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private BookingRepository bookingRepository;


    public Hotel save(Hotel hotel) {

        return hotelRepository.save(hotel);
    }

    public List<Hotel> findAllHotel() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> findById(Integer id) {
        return hotelRepository.findById(id);
    }


    public void deleteHotel(Hotel hotel) {
        hotelRepository.delete(hotel);
    }

    public Set<HotelResponse> findByCustomerSurname(String surname) {
        return hotelRepository.findByCustomerSurname(surname);

    }

    public BookingTotalAmount findSumPriceByHotel(Integer hotelId) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
        BookingTotalAmount bookingTotalAmount = new BookingTotalAmount();
        hotelOptional.ifPresent(hotel -> {

            if (hotel.getBookings() != null && !hotel.getBookings().isEmpty()) {
                bookingTotalAmount.setHotelId(hotel.getId());
                bookingTotalAmount.setHotelName(hotel.getName());
                bookingTotalAmount.setSumPrice(hotel.getBookings().stream()
                        .map(Booking::getPriceAmount).reduce(BigDecimal.ZERO, BigDecimal::add));

            }

        });

        return bookingTotalAmount;

    }

}

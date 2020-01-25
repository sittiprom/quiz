package com.anixe.quiz.controller;

import com.anixe.quiz.domain.Booking;
import com.anixe.quiz.domain.Hotel;
import com.anixe.quiz.domain.HotelResponse;
import com.anixe.quiz.service.BookingService;
import com.anixe.quiz.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value = "/getHotelBySurname/{surname}", method = RequestMethod.GET)
    public Set<HotelResponse> getHotelByCustomerSurname(@PathVariable String surname) {
        return hotelService.findByCustomerSurname(surname);
    }

    @RequestMapping(value = "/getbookingByHotelId/{id}", method = RequestMethod.GET)
    public List<Booking> getBookingByHotelId(@PathVariable Integer id) {
        return bookingService.findByHotelId(id);
    }
}

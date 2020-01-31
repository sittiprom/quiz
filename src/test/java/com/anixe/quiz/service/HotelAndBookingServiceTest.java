package com.anixe.quiz.service;

import com.anixe.quiz.domain.Booking;
import com.anixe.quiz.domain.Hotel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelAndBookingServiceTest {

    @Autowired
    private HotelService hotelService ;

    @Autowired
    private BookingService bookingService;


    @Before
    public void initHotel(){

        hotelService.deleteAllHotel();

        for(int i = 0 ; i < 20 ;i++){
            Hotel hotel = new Hotel();
            hotel.setId(i);
            hotel.setName("Hotel" + i);
            hotel.setAddress("Address" + i);
            hotel.setStarRating(new BigDecimal(Math.random() * (5.0 - 0.5) + 0.5));
            hotelService.save(hotel);

        }
    }

    @Test
    public void createBookingWithNotExistHotel(){
        List<Hotel> hotels = hotelService.findAllHotel();

        Assert.assertEquals("Expect 20 hotel" ,20,hotels.size());

        Hotel hotel = new Hotel();
        hotel.setName("Hotel Test");
        hotel.setAddress("Address");


        Booking booking = new Booking();
        booking.setCustomerName("Bow");
        booking.setCustomerSurname("Sitti");
        booking.setNumberOfPax(2);
        booking.setHotel(hotel);

        Booking result = bookingService.save(booking);

        hotels = hotelService.findAllHotel();

        Assert.assertEquals("Expect 21 hotel" ,21,hotels.size());
        Assert.assertEquals("Expect Hotel Name is Hotel Test","Hotel Test" , result.getHotel().getName());

    }







}

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
import java.util.Optional;


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

        for(int i = 0 ; i <= 20 ;i++){
            Hotel hotel = new Hotel();
            hotel.setId(i);
            hotel.setName("Hotel" + i);
            hotel.setAddress("Address" + i);
            hotel.setStarRating(new BigDecimal(Math.random() * (5.0 - 0.5) + 0.5));
            hotelService.save(hotel);

        }
    }

    @Test
    public void createBooking(){
        List<Hotel> hotels = hotelService.findAllHotel();
        Assert.assertEquals("Expect 20 hotel" ,20,hotels.size());

        //create booking in existing hotel
        Optional<Hotel> hotelOne = hotelService.findById(1);

        Booking bookingWithExistingHotel = new Booking();
        bookingWithExistingHotel.setCustomerName("Bo");
        bookingWithExistingHotel.setCustomerSurname("Test");
        bookingWithExistingHotel.setNumberOfPax(2);
        bookingWithExistingHotel.setHotel(hotelOne.get());
        bookingWithExistingHotel = bookingService.save(bookingWithExistingHotel);

        Assert.assertEquals("Expect 20 hotel .No new Hotel" ,20,hotels.size());
        Assert.assertEquals("Expect Hotel Name is Hotel1" ,"Hotel1" ,
                bookingWithExistingHotel.getHotel().getName());
        Assert.assertEquals("Expect HotelId is 1" , Integer.valueOf(1) ,bookingWithExistingHotel.getHotel().getId());

        //Create booing with notFoundHotelId
        Hotel hotelNotFound = new Hotel();
        hotelNotFound.setId(1099);
        hotelNotFound.setName("Hotel Not Found  Test");
        hotelNotFound.setAddress("Address");

        Booking bookingWithNotFoundHotel = new Booking();
        bookingWithNotFoundHotel.setCustomerName("Bo");
        bookingWithNotFoundHotel.setCustomerSurname("No Hotel");
        bookingWithNotFoundHotel.setNumberOfPax(2);
        bookingWithNotFoundHotel.setHotel(hotelNotFound);
        bookingWithNotFoundHotel = bookingService.save(bookingWithNotFoundHotel);
        hotels = hotelService.findAllHotel();

        Assert.assertEquals("Expect 21 hotel . New Hotel is created" ,21,hotels.size());
        Assert.assertEquals("Expect Hotel Name is Hotel1" ,"Hotel Not Found  Test" ,
                bookingWithNotFoundHotel.getHotel().getName());
        Assert.assertNotEquals("Expect HotelId is not 1099" , Integer.valueOf(1099) ,bookingWithNotFoundHotel.getHotel().getId());


        Hotel hotel = new Hotel();
        hotel.setName("Hotel Test");
        hotel.setAddress("Address");


        Booking booking = new Booking();
        booking.setCustomerName("Bow");
        booking.setCustomerSurname("Sitti");
        booking.setNumberOfPax(2);
        booking.setHotel(hotel);
        booking = bookingService.save(booking);

        hotels = hotelService.findAllHotel();

        Assert.assertEquals("Expect 22 hotel" ,22,hotels.size());
        Assert.assertEquals("Expect Hotel Name is Hotel Test","Hotel Test" , booking.getHotel().getName());

    }







}

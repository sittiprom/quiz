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
import java.util.*;
import java.util.stream.Collectors;


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
        Map<String, BigDecimal> sumPrice = new HashMap<>();
        hotelOptional.ifPresent(hotel -> {
            List<Booking> bookingPrice = new ArrayList<>();
            if (hotel.getBookings() != null && !hotel.getBookings().isEmpty()) {
                bookingPrice = hotel.getBookings().stream().filter(booking -> (booking.getCurrency() != null
                        && !booking.getCurrency().isEmpty()) && booking.getPriceAmount() != null).collect(Collectors.toList());

            }
            Set<String> currencySet = new HashSet<>(bookingPrice.stream().map(booking -> booking.getCurrency())
                    .collect(Collectors.toSet()));


            for (String currency : currencySet) {
                for (Booking booking : bookingPrice) {

                    if (booking.getCurrency().equals(currency)) {
                        if (sumPrice.containsKey(currency)) {
                            sumPrice.put(currency, sumPrice.get(currency).add(booking.getPriceAmount()));

                        } else {
                            sumPrice.put(currency, booking.getPriceAmount());
                        }

                    }
                }

            }
            bookingTotalAmount.setHotelName(hotel.getName());
            bookingTotalAmount.setHotelId(hotelId);
            bookingTotalAmount.setSumPrice(sumPrice);


        });

        return bookingTotalAmount;
    }
}

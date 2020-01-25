package com.anixe.quiz.controller;

import com.anixe.quiz.domain.Hotel;
import com.anixe.quiz.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/getAllHotel")
    public ResponseEntity<List<Hotel>> findAll() {
        return ResponseEntity.ok(hotelService.findAllHotel());
    }

    @GetMapping("/findByHotel/{id}")
    public ResponseEntity<Hotel> findById(@PathVariable Integer id) {

        Optional<Hotel> hotel = hotelService.findById(id);
        if (!hotel.isPresent()) {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(hotel.get());
    }

}


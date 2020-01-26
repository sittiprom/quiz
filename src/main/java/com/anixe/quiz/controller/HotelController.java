package com.anixe.quiz.controller;

import com.anixe.quiz.domain.Hotel;
import com.anixe.quiz.domain.HotelResponse;
import com.anixe.quiz.request.HotelRequest;
import com.anixe.quiz.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;


    @GetMapping("/findAll")
    public ResponseEntity<List<Hotel>> findAll() {
        return ResponseEntity.ok(hotelService.findAllHotel());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Hotel> findById(@PathVariable Integer id) {

        Optional<Hotel> hotel = hotelService.findById(id);
        if (!hotel.isPresent()) {
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(hotel.get());
    }

    @GetMapping("/findHotelBySurname/{surname}")
    public ResponseEntity<Set<HotelResponse>> getHotelByCustomerSurname(@PathVariable String surname) {
        Set<HotelResponse> hotelResponses = hotelService.findByCustomerSurname(surname);
        if(hotelResponses == null || hotelResponses.isEmpty() || hotelResponses.size() == 0){
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(hotelResponses);
    }

    @PostMapping("/create")
    public ResponseEntity create(@Valid @RequestBody HotelRequest hotelRequest) {
        Hotel hotel = Hotel.builder()
                        .id(hotelRequest.getId())
                        .name(hotelRequest.getName())
                        .starRating(hotelRequest.getStarRating()).build();
        return ResponseEntity.ok(hotelService.save(hotel));
    }

}


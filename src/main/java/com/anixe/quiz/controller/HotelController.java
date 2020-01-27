package com.anixe.quiz.controller;

import com.anixe.quiz.domain.Hotel;
import com.anixe.quiz.response.HotelResponse;
import com.anixe.quiz.request.HotelRequest;
import com.anixe.quiz.service.HotelService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelService hotelService;


    @GetMapping("/findAll")
    public ResponseEntity<List<Hotel>> findAll() {
        return ResponseEntity.ok(hotelService.findAllHotel());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Hotel> findById(@PathVariable Integer id) {

        log.info("Find hotel By Id  : " + id);

        Optional<Hotel> hotel = hotelService.findById(id);

        if (!hotel.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(hotel.get());
    }

    @GetMapping("/findHotelBySurname/{surname}")
    public ResponseEntity<Set<HotelResponse>> getHotelByCustomerSurname(@PathVariable String surname) {

        log.info("Find hotel By Customer surname   : " + surname);

        Set<HotelResponse> hotelResponses = hotelService.findByCustomerSurname(surname);
        if (hotelResponses == null || hotelResponses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(hotelResponses);
    }

    @PostMapping("/create")
    public ResponseEntity create(@Valid @RequestBody HotelRequest hotelRequest) {

        log.info("hotel create request : " + hotelRequest.toString());

        return ResponseEntity.ok(hotelService.save(buildHotelObject(hotelRequest)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Hotel> update(@PathVariable Integer id, @Valid @RequestBody HotelRequest hotelRequest) {

        log.info("hotel update request : " + hotelRequest.toString());

        if (!hotelService.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }
        Hotel hotel = buildHotelObject(hotelRequest);
        hotel.setId(id);
        return ResponseEntity.ok(hotelService.save(hotel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {

        log.info("delete hotel id  : " + id);
        Optional<Hotel> deletedHotel = hotelService.findById(id);
        if (!deletedHotel.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        hotelService.deleteHotel(deletedHotel.get());

        return ResponseEntity.ok().build();
    }

    private Hotel buildHotelObject(HotelRequest hotelRequest) {
        return Hotel.builder()
                .id(hotelRequest.getId())
                .name(hotelRequest.getName())
                .address(hotelRequest.getAddress())
                .starRating(hotelRequest.getStarRating()).build();

    }


}


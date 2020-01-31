package com.anixe.quiz.controller;

import com.anixe.quiz.domain.Hotel;
import com.anixe.quiz.response.BookingTotalAmount;
import com.anixe.quiz.response.HotelResponse;
import com.anixe.quiz.request.HotelRequest;
import com.anixe.quiz.service.HotelService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("hotel")
public class HotelController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(HotelController.class);
    private static final String HOTEL_ID = "Hotel Id " ;
    private static final String NOT_EXISTED = "is not existed" ;

    @Autowired
    private HotelService hotelService;


    @GetMapping("/findAll")
    public ResponseEntity<List<Hotel>> findAll() {
        return ResponseEntity.ok(hotelService.findAllHotel());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Hotel> findById(@PathVariable Integer id) {

        log.info("Find hotel By Id  : {} " , id);

        Optional<Hotel> hotel = hotelService.findById(id);

        if (!hotel.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(hotel.get());
    }

    @GetMapping("/findHotelBySurname/{surname}")
    public ResponseEntity<HotelResponse> getHotelByCustomerSurname(@PathVariable String surname) {

        log.info("Find hotel By Customer surname   : {} " , surname);

        Set<HotelResponse> hotelResponses = hotelService.findByCustomerSurname(surname);
        if (hotelResponses == null || hotelResponses.isEmpty()) {
            return returnError(HttpStatus.BAD_REQUEST, "Customer surname  " + surname + NOT_EXISTED);
        }

        return returnSuccess(hotelResponses);
    }

    @PostMapping("/create")
    public ResponseEntity<Hotel> create(@Valid @RequestBody HotelRequest hotelRequest , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return returnError(HttpStatus.BAD_REQUEST,bindingResult.getAllErrors().toString());
        }

        log.info("hotel create request : {} " , hotelRequest);
        return ResponseEntity.ok(hotelService.save(createHotelObject(hotelRequest)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Hotel> update(@PathVariable Integer id, @Valid @RequestBody HotelRequest hotelRequest ,
                                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return  returnError(HttpStatus.BAD_REQUEST,bindingResult.getAllErrors().toString());
        }

        if (!hotelService.findById(id).isPresent()) {
            log.error( HOTEL_ID + " {}" , id + NOT_EXISTED);
            return  returnError(HttpStatus.BAD_REQUEST,HOTEL_ID + id + NOT_EXISTED);
        }

        log.info("hotel update request : {} "  + " with hotel id {} : " ,hotelRequest, id);

        Hotel hotel = createHotelObject(hotelRequest);
        hotel.setId(id);
        return ResponseEntity.ok(hotelService.save(hotel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {

        log.info("delete hotel id {} : " , id);
        Optional<Hotel> deletedHotel = hotelService.findById(id);
        if (!deletedHotel.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        hotelService.deleteHotel(deletedHotel.get());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/findTotalAmountBooking/{id}")
    public ResponseEntity<BookingTotalAmount> findTotalAmountByHotelId(@PathVariable Integer id) {

        if (!hotelService.findById(id).isPresent()) {
            log.error(HOTEL_ID +  " {} " , id + NOT_EXISTED);
            return returnError(HttpStatus.BAD_REQUEST,HOTEL_ID + id + NOT_EXISTED);

        }

        log.info("Find TotalAmount Booking By hotelId : {} " , id);

        return returnSuccess(hotelService.findSumPriceByHotel(id));
    }

    private Hotel createHotelObject(HotelRequest hotelRequest) {
        return Hotel.builder()
                .id(hotelRequest.getId())
                .name(hotelRequest.getName())
                .address(hotelRequest.getAddress())
                .starRating(hotelRequest.getStarRating()).build();

    }


}


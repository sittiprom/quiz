package com.anixe.quiz.controller;

import com.anixe.quiz.domain.Booking;
import com.anixe.quiz.request.BookingRequest;
import com.anixe.quiz.service.BookingService;
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

@RestController
@RequestMapping("booking")
public class BookingController {

    private static final Logger log = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private BookingService bookingService;

    @GetMapping("/findByHotelId/{id}")
    public ResponseEntity<List<Booking>> getBookingByHotelId(@PathVariable Integer id) {

        log.info(" Find Booking By HotelId :  " + id);

        return ResponseEntity.ok(bookingService.findByHotelId(id));

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Integer id) {

        log.info(" Find Booking By BookingId :  " + id);

        Optional<Booking> booking = bookingService.findByBookingId(id);
        if (!booking.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(booking.get());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody BookingRequest bookingRequest,
                                          BindingResult bindingResult) {
        log.info(" Booking Create Request " + bookingRequest);

        if (bindingResult.hasErrors()) {
            return  new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);

        }

        return ResponseEntity.ok(bookingService.save(createBookingObject(bookingRequest)));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        log.info(" Delete Booking Id " + id );

        Optional<Booking> deletedHotel = bookingService.findByBookingId(id);
        if (!deletedHotel.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        bookingService.delete(deletedHotel.get());

        return ResponseEntity.ok().build();
    }


    private Booking createBookingObject(BookingRequest bookingRequest){

        return  Booking.builder()
                          .customerName(bookingRequest.getCustomerName())
                          .customerSurname(bookingRequest.getCustomerSurname())
                          .numberOfPax(bookingRequest.getNumberOfPax())
                          .hotel(bookingRequest.getHotel()).build();

    }
}

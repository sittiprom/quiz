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
    public ResponseEntity<?> getBookingByHotelId(@PathVariable Integer id) {

        log.info(" Find Booking By HotelId :  " + id);
        List<Booking> bookings = bookingService.findByHotelId(id);
        if(bookings == null || bookings.isEmpty()){
            return  ResponseEntity.badRequest().body("Hotel Id " + id + " is not existed");
        }

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
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        }

        return ResponseEntity.ok(bookingService.save(createBookingObject(bookingRequest)));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody BookingRequest bookingRequest,
                                    BindingResult bindingResult) {

        log.info("booking update request : " + bookingRequest.toString() + "with booking id : " + id);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        }

        if (!bookingService.findByBookingId(id).isPresent()) {
            log.error("Booking Id " + id + " is not existed");
            return  ResponseEntity.badRequest().body("Booking Id " + id + " is not existed");
        }

        Booking booking = createBookingObject(bookingRequest);
        booking.setId(id);
        return ResponseEntity.ok(bookingService.save(booking));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        log.info(" Delete Booking Id " + id);

        Optional<Booking> deletedHotel = bookingService.findByBookingId(id);
        if (!deletedHotel.isPresent()) {
            return  ResponseEntity.badRequest().body("Booking Id " + id + " is not existed");
        }

        bookingService.delete(deletedHotel.get());

        return ResponseEntity.ok().build();
    }


    private Booking createBookingObject(BookingRequest bookingRequest) {

        return Booking.builder()
                .customerName(bookingRequest.getCustomerName())
                .customerSurname(bookingRequest.getCustomerSurname())
                .numberOfPax(bookingRequest.getNumberOfPax())
                .hotel(bookingRequest.getHotel()).build();

    }
}

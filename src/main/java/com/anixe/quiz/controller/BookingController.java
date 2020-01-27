package com.anixe.quiz.controller;

import com.anixe.quiz.domain.Booking;
import com.anixe.quiz.domain.Hotel;
import com.anixe.quiz.request.BookingRequest;
import com.anixe.quiz.service.BookingService;
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

    @Autowired
    private BookingService bookingService;

    @GetMapping("/findByHotelId/{id}")
    public ResponseEntity<List<Booking>> getBookingByHotelId(@PathVariable Integer id) {

        return ResponseEntity.ok(bookingService.findByHotelId(id));

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Integer id) {

        Optional<Booking> booking = bookingService.findByBookingId(id);
        if (!booking.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(booking.get());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody BookingRequest bookingRequest,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return  new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);

        }

        return ResponseEntity.ok(bookingService.save(createBookingObject(bookingRequest)));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Booking> deletedHotel = bookingService.findByBookingId(id);
        if (!deletedHotel.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        bookingService.delete(deletedHotel.get());

        return ResponseEntity.ok().build();
    }


    private Booking createBookingObject(BookingRequest bookingRequest){

        Booking booking = Booking.builder()
                          .customerName(bookingRequest.getCustomerName())
                          .customerSurname(bookingRequest.getCustomerSurname())
                          .numberOfPax(bookingRequest.getNumberOfPax())
                          .hotel(bookingRequest.getHotel()).build();
        return  booking;


    }
}

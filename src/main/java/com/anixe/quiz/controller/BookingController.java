package com.anixe.quiz.controller;

import com.anixe.quiz.domain.Booking;
import com.anixe.quiz.request.BookingRequest;
import com.anixe.quiz.request.BookingUpdatePrice;
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
public class BookingController extends AbstractController{

    private static final Logger log = LoggerFactory.getLogger(BookingController.class);
    private static final String HOTEL_ID = "Hotel Id " ;
    private static final String NOT_EXISTED = " is not existed" ;
    private static final String BOOKING_ID = " Booking Id" ;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/findByHotelId/{id}")
    public ResponseEntity<Object> getBookingByHotelId(@PathVariable Integer id) {

        log.info(" Find Booking By HotelId :  {} " , id);
        List<Booking> bookings = bookingService.findByHotelId(id);
        if(bookings == null || bookings.isEmpty()){
            return returnError(HttpStatus.BAD_REQUEST,HOTEL_ID + id + NOT_EXISTED);
        }

        return returnSuccess(bookingService.findByHotelId(id));

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Integer id) {

        log.info(" Find Booking By BookingId : {} " , id);

        Optional<Booking> booking = bookingService.findByBookingId(id);
        if (!booking.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(booking.get());
    }

    @PostMapping("/create")
    public ResponseEntity<Booking> create(@Valid @RequestBody BookingRequest bookingRequest,
                                    BindingResult bindingResult) {
        log.info(" Booking Create Request {}"  , bookingRequest);

        if (bindingResult.hasErrors()) {
            return  returnError(HttpStatus.BAD_REQUEST,bindingResult.getAllErrors().toString());
          }

        return ResponseEntity.ok(bookingService.save(createBookingObject(bookingRequest)));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Booking> update(@PathVariable Integer id, @Valid @RequestBody BookingRequest bookingRequest,
                                    BindingResult bindingResult) {

        log.info("booking update request : {}"  + "with booking id : {}" ,bookingRequest, id);

        if (bindingResult.hasErrors()) {
            return returnError(HttpStatus.BAD_REQUEST,bindingResult.getAllErrors().toString());

        }

        if (!bookingService.findByBookingId(id).isPresent()) {
            log.error("Booking Id {} " , id + NOT_EXISTED);
            return returnError(HttpStatus.BAD_REQUEST,BOOKING_ID + id + NOT_EXISTED);
        }

        Booking booking = createBookingObject(bookingRequest);
        booking.setId(id);
        return returnSuccess(bookingService.save(booking));
    }

    @PutMapping("/updatePrice/{id}")
    public ResponseEntity<Booking> updatePrice(@PathVariable Integer id, @Valid @RequestBody BookingUpdatePrice bookingUpdatePrice,
                                    BindingResult bindingResult) {

        log.info("update price for booking Id : {} "  + " with the value {} " ,id, bookingUpdatePrice);

        if (bindingResult.hasErrors()) {
            return returnError(HttpStatus.BAD_REQUEST,bindingResult.getAllErrors().toString());

        }

        Optional<Booking> resultBooking = bookingService.findByBookingId(id);

        if (!resultBooking.isPresent()) {
            log.error(BOOKING_ID +  "  {} " , id + NOT_EXISTED);
            return returnError(HttpStatus.BAD_REQUEST,BOOKING_ID + id +  NOT_EXISTED);
        }

        Booking updateBooking = resultBooking.get();
        updateBooking.setCurrency(bookingUpdatePrice.getCurrency());
        updateBooking.setPriceAmount(bookingUpdatePrice.getPriceAmount());
        return  returnSuccess(bookingService.save(updateBooking));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        log.info(" Delete Booking Id {} " , id);

        Optional<Booking> deletedHotel = bookingService.findByBookingId(id);
        if (!deletedHotel.isPresent()) {
            return  ResponseEntity.badRequest().body(BOOKING_ID + id + NOT_EXISTED);
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

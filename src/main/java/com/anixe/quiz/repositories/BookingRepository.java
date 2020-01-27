package com.anixe.quiz.repositories;

import com.anixe.quiz.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking,Integer> {

    List<Booking> findByHotelId(Integer id);


}

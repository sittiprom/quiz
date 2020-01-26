package com.anixe.quiz.repositories;

import com.anixe.quiz.domain.Hotel;
import com.anixe.quiz.domain.HotelResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface HotelRepository extends JpaRepository<Hotel,Integer> {

    /*@Query("select NEW com.anixe.quiz.domain.HotelResponse(h.id,h.name,h.address,h.starRating" +
            ") FROM Hotel h JOIN h.bookings b where b.customerSurname = :surname")
    public Set<HotelResponse> findByCustomerSurname(@Param("surname") String surname);*/

}


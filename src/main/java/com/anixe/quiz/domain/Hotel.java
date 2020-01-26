package com.anixe.quiz.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hotel")
    private  Integer id ;
    private  String name ;
    private  String address;
    private BigDecimal starRating ;

    @OneToMany(mappedBy="hotel")
    @JsonIgnore
    private List<Booking> bookings;


}

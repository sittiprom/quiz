package com.anixe.quiz.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Hotel {

    @Id
    @GeneratedValue
    @Column(name = "id_hotel")
    private  Integer id ;
    private  String name ;
    private  String address;
    private BigDecimal starRating ;

    @OneToMany(mappedBy="hotel")
    private List<Booking> bookings;


}

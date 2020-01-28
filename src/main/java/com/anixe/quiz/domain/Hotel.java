package com.anixe.quiz.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Booking> bookings;


}

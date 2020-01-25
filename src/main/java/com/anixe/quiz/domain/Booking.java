package com.anixe.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Booking {

    @Id
    @GeneratedValue
    private Long id;
    private String customerName;
    private String customerSurname;
    private Integer numberOfPax;
    private String currency;
    private BigDecimal priceAmount;

    @ManyToOne
    @JoinColumn(name="id_hotel")
    private Hotel hotel;


}

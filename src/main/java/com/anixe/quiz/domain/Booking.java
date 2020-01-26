package com.anixe.quiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String customerName;
    private String customerSurname;
    private Integer numberOfPax;

    @JsonIgnore
    private String currency;
    @JsonIgnore
    private BigDecimal priceAmount;

    @ManyToOne(fetch = FetchType.LAZY ,optional = false)
    @JoinColumn(name="id_hotel" , nullable = false)
    @JsonIgnore
    @NotNull
    private Hotel hotel;



}

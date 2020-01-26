package com.anixe.quiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String customerName;
    private String customerSurname;
    private Integer numberOfPax;
    private String currency;
    private BigDecimal priceAmount;

    @ManyToOne(fetch = FetchType.LAZY ,optional = false)
    @JoinColumn(name="id_hotel" , nullable = false)
    @JsonIgnore
    @NotNull
    private Hotel hotel;



}

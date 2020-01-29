package com.anixe.quiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String currency ;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal priceAmount ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_hotel")
    @JsonIgnore
    @NotNull
    private Hotel hotel;

}

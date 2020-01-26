package com.anixe.quiz.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotelRequest {
    private  Integer id ;
    private  String name ;
    private  String address;
    private BigDecimal starRating ;
}

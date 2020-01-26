package com.anixe.quiz.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotelResponse {

    private  Integer id ;
    private  String name ;
    private  String address;
    private BigDecimal starRating ;

}

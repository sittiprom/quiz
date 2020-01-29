package com.anixe.quiz.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BookingTotalAmount {
    private Integer hotelId ;
    private String hotelName ;
    Map<String,BigDecimal> sumPrice ;
}

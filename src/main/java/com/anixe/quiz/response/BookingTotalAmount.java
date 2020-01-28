package com.anixe.quiz.response;

import lombok.*;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BookingTotalAmount {
    private Integer hotelId ;
    private String hotelName ;
    private BigDecimal sumPrice ;
    @Builder.Default
    private String currency = "Euro";
}

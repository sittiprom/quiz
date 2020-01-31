package com.anixe.quiz.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotelRequest {
    private  Integer id ;

    @NotBlank
    private  String name ;

    @NotBlank
    private  String address;

    @DecimalMin(value = "0.5" , message = "The start rating value should greater than or equal to 0.5")
    private BigDecimal starRating ;
}

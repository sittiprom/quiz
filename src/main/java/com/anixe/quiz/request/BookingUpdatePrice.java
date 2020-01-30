package com.anixe.quiz.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingUpdatePrice {

    private Integer id;

    @NotNull
    @DecimalMin(value = "1.0" , message = "The price value should greater than or equal to 1.0")
    private BigDecimal priceAmount ;

    @NotBlank
    private String currency ;
}

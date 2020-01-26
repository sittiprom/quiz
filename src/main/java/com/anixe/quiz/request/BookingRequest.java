package com.anixe.quiz.request;


import com.anixe.quiz.domain.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingRequest {

    private Integer id;
    @NotBlank
    private String customerName;
    @NotBlank
    private String customerSurname;

    @Min(1)
    private Integer numberOfPax;

    @NotNull
    private Hotel hotel;


}

package com.anixe.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingResponse {

    private String customerName;
    private String customerSurname;
    private Integer numberOfPax;
}

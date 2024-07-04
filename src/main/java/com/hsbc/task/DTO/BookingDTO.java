package com.hsbc.task.DTO;

import com.hsbc.task.Model.Flight;
import com.hsbc.task.Model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class BookingDTO {
    private LocalDateTime bookingDate;
    private Integer bookedSeats;
    private Double bookingPrice;
    private String name;
    private String surname;
    private String email;
    private String passportNumber;
    private User user;
    private Flight flight;
}

package com.hsbc.task.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FlightDTO {
    private Long flightId;
    private String departureCity;
    private Date departureDate;
    private String arrivalCity;
    private Date arrivalDate;
    private Double flightPrice;
    private Integer seatsAvailable;
    private Double totalPrice;
}

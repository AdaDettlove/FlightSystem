package com.hsbc.task.Utils;

import com.hsbc.task.DTO.FlightDTO;
import com.hsbc.task.Model.Flight;

public class FlightMapper {

    public static Flight mapToFlight(FlightDTO flightDTO){
        Flight flight = Flight.builder()
                .flightId(flightDTO.getFlightId())
                .departureDate(flightDTO.getDepartureDate())
                .departureCity(flightDTO.getDepartureCity())
                .arrivalCity(flightDTO.getArrivalCity())
                .arrivalDate(flightDTO.getArrivalDate())
                .seatsAvailable(flightDTO.getSeatsAvailable())
                .flightPrice(flightDTO.getFlightPrice())
                .build();
        return flight;
    }

    public static FlightDTO mapToFlightDto(Flight flight, int passengers) {
        FlightDTO flightDTO = FlightDTO.builder()
                .flightId(flight.getFlightId())
                .departureCity(flight.getDepartureCity())
                .arrivalDate(flight.getArrivalDate())
                .departureDate(flight.getDepartureDate())
                .arrivalCity(flight.getArrivalCity())
                .flightPrice(flight.getFlightPrice())
                .seatsAvailable(flight.getSeatsAvailable())
                .totalPrice(flight.getFlightPrice()*passengers)
                .build();
        return flightDTO;
    }
}

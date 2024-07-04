package com.hsbc.task.Service;

import com.hsbc.task.DTO.FlightDTO;

import java.util.List;
import java.util.Set;

public interface FlightService {
    FlightDTO findFlightById(long id, int passengers);
    List<FlightDTO> findFlights(String city, int passengers);
    Set<String> findAllDepartureCities();
}

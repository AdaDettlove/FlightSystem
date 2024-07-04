package com.hsbc.task.Service;

import com.hsbc.task.DTO.FlightDTO;
import com.hsbc.task.Model.Flight;
import com.hsbc.task.Repo.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.hsbc.task.Utils.FlightMapper.mapToFlightDto;

@Service
public class FlightServiceImpl implements FlightService{
    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Set<String> findAllDepartureCities(){
        List<Flight> flights = flightRepository.findAll();
        return flights.stream().map(Flight::getDepartureCity).collect(Collectors.toSet());
    }

    @Override
    public FlightDTO findFlightById(long id, int passengers) {
        Flight flight = flightRepository.findByFlightId(id);
        return mapToFlightDto(flight, passengers);
    }

    @Override
    public List<FlightDTO> findFlights(String city, int passengers) {
        List<Flight> flightsDepartingList =
                flightRepository.findByDepartureCityAndSeatsAvailableGreaterThanEqual(city, passengers);
        return flightsDepartingList.stream()
                              .map((flight) -> mapToFlightDto(flight, passengers))
                              .collect(Collectors.toList());
    }
}

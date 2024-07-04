package com.hsbc.task.Repo;

import com.hsbc.task.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureCityAndSeatsAvailableGreaterThanEqual(String city, int seats);
    Flight findByFlightId(Long flightId);
}
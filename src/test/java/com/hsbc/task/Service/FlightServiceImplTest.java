package com.hsbc.task.Service;

import com.hsbc.task.DTO.FlightDTO;
import com.hsbc.task.Model.Flight;
import com.hsbc.task.Repo.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {
    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    @Test
    public void testFindFlightById() {
        // given
        Flight mockFlight = getMockFlights().get(1);
        when(flightRepository.findByFlightId(123L)).thenReturn(mockFlight);

        // when
        FlightDTO result = flightService.findFlightById(123L, 2);

        // then
        assertNotNull(result);
    }
    @Test
    public void testFindAllDepartureCities() {
        // given
        List<Flight> mockFlights = getMockFlights();

        when(flightRepository.findAll()).thenReturn(mockFlights);

        // when
        Set<String> result = flightService.findAllDepartureCities();

        // then
        assertEquals(2, result.size());
        assertTrue(result.contains("Kraków"));
        assertTrue(result.contains("Katowice"));
    }

    @Test
    public void testFindFlights() {
        //given
         List<Flight> mockFlights = getMockFlights();
        when(flightRepository.findByDepartureCityAndSeatsAvailableGreaterThanEqual("Kraków", 2))
                .thenReturn(mockFlights);

        //when
        List<FlightDTO> result = flightService.findFlights("Kraków", 2);

        // then
        assertEquals(2, result.size());
    }

    private List<Flight> getMockFlights(){
        Flight mockFlight1 = new Flight(1L,
            "Kraków",
            Date.valueOf("2022-07-11"),
            "Drezno",
            Date.valueOf("2022-07-11"),
            30.00,
            15,
            Collections.emptyList());
        Flight mockFlight2 = new Flight(2L,
                "Katowice",
                Date.valueOf("2022-07-11"),
                "Drezno",
                Date.valueOf("2022-07-11"),
                30.00,
                15,
                Collections.emptyList());
        return List.of(mockFlight1, mockFlight2);
    }
}
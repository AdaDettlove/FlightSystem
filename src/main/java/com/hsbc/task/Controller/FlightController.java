package com.hsbc.task.Controller;

import com.hsbc.task.DTO.FlightDTO;
import com.hsbc.task.Service.FlightService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Controller
public class FlightController {

    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public String getFlights(HttpSession session,
                             Model model){
        if(session.getAttribute("username") == null) {
            return "redirect:/";
        }
        Set<String> departures = flightService.findAllDepartureCities();
        model.addAttribute("departures", departures);
        return "flights";
    }

    @PostMapping("/flights")
    public String getFlightsFrom(HttpSession session,
                                 Model model,
                                 HttpServletRequest request,
                                 @RequestParam(value = "availableSeats") int seats){
        if(session.getAttribute("username") == null) {
            return "redirect:/";
        }
        System.out.println(request.getParameter("departureCity"));
        String departureCity = Optional.ofNullable(request.getParameter("departureCity")).orElse("Kraków");
        List<FlightDTO> departingFlightsList = flightService.findFlights(departureCity,seats);
        model.addAttribute("flights", departingFlightsList);
        model.addAttribute("city", departureCity);
        model.addAttribute("passenger", seats);
        return "flights-table";
    }
}

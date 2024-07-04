package com.hsbc.task.Controller;

import com.hsbc.task.DTO.BookingDTO;
import com.hsbc.task.DTO.FlightDTO;
import com.hsbc.task.Model.Booking;
import com.hsbc.task.Model.User;
import com.hsbc.task.Service.BookingService;
import com.hsbc.task.Service.FlightService;
import com.hsbc.task.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class BookingController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookingService bookingService;

    @GetMapping("/bookings")
    public String goToBookingPage(HttpSession session,
                                  Model model,
                                  @RequestParam(value = "passenger") Integer seats,
                                  HttpServletRequest req) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/flights";
        }
        int id = Integer.parseInt(req.getParameter("flightId"));
        FlightDTO flight = flightService.findFlightById(id, seats);
        model.addAttribute("flight", flight);
        model.addAttribute("passenger", seats);
        model.addAttribute("booking", new Booking());

        User user = userService.getUserByUsername(username);
        model.addAttribute("flightId", id);
        model.addAttribute("userId", user.getUserId());

        return "booking-page";
    }

    @PostMapping("/bookings/{passenger}/flights/{flightId}/users/{userId}")
    public String bookFlight(@ModelAttribute BookingDTO booking,
                             HttpSession session,
                             @PathVariable(value = "passenger") Integer passengers,
                             @PathVariable(value = "flightId") Long flightId,
                             @PathVariable(value = "userId") Long userId) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/";
        }

        bookingService.addBooking(userId, flightId, passengers, booking);

        return "booking-success";
    }

    @GetMapping("/bookingHistory")
    public String goToBookingHistory(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/flights";
        }

        List<BookingDTO> bookings = bookingService.getBookingHistoryOfUser(username);
        model.addAttribute("userBookingList", bookings);
        System.out.println(bookings);
        return "booking-history";
    }
}
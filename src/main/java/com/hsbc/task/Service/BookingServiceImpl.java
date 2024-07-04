package com.hsbc.task.Service;

import com.hsbc.task.DTO.BookingDTO;
import com.hsbc.task.Model.Booking;
import com.hsbc.task.Model.Flight;
import com.hsbc.task.Model.User;
import com.hsbc.task.Repo.BookingRepository;
import com.hsbc.task.Repo.FlightRepository;
import com.hsbc.task.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.hsbc.task.Utils.BookingMapper.mapToBooking;
import static com.hsbc.task.Utils.BookingMapper.mapToBookingDTO;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<BookingDTO> getBookingHistoryOfUser(String username){
        List<Booking> list= bookingRepository.findAll();
        return list.stream()
                .filter(booking -> Objects.equals(booking.getUserDetails().getUsername(), username))
                .map(booking -> mapToBookingDTO(booking))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addBooking(Long userId, Long flightId, Integer passenger, BookingDTO bookingDto){
        User user = userRepository.findByUserId(userId);
        Flight flight = flightRepository.findByFlightId(flightId);
        Booking booking = mapToBooking(bookingDto);
        booking.setBookingDate(LocalDateTime.now());
        booking.setPassengerNumber(passenger);
        booking.setBookingPrice(flight.getFlightPrice()*passenger);
                booking.setUserDetails(user);
                booking.setFlightDetails(flight);
        Integer seatsAvailableAfterBooking = flight.getSeatsAvailable()-passenger;

        flight.setSeatsAvailable(seatsAvailableAfterBooking);
        bookingRepository.save(booking);
        return true;
    }
}

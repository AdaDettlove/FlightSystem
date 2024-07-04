package com.hsbc.task.Service;

import com.hsbc.task.DTO.BookingDTO;
import com.hsbc.task.Model.Booking;

import java.util.List;

public interface BookingService {
    boolean addBooking(Long userId, Long flightId, Integer passenger,BookingDTO bookingDto);
    List<BookingDTO> getBookingHistoryOfUser(String username);

    }

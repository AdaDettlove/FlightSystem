package com.hsbc.task.Utils;

import com.hsbc.task.DTO.BookingDTO;
import com.hsbc.task.Model.Booking;

public class BookingMapper {
    public static Booking mapToBooking(BookingDTO bookingDto){
        Booking booking = Booking.builder()
                .bookingDate(bookingDto.getBookingDate())
                .bookingPrice(bookingDto.getBookingPrice())
                .surname(bookingDto.getSurname())
                .name(bookingDto.getName())
                .email(bookingDto.getEmail())
                .passengerNumber(bookingDto.getBookedSeats())
                .passportNumber(bookingDto.getPassportNumber())
                .build();
        return booking;
    }
    public static BookingDTO mapToBookingDTO(Booking booking){
        BookingDTO bookingDto = BookingDTO.builder()
                .bookingDate(booking.getBookingDate())
                .bookedSeats(booking.getPassengerNumber())
                .bookingPrice(booking.getBookingPrice())
                .email(booking.getEmail())
                .flight(booking.getFlightDetails())
                .name(booking.getName())
                .surname(booking.getSurname())
                .passportNumber(booking.getPassportNumber())
                .user(booking.getUserDetails())
                .build();
        return bookingDto;
    }
}

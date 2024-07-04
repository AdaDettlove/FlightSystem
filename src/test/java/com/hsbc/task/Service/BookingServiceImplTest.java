package com.hsbc.task.Service;

import com.hsbc.task.DTO.BookingDTO;
import com.hsbc.task.Model.Booking;
import com.hsbc.task.Model.Flight;
import com.hsbc.task.Model.User;
import com.hsbc.task.Repo.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    void testGetBookingHistoryOfUser() {
        // given
        Flight mockFlight1 = new Flight(1L,
                "Kraków",
                Date.valueOf("2022-07-11"),
                "Drezno",
                Date.valueOf("2022-07-11"),
                30.00,
                15,
                Collections.emptyList());
        User mockUser = new User();
        mockUser.setUsername("testUser");
        mockUser.setPassword("testPassword");
        Booking booking1 = new Booking(1L,LocalDateTime.now(), 2,20.00,"Adam", "Nowak", "aa@wp.pl", "AZ1234567", mockFlight1,  mockUser);
        List<Booking> mockBookings = List.of(booking1);
        when(bookingRepository.findAll()).thenReturn(mockBookings);

        // when
        List<BookingDTO> result = bookingService.getBookingHistoryOfUser("testUser");

        // then
        assertEquals(1, result.size());
    }
}


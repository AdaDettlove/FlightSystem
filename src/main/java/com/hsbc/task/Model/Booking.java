package com.hsbc.task.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id")
    private Long bookingId;

    private LocalDateTime bookingDate;
    private Integer passengerNumber;
    private Double bookingPrice;

    private String name;

    private String surname;


    private String email;

    private String passportNumber;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flightDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userDetails;

    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId;
    }

}

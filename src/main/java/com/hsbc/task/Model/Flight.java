package com.hsbc.task.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="flight_id")
    private Long flightId;
    private String departureCity;
    private Date departureDate;
    private String arrivalCity;
    private Date arrivalDate;
    private Double flightPrice;
    private Integer seatsAvailable;
    @OneToMany(mappedBy = "flightDetails", cascade=CascadeType.PERSIST)
    private List<Booking> bookingsForPlanes = new ArrayList<>();
}

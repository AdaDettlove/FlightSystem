package com.hsbc.task.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "user_id")
    private Long userId;
    private String username;
    private String password;
    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.PERSIST)
    private List<Booking> usersBookings = new ArrayList<>();
}

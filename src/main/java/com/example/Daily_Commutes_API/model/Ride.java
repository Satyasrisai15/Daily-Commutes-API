package com.example.Daily_Commutes_API.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "rides")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(name = "driver_id") 
    private Driver driver;

    private LocalDate rideDate;

    @Enumerated(EnumType.STRING)
    private RideStatus status;
}

package com.example.Daily_Commutes_API.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "drivers")
@NoArgsConstructor
public class Driver {

    @Id
    private Long id; 

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId 
    @JoinColumn(name = "id")
    private User user;

    private String licenseNumber;

    private String vehicleDetails;

    private String serviceArea;

    @OneToMany(mappedBy = "driver")
    private List<Ride> assignedRides;

    public Driver(User user) {
        this.user = user;
    }
}

package com.example.Daily_Commutes_API.service;

import com.example.Daily_Commutes_API.model.*;
import com.example.Daily_Commutes_API.repository.DriverRepository;
import com.example.Daily_Commutes_API.repository.RideRepository;
import com.example.Daily_Commutes_API.repository.SubscriptionRepository;
import com.example.Daily_Commutes_API.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AppService {

    private final DriverRepository driverRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final RideRepository rideRepository;
    private final UserRepository userRepository;

    public void updateDriverServiceArea(Long userId, String serviceArea) {
        // For simplicity, we assume a Driver profile exists if role is DRIVER
        Driver driver = driverRepository.findById(userId)
                .orElse(new Driver(userRepository.findById(userId).get()));
        driver.setServiceArea(serviceArea);
        driverRepository.save(driver);
    }

    public void createSubscription(Long commuterId, Subscription newSubscription) {
        User commuter = userRepository.findById(commuterId).orElseThrow();
        newSubscription.setUser(commuter);

        // V1 RIDE MATCHING LOGIC (SUPER SIMPLE)
        // Find the first driver available. In a real app, this would be complex.
        Driver matchedDriver = driverRepository.findAll().stream().findFirst().orElseThrow();

        subscriptionRepository.save(newSubscription);

        // Generate individual rides for the subscription period
        int days = newSubscription.getType() == SubscriptionType.WEEKLY ? 7 : 30;
        for (int i = 0; i < days; i++) {
            Ride ride = new Ride();
            ride.setSubscription(newSubscription);
            ride.setDriver(matchedDriver);
            ride.setRideDate(newSubscription.getStartDate().plusDays(i));
            ride.setStatus(RideStatus.SCHEDULED);
            rideRepository.save(ride);
        }
    }
}

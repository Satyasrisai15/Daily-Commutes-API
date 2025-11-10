package com.example.Daily_Commutes_API.repository;

import com.example.Daily_Commutes_API.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}

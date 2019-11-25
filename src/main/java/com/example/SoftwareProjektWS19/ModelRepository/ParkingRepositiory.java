package com.example.SoftwareProjektWS19.ModelRepository;

import com.example.SoftwareProjektWS19.Model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepositiory extends JpaRepository<Parking, Long> {
}

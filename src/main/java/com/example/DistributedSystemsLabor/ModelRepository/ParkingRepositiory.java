package com.example.DistributedSystemsLabor.ModelRepository;

import com.example.DistributedSystemsLabor.Model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepositiory extends JpaRepository<Parking, Long> {
}

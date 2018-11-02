package com.example.DistributedSystemsLabor.ModelRepository;

import com.example.DistributedSystemsLabor.Model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
}

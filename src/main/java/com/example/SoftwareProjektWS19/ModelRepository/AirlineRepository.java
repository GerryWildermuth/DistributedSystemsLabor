package com.example.SoftwareProjektWS19.ModelRepository;

import com.example.SoftwareProjektWS19.Model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
}

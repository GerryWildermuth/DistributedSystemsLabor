package com.example.SoftwareProjektWS19.ModelRepository;

import com.example.SoftwareProjektWS19.Model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long>
{
}

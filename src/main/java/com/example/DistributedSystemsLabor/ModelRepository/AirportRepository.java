package com.example.DistributedSystemsLabor.ModelRepository;

import com.example.DistributedSystemsLabor.Model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long>
{
}

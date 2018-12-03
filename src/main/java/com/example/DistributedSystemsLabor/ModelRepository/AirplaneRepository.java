package com.example.DistributedSystemsLabor.ModelRepository;

import com.example.DistributedSystemsLabor.Enums.Status;
import com.example.DistributedSystemsLabor.Model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    List<Airplane> findByStatus(Status s);
}

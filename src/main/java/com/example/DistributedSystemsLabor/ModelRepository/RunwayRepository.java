package com.example.DistributedSystemsLabor.ModelRepository;

import com.example.DistributedSystemsLabor.Enums.Status;
import com.example.DistributedSystemsLabor.Model.Runway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RunwayRepository extends JpaRepository<Runway, Long> {
    List<Runway> findByLocked(boolean Locked);
}

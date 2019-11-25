package com.example.SoftwareProjektWS19.ModelRepository;

import com.example.SoftwareProjektWS19.Model.Runway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RunwayRepository extends JpaRepository<Runway, Long> {
    List<Runway> findByLocked(boolean Locked);
}

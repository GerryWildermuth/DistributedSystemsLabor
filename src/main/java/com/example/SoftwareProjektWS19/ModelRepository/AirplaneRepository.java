package com.example.SoftwareProjektWS19.ModelRepository;

import com.example.SoftwareProjektWS19.Enums.Status;
import com.example.SoftwareProjektWS19.Model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    List<Airplane> findByStatus(Status s);
}

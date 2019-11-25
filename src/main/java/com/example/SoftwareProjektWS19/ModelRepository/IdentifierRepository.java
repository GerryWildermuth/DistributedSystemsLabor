package com.example.SoftwareProjektWS19.ModelRepository;

import com.example.SoftwareProjektWS19.Model.Identifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentifierRepository extends JpaRepository<Identifier, Long> {
}

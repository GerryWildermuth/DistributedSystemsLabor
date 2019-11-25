package com.example.DistributedSystemsLabor.ModelRepository;

import com.example.DistributedSystemsLabor.Model.Identifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentifierRepository extends JpaRepository<Identifier, Long> {
}

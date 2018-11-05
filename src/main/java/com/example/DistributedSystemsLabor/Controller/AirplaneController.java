package com.example.DistributedSystemsLabor.Controller;

import com.example.DistributedSystemsLabor.Enums.Status;
import com.example.DistributedSystemsLabor.Model.Airplane;
import com.example.DistributedSystemsLabor.Model.Identifier;
import com.example.DistributedSystemsLabor.ModelRepository.AirplaneRepository;
import com.example.DistributedSystemsLabor.ModelRepository.IdentifierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@RestController()
public class AirplaneController {
    public AirplaneController()
    {
    }

    @Autowired
    public AirplaneRepository airplaneRepository;

    @Autowired
    public IdentifierRepository identifierRepository;


    @GetMapping("/airplanes")
    public List<Airplane> AllAirplanes(){
        List<Airplane> airplanes = airplaneRepository.findAll();
        return airplanes;
    }

    @GetMapping("/airplane/{id}")
    public Airplane AirplaneById(@PathVariable Long id){
        return airplaneRepository.getOne(id);
    }

    @PostMapping("/airplane")
    public void CreateAirplane(@RequestBody Airplane airplane,String IdentifierName)
    {
        airplaneRepository.save(airplane);
        identifierRepository.save(new Identifier(IdentifierName,airplane));
    }

    @GetMapping("/sample")
    public void CreateSample()
    {
        Airplane airplane = new Airplane();
        airplane.setStatus(Status.Flying);
        airplaneRepository.save(airplane);
        Identifier identifier = new Identifier("test",airplane);
        identifierRepository.save(identifier);
        airplane.setIdentifier(identifier);
        airplaneRepository.save(airplane);
    }

    @DeleteMapping("/airplane/{id}")
    public void DeleteAirplane(@PathVariable Long id)
    {
        airplaneRepository.deleteById(id);
    }

    @GetMapping("/initialise")
    public void initialise()
    {
        Stream.of(
                new Airplane(Timestamp.valueOf(LocalDateTime.now()), new Identifier("TurkishAirlines1"), Status.Flying),
                new Airplane(Timestamp.valueOf(LocalDateTime.now()),new Identifier("TurkishAirlines2"), Status.Flying),
                new Airplane(Timestamp.valueOf(LocalDateTime.now()),new Identifier("TurkishAirlines3"), Status.Flying),
                new Airplane(Timestamp.valueOf(LocalDateTime.now()),new Identifier("TurkishAirlines4"), Status.Flying),
                new Airplane(Timestamp.valueOf(LocalDateTime.now()),new Identifier("TurkishAirlines5"), Status.Flying)
        ).forEach(airplaneRepository::save);
    }
}

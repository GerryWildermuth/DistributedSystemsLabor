package com.example.DistributedSystemsLabor.Controller;

import com.example.DistributedSystemsLabor.Enums.Status;
import com.example.DistributedSystemsLabor.Model.Airplane;
import com.example.DistributedSystemsLabor.Model.Identifier;
import com.example.DistributedSystemsLabor.ModelRepository.AirplaneRepository;
import com.example.DistributedSystemsLabor.ModelRepository.IdentifierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> CreateAirplane(@RequestBody Airplane airplane, String IdentifierName, BindingResult result)
    {
        if(result.hasErrors())
        {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<Airplane> airplaneResponseEntity = new ResponseEntity<>(airplaneRepository.save(airplane), HttpStatus.CREATED);
        identifierRepository.save(new Identifier(IdentifierName,airplane));
        return airplaneResponseEntity;
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
        Identifier first;
        Identifier second;
        Identifier third;
        Identifier fourth;
        Identifier fifth;

        Stream.of(
                first= new Identifier("TurkishAirlines1"),
                second = new Identifier("TurkishAirlines2"),
                third = new Identifier("TurkishAirlines3"),
                fourth = new Identifier("TurkishAirlines4"),
                fifth = new Identifier("TurkishAirlines5")
        ).forEach(identifierRepository::save);

        Stream.of(
                new Airplane(Timestamp.valueOf(LocalDateTime.now()),first, Status.Flying),
                new Airplane(Timestamp.valueOf(LocalDateTime.now()),second, Status.Flying),
                new Airplane(Timestamp.valueOf(LocalDateTime.now()),third, Status.Flying),
                new Airplane(Timestamp.valueOf(LocalDateTime.now()),fourth, Status.Flying),
                new Airplane(Timestamp.valueOf(LocalDateTime.now()),fifth, Status.Flying)
        ).forEach(airplaneRepository::save);
    }
}

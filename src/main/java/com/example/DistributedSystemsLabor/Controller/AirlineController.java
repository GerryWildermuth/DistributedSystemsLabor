package com.example.DistributedSystemsLabor.Controller;

import com.example.DistributedSystemsLabor.Model.Airline;
import com.example.DistributedSystemsLabor.Model.Airplane;
import com.example.DistributedSystemsLabor.ModelRepository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class AirlineController {

    public AirlineController() {
    }

    @Autowired
    public AirlineRepository airlineRepository;

    @GetMapping("/airlines")
    public List<Airline> AllAirlines(){
        List<Airline> airlines = airlineRepository.findAll();
        return airlines;
    }

    @GetMapping("/airline/{id}")
    public Airline AirlineById(@PathVariable Long id){
        return airlineRepository.getOne(id);
    }

    @PostMapping("/airline")
    public ResponseEntity<?> CreateAirline(@RequestBody Airline airline, BindingResult result)
    {
        if(result.hasErrors())
        {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(airlineRepository.save(airline), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/airline/{id}")
    public void DeleteAirline(@PathVariable Long id)
    {
        airlineRepository.deleteById(id);
    }
}

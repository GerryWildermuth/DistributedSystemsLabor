package com.example.DistributedSystemsLabor.Controller;

import com.example.DistributedSystemsLabor.Model.Airline;
import com.example.DistributedSystemsLabor.ModelRepository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void CreateAirline(@RequestBody Airline airline)
    {
        airlineRepository.save(airline);
    }
    
    @DeleteMapping("/airline/{id}")
    public void DeleteAirline(@PathVariable Long id)
    {
        airlineRepository.deleteById(id);
    }
}

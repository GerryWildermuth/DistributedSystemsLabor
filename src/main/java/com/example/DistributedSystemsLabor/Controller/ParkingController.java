package com.example.DistributedSystemsLabor.Controller;

import com.example.DistributedSystemsLabor.Enums.Status;
import com.example.DistributedSystemsLabor.Model.Airplane;
import com.example.DistributedSystemsLabor.Model.Parking;
import com.example.DistributedSystemsLabor.Model.Runway;
import com.example.DistributedSystemsLabor.ModelRepository.AirplaneRepository;
import com.example.DistributedSystemsLabor.ModelRepository.ParkingRepositiory;
import com.example.DistributedSystemsLabor.ModelRepository.RunwayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

//Maximal 8
@RestController
public class ParkingController {
    public ParkingController() {
    }

    @Autowired
    public ParkingRepositiory parkingRepository;

    @Autowired
    public RunwayRepository runwayRepository;

    @Autowired
    public AirplaneRepository airplaneRepository;

    @GetMapping("/parkings")
    public List<Parking> AllParkings(){
        List<Parking> parkings = parkingRepository.findAll();
        return parkings;
    }

    @GetMapping("/parking/{id}")
    public Parking ParkingById(@PathVariable Long id){
        return parkingRepository.getOne(id);
    }

    @PostMapping("/parking")
    public void CreateParking(@RequestBody Parking parking)
    {
        if(parking.getId()<8)
        parkingRepository.save(parking);
    }

    @GetMapping("/parking/park")
    public void ParkingPlane(@PathVariable Long id)
    {
        Airplane airplane = airplaneRepository.getOne(id);
        if(airplane.getStatus()== Status.Landed)
        {
            if(airplane.getParking()!=null)
            {
                airplane.setStatus(Status.Parked);
            }
        }
    }

    @GetMapping("/parking/unlock")
    public void ParkingUnlock(@PathVariable Long id)
    {
        Airplane airplane = airplaneRepository.getOne(id);
        Parking parking = airplane.getParking();
        if(airplane.getStatus()== Status.Flying || airplane.getStatus()== Status.Other)
            if(parking!=null)
            {
                airplane.setParking(null);
                parking.setLocked(false);
            }
    }

    @DeleteMapping("/parking/{id}")
    public void DeleteParking(@PathVariable Long id)
    {
        parkingRepository.deleteById(id);
    }
}

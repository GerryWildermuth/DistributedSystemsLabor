package com.example.DistributedSystemsLabor.Controller;

import com.example.DistributedSystemsLabor.Enums.Status;
import com.example.DistributedSystemsLabor.Model.Airplane;
import com.example.DistributedSystemsLabor.Model.Parking;
import com.example.DistributedSystemsLabor.Model.Runway;
import com.example.DistributedSystemsLabor.ModelRepository.AirplaneRepository;
import com.example.DistributedSystemsLabor.ModelRepository.ParkingRepositiory;
import com.example.DistributedSystemsLabor.ModelRepository.RunwayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
        return parkingRepository.findAll();
    }

    @GetMapping("/parking/{id}")
    public Parking ParkingById(@PathVariable Long id){
        return parkingRepository.getOne(id);
    }

    @PostMapping("/parking")
    public ResponseEntity<?> CreateParking(@RequestBody Parking parking, BindingResult result)
    {
        if(result.hasErrors())
        {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        if(parking.getId()<8)
                return new ResponseEntity<>(parkingRepository.save(parking), HttpStatus.CREATED);
        return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/parking/park")
    public boolean ParkingPlane(@RequestParam("Slot") Long parkingId, @RequestParam("Airplane") Long airplaneId)
    {
        Airplane airplane = airplaneRepository.getOne(airplaneId);

        if(airplane == null){
            return false;
        }

        if(airplane.getStatus() == Status.Landed)
        {
            // Lock Slot
            Parking parking = parkingRepository.getOne(parkingId);

            if(parking == null){
                return false;
            }

            parking.setLocked(true);
            parkingRepository.save(parking);

            // Park Plane
            airplane.setStatus(Status.Parked);
            airplane.setParking(parking);

            // Free runway
            Runway runway = airplane.getRunway();
            airplane.setRunway(null);
            runway.setLocked(false);
            runwayRepository.save(runway);

            airplaneRepository.save(airplane);
            return true;
        }
        return false;
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

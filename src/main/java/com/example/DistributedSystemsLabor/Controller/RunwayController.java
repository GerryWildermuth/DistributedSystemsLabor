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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

//Maximal 4
@RestController
public class RunwayController {

    public RunwayController() {
    }

    @Autowired
    public RunwayRepository runwayRepository;

    @Autowired
    public ParkingRepositiory parkingRepository;

    @Autowired
    public AirplaneRepository airplaneRepository;

    @GetMapping("/runways")
    public List<Runway> AllRunways(){
        List<Runway> runways = runwayRepository.findAll();
        return runways;
    }

    @GetMapping("/runway/{id}")
    public Runway RunwayById(@PathVariable Long id){
        return runwayRepository.getOne(id);
    }

    @PostMapping("/runway")
    public void CreateRunway(@RequestBody Runway runway)
    {
        if(runway.getId()<4)
        runwayRepository.save(runway);
    }

    @GetMapping("runway/landing")
    public void LandingPlane(@PathVariable Long id)
    {
        Airplane airplane = airplaneRepository.getOne(id);
        if(airplane.getStatus()== Status.Landing)
        {
            Collection<Parking> parkings = parkingRepository.findAll();
            Collection<Runway> runways = runwayRepository.findAll();
            for(Parking parking: parkings)
            {
                if(parking.isLocked()==false)
                {
                    for(Runway runway: runways)
                    {
                        if(runway.isLocked()==false)
                        {
                            runway.setLocked(true);
                            parking.setLocked(true);
                            airplane.setRunway(runway);
                            airplane.setParking(parking);
                            airplane.setRealArrivalTime(Timestamp.valueOf(LocalDateTime.now()));
                            airplane.setStatus(Status.Landed);
                            runwayRepository.save(runway);
                            parkingRepository.save(parking);
                            airplaneRepository.save(airplane);
                        }
                    }
                }
            }
        }
    }

    @GetMapping("/runway/unlock")
    public void RunwayUnlock(@PathVariable Long id)
    {
        Airplane airplane = airplaneRepository.getOne(id);
        Runway runway = airplane.getRunway();
        if(airplane.getStatus()== Status.Flying || airplane.getStatus()== Status.Parked)
        {
            if(runway!=null)
            {
                airplane.setRunway(null);
                runway.setLocked(false);
            }
        }
    }

    @DeleteMapping("/runway/{id}")
    public void DeleteRunway(@PathVariable Long id)
    {
        runwayRepository.deleteById(id);
    }
}

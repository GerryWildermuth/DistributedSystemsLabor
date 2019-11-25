package com.example.SoftwareProjektWS19.Controller;

import com.example.SoftwareProjektWS19.Enums.Status;
import com.example.SoftwareProjektWS19.Model.Airplane;
import com.example.SoftwareProjektWS19.Model.Runway;
import com.example.SoftwareProjektWS19.ModelRepository.AirplaneRepository;
import com.example.SoftwareProjektWS19.ModelRepository.ParkingRepositiory;
import com.example.SoftwareProjektWS19.ModelRepository.RunwayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> CreateRunway(@RequestBody Runway runway, BindingResult result)
    {
        if(result.hasErrors())
        {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        if(runway.getId()<4)
            return new ResponseEntity<>(runwayRepository.save(runway), HttpStatus.CREATED);
        return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("runway/landing")
    public boolean LandingPlane(@RequestParam("Airplane") Long airplaneId, @RequestParam("Runway") Long runwayId)
    {
        Runway runway = runwayRepository.getOne(runwayId);

        if(runway.getLocked()){
            return false;
        }

        Airplane airplane = airplaneRepository.getOne(airplaneId);

        if(airplane.getStatus() == Status.Landing && airplane.getRunway() == null)
        {
            runway.setLocked(true);
            runwayRepository.save(runway);

            airplane.setRunway(runway);
            airplaneRepository.save(airplane);

            new Thread( () -> SimulateLanding(airplane)).start();
            return true;
        }

        return false;
    }

    @GetMapping("/runway/unlock")
    public boolean RunwayUnlock(@RequestParam("Airplane") Long airplaneId)
    {
        Airplane airplane = airplaneRepository.getOne(airplaneId);
        Runway runway = airplane.getRunway();
        if(airplane.getStatus()== Status.Parked)
        {
            if(runway!=null)
            {
                airplane.setRunway(null);
                runway.setLocked(false);
                runwayRepository.save(runway);
                airplaneRepository.save(airplane);
                return true;
            }
        }
        return false;
    }

    @DeleteMapping("/runway/{id}")
    public void DeleteRunway(@PathVariable Long id)
    {
        runwayRepository.deleteById(id);
    }

    private void SimulateLanding(Airplane airplane){
        long realArrivel = airplane.getEstimatedArrivalTime().getTime() + ((long)(Math.random() * 60000L) - 30000L);
        long timeDiff = realArrivel - System.currentTimeMillis();
        try {
            Thread.sleep(timeDiff);
        } catch(Exception e){
            System.out.println("Error while landing "+ airplane.getIdentifier().getName());
            System.out.println(e);
        }
        airplane.setStatus(Status.Landed);
        airplaneRepository.save(airplane);
    }
}

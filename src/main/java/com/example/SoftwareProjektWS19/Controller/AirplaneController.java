package com.example.SoftwareProjektWS19.Controller;

import com.example.SoftwareProjektWS19.Enums.Status;
import com.example.SoftwareProjektWS19.Model.Airplane;
import com.example.SoftwareProjektWS19.Model.Identifier;
import com.example.SoftwareProjektWS19.ModelRepository.AirplaneRepository;
import com.example.SoftwareProjektWS19.ModelRepository.IdentifierRepository;
import com.example.SoftwareProjektWS19.ModelRepository.RunwayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController()
public class AirplaneController {

    private boolean IsInit = false;

    public AirplaneController()
    {
    }

    @Autowired
    public AirplaneRepository airplaneRepository;

    @Autowired
    public IdentifierRepository identifierRepository;

    @Autowired
    public RunwayRepository runwayRepository;

    @GetMapping("/airplanes")
    public List<Airplane> AllAirplanes(@RequestParam(name="Status", defaultValue = "All") String status){
        List<Airplane> airplanes;

        switch(status){
            case "Flying":
                airplanes = airplaneRepository.findByStatus(Status.Flying);
                break;
            case "Landing":
                airplanes = airplaneRepository.findByStatus(Status.Landing);
                break;
            case "Landed":
                airplanes = airplaneRepository.findByStatus(Status.Landed);
                break;
            default:
                airplanes = airplaneRepository.findAll();
                break;
        }
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
        identifierRepository.save(new Identifier(IdentifierName));
        return airplaneResponseEntity;
    }

    @GetMapping("/airplane/update")
    public boolean UpdateAirplane(@RequestParam("Airplane") Long airplaneId, @RequestParam("RAT") Long rat){
        Airplane airplane = airplaneRepository.getOne(airplaneId);

        if(airplane != null){
            airplane.setRealArrivalTime(new Timestamp(rat));
            airplaneRepository.save(airplane);
            return true;
        }
        return false;
    }

    @GetMapping("/airplane/permitlanding")
    public boolean PermitLanding(@RequestParam Long id, @RequestParam Long EAT){
        Airplane airplane = airplaneRepository.getOne(id);
        if(airplane==null){
            return false;
        }
        airplane.setStatus(Status.Landing);
        airplane.setEstimatedArrivalTime(Timestamp.valueOf(LocalDateTime.now().plusMinutes(EAT)));
        airplaneRepository.save(airplane);

        return true;
    }

    @GetMapping("/sample")
    public void CreateSample()
    {
        Airplane airplane = new Airplane();
        airplane.setStatus(Status.Flying);
        airplaneRepository.save(airplane);
        Identifier identifier = new Identifier("test");
        identifierRepository.save(identifier);
        airplane.setIdentifier(identifier);
        airplaneRepository.save(airplane);
    }

    @DeleteMapping("/airplane/{id}")
    public void DeleteAirplane(@PathVariable Long id)
    {
        airplaneRepository.deleteById(id);
    }
}

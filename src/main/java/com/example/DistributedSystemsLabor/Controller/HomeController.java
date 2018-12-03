package com.example.DistributedSystemsLabor.Controller;

import com.example.DistributedSystemsLabor.Enums.Status;
import com.example.DistributedSystemsLabor.Model.*;
import com.example.DistributedSystemsLabor.ModelRepository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
class HomeController
{
    private boolean IsInit = false;

    public HomeController() {
    }

    @Autowired
    public AirportRepository airportRepository;

    @Autowired
    public AirlineRepository airlineRepository;

    @Autowired
    public AirplaneRepository airplaneRepository;

    @Autowired
    public IdentifierRepository identifierRepository;

    @Autowired
    public RunwayRepository runwayRepository;

    @Autowired
    public ParkingRepositiory parkingRepositiory;

    @GetMapping("/Index")
    public ModelAndView Index()
    {
        return new ModelAndView("Home.html","allAirports",airportRepository.findAll());
    }

    @GetMapping("/Init")
    public void Init(){
        // Run Init Code only once
        if(IsInit){
            return;
        }
        IsInit = true;

        // Airline Inits
        Airline airline = new Airline("Turkish Airlines");
        airlineRepository.save(airline);

        // Airplane Inits
        List<Airplane> airplanes = new ArrayList<>();
        Stream.of(
                new Airplane(Status.Flying),
                new Airplane(Status.Flying),
                new Airplane(Status.Flying),
                new Airplane(Status.Flying),
                new Airplane(Status.Flying)
        ).forEach(airplanes::add);

        for (Airplane a : airplanes) {
            airplaneRepository.save(a);
            a.setIdentifier(new Identifier("TR"+ randomString(5)));
            identifierRepository.save(a.getIdentifier());
        }

        // Runway Inits
        for(int i = 0; i < 4; ++i){
            Runway r = new Runway(false);
            runwayRepository.save(r);
        }

        // Parking Inits
        for(int i = 0; i < 8; ++i){
            Parking p = new Parking(false);
            parkingRepositiory.save(p);
        }
    }

    // Helper Functions
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static SecureRandom rnd = new SecureRandom();

    String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
}

package com.example.DistributedSystemsLabor.Controller;

import com.example.DistributedSystemsLabor.ModelRepository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
class HomeController
{
    public HomeController() {
    }

    @Autowired
    public AirportRepository airportRepository;


    @GetMapping("/Index")
    public ModelAndView Index()
    {
        return new ModelAndView("Home.html","allAirports",airportRepository.findAll());
    }
}

package com.example.DistributedSystemsLabor.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Airline
{
    public Airline(@NotNull String name) {
        Name = name;
    }

    public Airline() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotNull
    private String Name;
    @ManyToOne
    private Airplane airplane; // Keine Liste?

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}

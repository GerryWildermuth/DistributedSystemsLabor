package com.example.DistributedSystemsLabor.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Identifier
{
    public Identifier(@NotNull String name, @NotNull Airplane airplane) {
        Name = name;
        this.airplane = airplane;
    }

    public Identifier() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Number;
    @NotNull
    private String Name;
    @NotNull
    @OneToOne
    private Airplane airplane;

    public Long getNumber() {
        return Number;
    }

    public void setNumber(Long number) {
        Number = number;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }
}

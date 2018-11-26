package com.example.DistributedSystemsLabor.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Identifier
{
    public Identifier(@NotNull String name) {
        Name = name;
    }

    public Identifier() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Number;
    @NotNull
    private String Name;

    public Long getNumber() {
        return Number;
    }

    public void setNumber(Long number) {
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

package com.example.DistributedSystemsLabor.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Runway
{
    public Runway(boolean locked) {
        this.locked = locked;
    }

    public Runway() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private boolean locked;

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    public boolean getLocked() {
        return locked;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}

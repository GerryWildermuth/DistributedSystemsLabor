package com.example.DistributedSystemsLabor.Model;

import com.example.DistributedSystemsLabor.Enums.Status;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Airplane
{
    public Airplane(Status status){
        this.status = status;
    }

    public Airplane(Identifier identifier, Status status) {
        this.identifier = identifier;
        this.status = status;
    }

    public Airplane(Timestamp estimatedArrivalTime, Identifier identifier, Status status) {
        EstimatedArrivalTime = estimatedArrivalTime;
        this.identifier = identifier;
        this.status = status;
    }

    public Airplane(Timestamp estimatedArrivalTime, Status status) {
        EstimatedArrivalTime = estimatedArrivalTime;
        this.status = status;
    }

    public Airplane() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Number;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private Timestamp EstimatedArrivalTime;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private Timestamp RealArrivalTime;
    @OneToOne
    @JoinColumn(unique = true)
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private Identifier identifier;
    private Status status = Status.Other;
    @OneToOne
    @JoinColumn(unique = true)
    @Cascade(org.hibernate.annotations.CascadeType.DETACH)
    private Runway runway;
    @OneToOne
    @JoinColumn(unique = true)
    @Cascade(org.hibernate.annotations.CascadeType.DETACH)
    private Parking parking;


    public Runway getRunway() {
        return runway;
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Timestamp getEstimatedArrivalTime() {
        return EstimatedArrivalTime;
    }

    public void setEstimatedArrivalTime(Timestamp estimatedArrivalTime) {
        EstimatedArrivalTime = estimatedArrivalTime;
    }

    public Timestamp getRealArrivalTime() {
        return RealArrivalTime;
    }

    public void setRealArrivalTime(Timestamp realArrivalTime) {
        RealArrivalTime = realArrivalTime;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getNumber() {
        return Number;
    }

    public void setNumber(Long number) {
        Number = number;
    }
}

import { Component, OnInit } from '@angular/core';
import {SelectItem} from "primeng/api";
import {IAirplane} from "../WebModels/iairplane";
import {HttpClient} from "@angular/common/http";
import {WebRestService} from "../WebService/web-rest.service";
import {SynchronizerService} from "../WebService/synchronizer.service";
import {IParking} from "../WebModels/iparking";
import {IRunway} from "../WebModels/irunway";

@Component({
  selector: 'app-parking-plane-control',
  templateUrl: './parking-plane-control.component.html',
  styleUrls: ['./parking-plane-control.component.css']
})
export class ParkingPlaneControl {

  private landedAirplanes: IAirplane[] = [];
  private parkedAirplanes: IAirplane[] = [];
  public parkingPositions: IParking[] = [];

  public items: SelectItem[] = [];

  public selectedAirplane: IAirplane;

  constructor(private http:HttpClient, private service: WebRestService, private sync:SynchronizerService) {
    this.sync.Clock.subscribe(() => this.LoadData());
  }

  public LoadData(){
    this.landedAirplanes = this.service.GetAirplanes("Landed");
    this.items = this.landedAirplanes.map((airplane:IAirplane) => {
      return {
        label: airplane.identifier.name,
        value: airplane,
        disabled: airplane.parking != null
      };
    });
    this.parkedAirplanes = this.service.GetAirplanes("Parked");

    this.parkingPositions = this.service.GetParkings();
  }


  public ParkPlane(parkingPosition:IParking){
    this.http.get(this.service.BaseUrl+'/parking/park' +
      '?Slot='+parkingPosition.id+'&Airplane='+this.selectedAirplane.number)
      .subscribe(value=>{
        if(value){
          alert("Airplane is parked and Runway "+this.selectedAirplane.runway.id+' is free now');
        }else{
          alert("Could not park Airplane!")
        }
      });
  }

  public GetAirplane(parking: IRunway): IAirplane{
    return this.parkedAirplanes.find((airplane:IAirplane):boolean => {
      if(airplane.parking != null){
        return airplane.parking.id == parking.id;
      }
      return false;
    });
  }
}

import { Component, OnInit } from '@angular/core';
import {WebRestService} from "../WebService/web-rest.service";
import {SynchronizerService} from "../WebService/synchronizer.service";
import {IAirplane} from "../WebModels/iairplane";

@Component({
  selector: 'app-time-table',
  templateUrl: './time-table.component.html',
  styleUrls: ['./time-table.component.css']
})
export class TimeTable {

  public airplanes:IAirplane[] = [];

  constructor(private service: WebRestService) {
    this.service.OnLoad.subscribe(() => this.LoadData());
  }


  public LoadData(){
    this.airplanes = this.service.GetAirplanes();
  }


  public GetDateTimeString(dateValue:number):string {
    return this.service.GetDateTimeString(dateValue);
  }
}

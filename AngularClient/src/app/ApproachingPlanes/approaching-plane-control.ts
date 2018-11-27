import {Component} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {WebRestService} from "../WebService/web-rest.service";
import {IAirplane} from "../WebModels/iairplane";
import {SelectItem} from "primeng/api";

@Component({
  selector: 'approaching-plane-control',
  templateUrl: './approaching-plane-control.html',
  styleUrls: ['./approaching-plane-control.css']
})
export class ApproachingPlaneControl {

  private appraochingAirplanes: IAirplane[] = [];

  public items: SelectItem[] = [];

  public selectedAirplane: IAirplane;


  public constructor(private http:HttpClient, private service: WebRestService){
    this.http.get(service.GetBaseUrl()+'airplanes')
      .subscribe(value => {
        console.log(value as IAirplane[]);
        this.appraochingAirplanes = value as IAirplane[];
        this.items = this.appraochingAirplanes.map(value => {
          return {
            label: value.identifier.name,
            value: value
          }
        })
      });
  }
}

import {Component, HostListener} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {WebRestService} from "../WebService/web-rest.service";
import {IAirplane} from "../WebModels/iairplane";
import {SelectItem} from "primeng/api";
import {SynchronizerService} from "../WebService/synchronizer.service";

@Component({
  selector: 'approaching-plane-control',
  templateUrl: './approaching-plane-control.html',
  styleUrls: ['./approaching-plane-control.css']
})
export class ApproachingPlaneControl {

  private approachingAirplanes: IAirplane[] = [];

  public items: SelectItem[] = [];

  public selectedAirplane: IAirplane;

  public EAT: number = 0;

  public constructor(private http:HttpClient, private service: WebRestService, private sync:SynchronizerService){
    this.sync.Clock.subscribe(() => this.LoadData());
  }

  public LoadData(){
    this.approachingAirplanes = this.service.GetAirplanes('Flying');
    this.items = this.approachingAirplanes.map((airplane:IAirplane) => {
      return { label: airplane.identifier.name, value: airplane};
    });
  }

  public PermitLanding(){
    this.http.get(this.service.BaseUrl+'/airplane/permitlanding?id='+this.selectedAirplane.number+"&EAT="+this.EAT)
      .subscribe(value => {
        if(value) {
          alert("Plane permitted for landing");
        }else {
          alert("Plane not permitted for landing");
        }
      })
  }
}

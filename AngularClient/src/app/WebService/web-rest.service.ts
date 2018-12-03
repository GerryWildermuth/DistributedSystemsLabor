import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {IAirplane} from "../WebModels/iairplane";
import {SynchronizerService} from "./synchronizer.service";
import {IParking} from "../WebModels/iparking";

@Injectable({
  providedIn: 'root'
})
export class WebRestService {

  private airplanes: IAirplane[] = [];
  private parkingPositions: IParking[] = [];

  public readonly BaseUrl:string = 'http://localhost:8080/';
  public OnLoad:EventEmitter<any> = new EventEmitter<any>();

  constructor(private http:HttpClient, private sync: SynchronizerService) {
    this.sync.Clock.subscribe(() =>{
      this.RefreshData();
      console.log(this.airplanes);
    });
  }

  public GetAirplanes(status:string =""): IAirplane[]{
    if(status == ""){
      return this.airplanes;
    }
    return this.airplanes.filter((airplane:IAirplane):boolean => {
      return airplane.status == status;
    });
  }


  public GetParkings(): IParking[] {
    return this.parkingPositions;
  }

  private RefreshData(){
    this.http.get<IAirplane[]>(this.BaseUrl+'airplanes?Status=All')
      .subscribe(value => {
        this.airplanes = value;
        this.OnLoad.emit();
      });
    this.http.get<IParking[]>(this.BaseUrl+'parkings')
      .subscribe(value =>{
        this.parkingPositions = value;
        this.OnLoad.emit();
      });
    }

  public GetDateTimeString(dateValue:number):string {
    if(dateValue == null){
      return null;
    }
    return new Date(dateValue).toTimeString();
  }
}

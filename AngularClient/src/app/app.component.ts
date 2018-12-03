import {Component, EventEmitter, Output} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {WebRestService} from "./WebService/web-rest.service";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public title = 'Verteilte Systeme Labor';

  public constructor(private http:HttpClient, private service: WebRestService, private router: Router){
    this.InitService();
  }

  private InitService(){
    // Init Airplanes
    this.http.get(this.service.BaseUrl+'Init')
      .subscribe((value) => {console.log("Service Initialized")})
  }
}

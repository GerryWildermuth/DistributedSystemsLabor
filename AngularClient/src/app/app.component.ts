import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {WebRestService} from "./WebService/web-rest.service";
import {serialize} from "@angular/compiler/src/i18n/serializers/xml_helper";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Verteilte Systeme Labor';

  public constructor(private http:HttpClient, private service: WebRestService){
    this.InitService();
  }

  private InitService(){
    // Init Airplanes
    this.http.get(this.service.GetBaseUrl()+'airline/initialize')
      .subscribe((value) => {console.log("Initialized Airlines")})

    this.http.get(this.service.GetBaseUrl()+'airplane/initialize')
      .subscribe((value) => {console.log("Initialized Airplanes")});
  }
}
;

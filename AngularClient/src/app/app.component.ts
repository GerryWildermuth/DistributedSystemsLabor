import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Verteilte Systeme Labor';

  public constructor(private http:HttpClient){
    this.InitService();
  }

  private InitService(){
    this.http.get("http://localhost:8080/initialize").subscribe((value) => {alert("Initialized Service: "+value)});
  }
}

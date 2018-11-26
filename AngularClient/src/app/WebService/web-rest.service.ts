import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WebRestService {

  constructor() { }

  public GetBaseUrl(){
    return 'http://localhost:8080/';
  }
}

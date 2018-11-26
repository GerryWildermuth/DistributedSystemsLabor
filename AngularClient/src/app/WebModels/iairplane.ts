import {IIdentifier} from "./iidentifier";
import {IRunway} from "./irunway";
import {IParking} from "./iparking";

export interface IAirplane {
  number:number,
  estimatedArrivalTime:Date,
  realArrivalTime:Date,
  identifier:IIdentifier,
  status:string,
  runway:IRunway,
  parking:IParking,
}

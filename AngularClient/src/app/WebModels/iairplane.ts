import {IIdentifier} from "./iidentifier";
import {IRunway} from "./irunway";
import {IParking} from "./iparking";

export interface IAirplane {
  number:number,
  estimatedArrivalTime:number,
  realArrivalTime:number,
  identifier:IIdentifier,
  status:string,
  runway:IRunway,
  parking:IParking,
}

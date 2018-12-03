import {EventEmitter, Injectable, Output} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SynchronizerService {
  private _duration = 1500;

  Clock: EventEmitter<void> = new EventEmitter<void>();


  constructor() {
    setInterval(() => {
      this.Clock.emit();
    }, this._duration);
  }
}

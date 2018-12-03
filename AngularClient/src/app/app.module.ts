import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from "@angular/common/http";
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';


import { ToolbarModule } from 'primeng/toolbar';
import { SplitButtonModule } from 'primeng/splitbutton';
import { ListboxModule } from 'primeng/listbox';
import { CardModule } from 'primeng/card';
import { MultiSelectModule } from 'primeng/multiselect';
import { TableModule } from 'primeng/table';

import { AppComponent } from './app.component';
import { ApproachingPlaneControl } from './ApproachingPlanes/approaching-plane-control';
import { LandingPlaneControl } from './LandingPlanes/landing-plane-control.component';
import { ParkingPlaneControl } from './ParkingPlanes/parking-plane-control.component';
import { TimeTable } from './TimeTable/time-table.component';

const appRoutes: Routes = [
  {
    path: 'TimeTable',
    component: TimeTable
  },
  {
    path: 'ParkingPlaneControl',
    component: ParkingPlaneControl
  },
  {
    path: 'LandingPlaneControl',
    component: LandingPlaneControl
  },
  {
    path: 'ApproachingPlaneControl',
    component: ApproachingPlaneControl,
  },
  { path: '',
    redirectTo: '/ApproachingPlaneControl',
    pathMatch: 'full'
  }
];


@NgModule({
  declarations: [
    AppComponent,
    ApproachingPlaneControl,
    LandingPlaneControl,
    ParkingPlaneControl,
    TimeTable
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    ),
    HttpClientModule,
    BrowserModule,
    FormsModule,
    ToolbarModule,
    SplitButtonModule,
    ListboxModule,
    CardModule,
    MultiSelectModule,
    TableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

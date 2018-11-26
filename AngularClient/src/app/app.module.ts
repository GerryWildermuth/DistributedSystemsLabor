import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from "@angular/common/http";
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';


import { ToolbarModule } from 'primeng/toolbar';
import { SplitButtonModule } from 'primeng/splitbutton';
import { ListboxModule } from 'primeng/listbox';


import { AppComponent } from './app.component';
import {ApproachingPlaneControl} from './ApproachingPlanes/approaching-plane-control';

const appRoutes: Routes = [
  {
    path: 'APC',
    component: ApproachingPlaneControl,
    data: { title: 'Heroes List' }
  },
  { path: '',
    redirectTo: '/APC',
    pathMatch: 'full'
  }
];


@NgModule({
  declarations: [
    AppComponent,
    ApproachingPlaneControl
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
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

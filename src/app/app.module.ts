import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { FlashMessagesModule } from 'angular2-flash-messages';
import { HttpModule } from '@angular/http';

// Component Imports
import { AppComponent } from './app.component';
import { DashbaordComponent } from './components/dashbaord/dashbaord.component';
import { ClientsComponent } from './components/clients/clients.component';
import { ClientDetailsComponent } from './components/client-details/client-details.component';
import { AddClientComponent } from './components/add-client/add-client.component';
import { EditClientComponent } from './components/edit-client/edit-client.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { SettingsComponent } from './components/settings/settings.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { AlertComponent } from './alert/alert.component';

// Service Imports
import { ClientService } from './services/client.service';
import { AuthService } from './services/auth.service';
import { SettingsService } from './services/settings.service';
import { AlertService } from './services/alert.service';

import { AuthGuard } from './guards/auth.guard';
import { RegisterGuard } from './guards/register.guard';

const Rute: Routes = [
  {path:'', component:DashbaordComponent,canActivate:[AuthGuard]},
  {path:'register', component:RegisterComponent,canActivate:[RegisterGuard]},
  {path:'sidebar', component:SidebarComponent},
  {path:'login', component:LoginComponent},
  {path:'settings', component:SettingsComponent},
  {path:'add-client', component:AddClientComponent,canActivate:[AuthGuard]},
  {path:'client/:id', component:ClientDetailsComponent,canActivate:[AuthGuard]},
  {path:'edit-client/:id', component:EditClientComponent,canActivate:[AuthGuard]},
  {path:'**', component:PageNotFoundComponent}
];

export const firebaseConfig = {
    apiKey: "AIzaSyATyVKro-cR26OLKR_O78HY8u_P82-mQ3o",
    authDomain: "clientpanel-e9ae7.firebaseapp.com",
    databaseURL: "https://clientpanel-e9ae7.firebaseio.com",
    storageBucket: "clientpanel-e9ae7.appspot.com",
    messagingSenderId: "479596555240"
}

@NgModule({
  declarations: [
    AppComponent,
    DashbaordComponent,
    ClientsComponent,
    ClientDetailsComponent,
    AddClientComponent,
    EditClientComponent,
    NavbarComponent,
    SidebarComponent,
    LoginComponent,
    RegisterComponent,
    SettingsComponent,
    PageNotFoundComponent,
    AlertComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(Rute),
    FlashMessagesModule,
    HttpModule
  ],
  providers: [
    ClientService,
    AuthService,
    AuthGuard,
    SettingsService,
    RegisterGuard,
    AlertService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

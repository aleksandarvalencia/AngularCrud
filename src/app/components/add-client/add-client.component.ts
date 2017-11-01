import { Component, OnInit } from '@angular/core';
import { FlashMessagesService } from 'angular2-flash-messages';
import { Router } from '@angular/router';
import { ClientService } from '../../services/client.service';
import { Client } from '../../services/models/Client';
import { SettingsService } from '../../services/settings.service';

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent implements OnInit {
  client:Client = {
    idUser:0,
    firstname:'',
    secondname:'',
    idAuth:0,
    username:'',
    logged:0,
    status:0,
    password:'',
    confirmPassword:'',
    logonattempt:''

  }
  statusCode:number;
  disableBalanceOnAdd:boolean = true;
  
  constructor
  ( 
    public flashMessagesService:FlashMessagesService,
    public router:Router,
    public clientService: ClientService,
    public settingsService:SettingsService
  ) { }

  ngOnInit() {
    this.disableBalanceOnAdd = this.settingsService.getSettings().disableBalanceOnAdd;
  }

  onSubmit({value, valid}:{value:Client, valid:boolean}){

    if(!valid){
      this.flashMessagesService.show('Please fill in all fields', {cssClass:'alert-danger', timeout: 4000});
      this.router.navigate(['add-client']);
    } else {
      // Add new client
      this.clientService.newClient(value).subscribe(successCode => {
        this.statusCode = successCode;
        },errorCode => this.statusCode = errorCode,
      () => this.zavrsio_insert());
     
    }
  }

    zavrsio_insert()
    {

      this.flashMessagesService.show('New client added', {cssClass:'alert-success', timeout: 4000});
      this.router.navigate(['/']);

    }
}


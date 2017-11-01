import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../services/client.service'; 
import { FlashMessagesService } from 'angular2-flash-messages';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Client } from '../../services/models/Client';
import { SettingsService } from '../../services/settings.service';


@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css']
})
export class EditClientComponent implements OnInit {
  id:string;
  statusCode:number;
  public update_done:boolean=true;
  public client:Client = {
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

  disableBalanceOnEdit:boolean = true;

  constructor(
    public clientService:ClientService,
    public router:Router,
    public route:ActivatedRoute,
    public flashMessagesService:FlashMessagesService,
    public settingsService:SettingsService
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];

    // Get Client
    this.clientService.getClient(this.id).subscribe(client => {
      this.client = client;
    });

    this.disableBalanceOnEdit = this.settingsService.getSettings().disableBalanceOnEdit;
    this.update_done=false;
    this.clientService.setUpdate_done(this.update_done);
    this.clientService.setClient(this.client);
  }

  onSubmit({value, valid}:{value:Client, valid:boolean}){
    if(!valid){
      this.flashMessagesService.show('Please fill in all fields', {cssClass:'alert-danger', timeout: 4000});
      this.router.navigate(['edit-client/'+this.id]);
      this.update_done=false;
    } else {
      // Update client
      this.update_done=true;
      this.clientService.setUpdate_done(this.update_done);
      this.client=value;
      this.clientService.setClient(this.client);

      this.clientService.updateClient(this.id, value).subscribe(
        data => this.client = data,
        errorCode =>  this.statusCode = errorCode,
        () => this.zavrsio()
      );

  }
  }
    zavrsio()
    {
      this.flashMessagesService.show('Client updated', {cssClass:'alert-success', timeout: 4000});
      this.router.navigate(['/client/'+this.id]);
    }

}

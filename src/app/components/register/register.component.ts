import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {AlertService} from '../../services/alert.service';
import {ClientService} from '../../services/client.service';
import { Client } from '../../services/models/Client';

@Component({
    moduleId: module.id,
    templateUrl: 'register.component.html'
})
 
export class RegisterComponent {
    model: any = {};
    loading = false;
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
 
    constructor(
        private router: Router,
        private clientService: ClientService,
        private alertService: AlertService) { }
 
    register() {
        this.loading = true;
        console.log(this.client);
        this.clientService.newClient(this.client)
            .subscribe(
                data => {
                    this.alertService.success('Registracija uspešno urađena!', true);
                    this.router.navigate(['/login']);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }
}

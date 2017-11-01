import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class AuthGuard implements CanActivate {
    constructor(
        private router:Router
      
    ){}

    ulogovan: boolean=false;
    canActivate(): boolean{
        if (localStorage.getItem('currentUser')==null)
            {
                this.router.navigate(['/login']);
                this.ulogovan=false;
            }
        else
            {
                this.ulogovan=true;
            }

            return  this.ulogovan;
    }
    /*canActivate():boolean
    {
        return true;
    }*/
}
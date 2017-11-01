import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FlashMessagesService } from 'angular2-flash-messages';
import { AuthService } from '../../services/auth.service';
import { AlertService } from '../../services/alert.service';
import { Response }  from "@angular/http";
import { NavbarComponent } from '../../components/navbar/navbar.component';

@Component({
  moduleId: module.id,
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers:[NavbarComponent]
})
export class LoginComponent implements OnInit {
  email:string;
  password:string;
  loading = false;
  returnUrl: string;
  model: any = {};


  constructor(
    private authService:AuthService,
    private router:Router,
    private _flashMessagesService: FlashMessagesService,
    private alertService:AlertService,
    private route: ActivatedRoute,
    private navigation:NavbarComponent
  ) { }

  ngOnInit() {

    this.authService.logout();
    //this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
   
  }

 /* onSubmit(){
    this.authService.login(this.email, this.password)
      .then((res) => {
        this._flashMessagesService.show('You are logged in', { cssClass: 'alert-success', timeout: 4000 });
        this.router.navigate(['/']);
      })
      .catch((err) => {
        this._flashMessagesService.show(err.message, { cssClass: 'alert-danger', timeout: 4000 });
        this.router.navigate(['/login']);
      });
      this.router.navigate(['/']);
}*/

  login() {
    this.loading = true;
    this.authService.login(this.model.username, this.model.password)
        .subscribe(
            data => {
              //this.navigation.ngOnInit();
              this.router.navigate(['/']);
            },
            (greska:Response) => {
                this.alertService.error(greska.json().message);
                this.loading = false;
            });
}


}
import { Injectable } from '@angular/core';
import { Http, RequestOptions, Request, RequestMethod, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'
import { ClientService } from '../services/client.service';

@Injectable()
export class AuthService {
  public token: string;
  currentUser : any = null;
  public current_user : string=null;
  public cur_user : string =null;

  constructor(private http: Http, public clientService: ClientService ) 
  { 

    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = this.currentUser && this.currentUser.token;
  }

  login(username: string, password: string) {

    let headers = new Headers({'Content-Type' : 'application/json' });
    headers.append('Accept','application/json');
    let options = new RequestOptions({ headers: headers });

    return this.http.post('http://localhost:8080/AngularCrud/login', JSON.stringify({ username: username, password: password }),options)
        .map((response: Response) => {
            let user = response.json();
            this.current_user=response.json().username;
            this.setAuth(response.json().username);
            if (user && user.token) {
                localStorage.setItem('currentUser', JSON.stringify(user));
                /*let token = response.json() && response.json().token;
                 this.token = token;
                localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token }));
                */
            }
            return user;
        },message=>this.handleError(message));
}

logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
}

private handleError(error: any){
  console.error('An error occurred', error);
}

  /*login(email:string, password:string){
    return new Promise((resolve, reject) => {
      this.afAuth.auth.signInWithEmailAndPassword(email, password)
        .then(userData => resolve(userData),
        err => reject(err));
    });
  }*/

    setAuth(current_user : string)
    {
        this.cur_user=current_user;
    }

    // Check user status

    getAuth() 
    {
      return this.cur_user;
    }
  
    // Logout User
    /*logout(){
      this.afAuth.auth.signOut();
    }*/

     // Register User
  register(email:string, password:string){
    /*return new Promise((resolve, reject) => {
      this.afAuth.auth.createUserWithEmailAndPassword(email, password)
        .then(userData => resolve(userData),
          err => reject(err));
    });*/
  }

}

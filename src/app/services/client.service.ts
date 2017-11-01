import { Injectable } from '@angular/core';
import { Http, RequestOptions, Request, RequestMethod, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
import { Client } from './models/Client';

@Injectable()
export class ClientService {
  clients:Observable<any[]>;
  client:Observable<any>;
  client_prom:Client;
  update_done:boolean;
  public cur_user : string =null;

  constructor(public http:Http) 
  {
    this.update_done=false;
  }

  getClients(){
      this.clients=this.http.get('http://localhost:8080/AngularCrud/users').map(res=>res.json());
      return this.clients;
  }

  getClient(id:string){
    this.client = this.http.get('http://localhost:8080/AngularCrud/users/'+ id).map(res=>res.json()) as Observable<Client>;
    return this.client;
  }

  
  newClient(client:Client): Observable<number>{
    /* let headers = new Headers({ 'Authorization': 'Bearer ' + this.authenticationService.token });*/ 
    let headers = new Headers({'Content-Type' : 'application/json' });
    headers.append('Accept','application/json');
    let options = new RequestOptions({ headers: headers });
    return this.http.post('http://localhost:8080/AngularCrud/users',JSON.stringify(client),options).map(success => success.status).catch(this.handleErrorOb); 
  }

  /*newClient(client:Client): Promise<Client>{
    let headers = new Headers({'Content-Type' : 'application/json' });
    headers.append('Accept','application/json');
    let options = new RequestOptions({ headers: headers });
    return this.http.post('http://localhost:8080/AngularCrud/users',JSON.stringify(client),options).toPromise().then(res => res.json().data as Client) 
    .catch(this.handleError);
  }*/
  
  updateClient(id:string, client:Client) : Observable<Client> {
     /* let headers = new Headers({ 'Authorization': 'Bearer ' + this.authenticationService.token });*/ 
    let headers = new Headers({'Content-Type':'application/json' });    
    headers.append('Accept','application/json');
    let options = new RequestOptions({ headers: headers });
    return this.http.put('http://localhost:8080/AngularCrud/users/'+ id,JSON.stringify(client),options).map(res=>res.json())
    .catch(this.handleErrorOb) as Observable<Client>;
  }
  /*updateClient(id:string, client:Client): Promise<Client> {
    let headers = new Headers({'Content-Type':'application/json' });    
    headers.append('Accept','application/json');
    let options = new RequestOptions({ headers: headers });
    return this.http.put('http://localhost:8080/AngularCrud/users/'+ id,JSON.stringify(client),options).toPromise().then(() => client)
    .catch(this.handleError);
  }*/
  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
  private handleErrorOb(error: any) : Observable<any> {
    console.error('An error occurred', error);
    return Observable.throw(error.message || error);
  }

  deleteClient(id:string): Observable<number>{
     /* let headers = new Headers({ 'Authorization': 'Bearer ' + this.authenticationService.token });*/ 
     let headers = new Headers({ 'Accept': 'application/json' });
     headers.append('Content-Type', 'application/json');
     let options = new RequestOptions({ headers: headers });
     return this.http.delete('http://localhost:8080/AngularCrud/users/'+ id,options).map(success => success.status)
     .catch(this.handleErrorOb);

    
  }
  /*deleteClient(id:string):Promise<any>{
    let headers = new Headers({ 'Accept': 'application/json' });
    headers.append('Content-Type', 'application/json');
    let options = new RequestOptions({ headers: headers });
    console.log('delete');
   return this.http.delete('http://localhost:8080/AngularCrud/users/'+ id,options).toPromise().then(() => null)
   .catch(this.handleError);
 }*/

    setClient(prom_client:Client)
    {
      this.client_prom=prom_client;
    }

    getClient_prom() : Client
    {
        return this.client_prom;

    }

    getUpdate_done() :boolean
    {
        return this.update_done;

    }

    setUpdate_done(up_dn:boolean)
    {
        this.update_done=up_dn;

    }
    setAuth(current_user : string)
    {
        this.cur_user=current_user;
    }

    getAuth() 
    {
      return this.cur_user;
    }

}

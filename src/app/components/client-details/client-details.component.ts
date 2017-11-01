import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../services/client.service'; 
import { FlashMessagesService } from 'angular2-flash-messages';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Client } from '../../services/models/Client';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css'],
})
export class ClientDetailsComponent implements OnInit {
  id:string;
  client: Client;
  hasBalance:boolean = false;
  showBalanceUpdateInput:boolean = false;
  statusCode:number;
  update_done:boolean=false;

  constructor(
    public clientService:ClientService,
    public router:Router,
    public route:ActivatedRoute,
    public flashMessagesService:FlashMessagesService,
  ) { }

  ngOnInit() 
  {

      this.update_done=this.clientService.getUpdate_done();
      this.id = this.route.snapshot.params['id'];

      this.clientService.getClient(this.id).subscribe(client => 
        {
          this.client = client;
        });

      /*if (this.update_done==null || !this.update_done)
      {
        
        
        this.clientService.getClient(this.id).subscribe(client => 
          {
            this.client = client;
          });
      }
      else
      {
        this.client=this.clientService.getClient_prom();

        this.clientService.updateClient(this.id,this.client).subscribe(data => 
          {this.client = data;
          },
          errorCode =>  this.statusCode = errorCode,
          () => this.completed()
        );

      } */
    }
 
      onDeleteClick(){
        if(confirm("Are you sure to delete?")){
          this.clientService.deleteClient(this.id).subscribe(successCode => 
            {
              this.statusCode = successCode;
            },errorCode => this.statusCode = errorCode,
            () => this.completed()
           );
          
        }
      }

      completed()
      {
         this.flashMessagesService.show('Client Deleted', { cssClass: 'alert-success', timeout: 4000 });
          this.router.navigate(['/']);
          
      }

}

import { Component, OnInit } from '@angular/core';
import {Client } from '@stomp/stompjs';
import * as Sock from 'sockjs-client';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  public listUser = ['General','Alejo', 'Daniela'];
  public user: string;
  private client: Client
  constructor() {
    this.client = new Client();
    this.user = 'General';
  }

  ngOnInit(): void {

    this.client.webSocketFactory = ()=>{
      console.log('Conectar')
      return new Sock(`${environment.urlApi}/chat`);
    }
    console.log('Prueba')
    this.client.onConnect = (frame) => {
      console.log('error')
      console.log('Conectados: '+ this.client.connected+ ' : '+ frame);
    }
    console.log('Activar')
    this.client.activate();
  }

  selectUser(event: any){
    this.user = event.option._value;

  }
}

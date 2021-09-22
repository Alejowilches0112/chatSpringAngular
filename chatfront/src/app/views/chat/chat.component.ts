import { Message } from './../../models/Message';
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
  public canal: string;
  private client: Client
  public viewChat: boolean;
  public message: Message;
  public listMessage: Message[];
  public Usuario: string = '';

  constructor() {
    this.client = new Client();
    this.canal = '';
    this.viewChat = false;
    this.listMessage = [];
    this.message = new Message();
  }

  ngOnInit(): void {

    this.client.webSocketFactory = ()=>{
      return new Sock(`${environment.urlApi}/chat`);
    }
    this.client.onConnect = (frame) => {
      console.log('Conectados: '+ this.client.connected+ ' : '+ frame);
      this.client.subscribe('/app-chat/mensaje', (e) => {

        const mensaje = JSON.parse(e.body) as Message;
        console.log(mensaje)
        mensaje.timestamp = new Date(mensaje.timestamp);

        if (!this.message.color && mensaje.tipo === 'NEW USER' && this.message.username === mensaje.username){
          this.message.color = mensaje.color;
        }
        if( mensaje.tipo === 'NEW USER' ){
          this.message.listUser = mensaje.listUser;
        }
        this.listMessage.push(mensaje);
        console.log(this.listMessage)
      });

      this.message.tipo = 'NEW USER';
      this.message.dentination = 'General';
      this.canal = 'General';
      this.client.publish({destination: '/app/chatGeneral/connection', body: JSON.stringify(this.message)})
    }

    this.client.onDisconnect = (frame) => {
      console.log('Desconectados: '+ !this.client.connected+ ' : '+ frame);
    }

  }

  selectUser(event: any){
    this.canal = event.option._value;
    this.message.dentination = this.canal;
  }

  conectar(): void{
    this.client.activate();
    this.viewChat = true;
    this.Usuario = this.message.username;
    console.log('conectar');
  }

  desconectar(): void{
    this.client.deactivate();
    this.viewChat = false;
    this.canal = '';
    console.log('desconectar');
  }

  sendMessage(): void {
    this.message.tipo = 'MENSAJE';
    this.message.dentination = (this.message.dentination === 'General') ? this.message.dentination :`${this.message.dentination}_${this.message.username}`;
    console.log(this.canal)
    console.log(this.message.dentination.indexOf(this.canal))
    console.log(this.message.dentination.indexOf(this.message.username))
    const mensaje = JSON.stringify(this.message);
    this.client.publish({destination: `/app/chatGeneral/${this.message.dentination}`, body: mensaje});
    this.message.mensaje = '';
  }
}

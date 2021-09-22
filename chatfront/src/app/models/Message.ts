export class Message {
  mensaje: string;
  timestamp: any;
  dentination: string;
  username : string;
  tipo: string;
  color: any;
  listUser: string [];

  constructor() {
    this.mensaje = '';
    this.dentination = '';
    this.username = '';
    this.tipo = '';
    this.color = null;
    this.timestamp = null;
    this.listUser = [];
 }
}

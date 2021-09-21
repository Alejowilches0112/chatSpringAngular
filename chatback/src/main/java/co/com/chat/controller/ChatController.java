package co.com.chat.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import co.com.chat.model.Mensaje;

@Controller
public class ChatController {
	
	@MessageMapping("/chatGeneral")
	@SendTo("/app-chat/mensaje")
	public Mensaje recibeMensaje(Mensaje mensaje) {
		mensaje.setTimestamp(new Date().getTime());
		mensaje.setMensaje("El mensaje recibido es: ".concat(mensaje.getMensaje()));
		
		return mensaje;
	}
}

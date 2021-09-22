package co.com.chat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import co.com.chat.model.Mensaje;

@Controller
public class ChatController {

	private String[] colores = { "red", "green", "blue", "magenta", "purple", "orange" };
	private List<String> canales = new ArrayList<>();
	@Autowired
	private SimpMessagingTemplate messaginTemplate;

	@MessageMapping("/chatGeneral/{to}")
	// @SendTo("/app-chat/mensaje")
	public void recibeMensaje(@DestinationVariable String to, Mensaje mensaje) {
		try {
			System.err.println(to);
			/*
			 * if (canales.size() == 0) { canales.add("General"); }
			 */
			mensaje.setTimestamp(new Date().getTime());
			if (mensaje.getTipo().equals("NEW USER")) {
				mensaje.setMensaje("nuevo usuario ".concat(mensaje.getMensaje()));
				mensaje.setColor(colores[new Random().nextInt(colores.length)]);
				if (canales.indexOf(mensaje.getUsername()) == -1) {
					canales.add(mensaje.getUsername());
				}
				mensaje.setListUser(canales);
			}
			System.err.println(mensaje.toString());
			messaginTemplate.convertAndSend("/app-chat/mensaje", mensaje);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			// System.err.println(e.getCause().getMessage());
			mensaje.setMensaje("Mensaje No Recibido");
		}
	}
}

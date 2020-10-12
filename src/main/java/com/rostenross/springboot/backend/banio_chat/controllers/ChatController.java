package com.rostenross.springboot.backend.banio_chat.controllers;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.rostenross.springboot.backend.banio_chat.document.Mensaje;

@Controller
public class ChatController {
	private Logger log = LoggerFactory.getLogger(ChatController.class);
	private String[] colores= {"red","green","blue","magenta","purple","orange"};
	
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Mensaje recibeMensaje(Mensaje mensaje) {
		mensaje.setFecha(new Date().getTime());
		if (mensaje.getTipo().equals("NUEVO_USUARIO")) {
			log.debug("notificacion: ....");
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("nuevo usuario");
			log.debug("notificacion: "+mensaje.getTipo());
			log.debug("notificacion: "+mensaje.getTexto());
			log.debug("notificacion: "+mensaje.isNuevo());
		}
		
		return mensaje;
	}
	
	@MessageMapping("/notificacion")
	@SendTo("/chat/notificacion")
	public Mensaje recibeNotificacion(Mensaje mensaje) {
		mensaje.setFecha(new Date().getTime());
		if (mensaje.getTipo().equals("NOTIFICACION")) {
			log.debug("notificacion: ....");
			mensaje.setColor("green");
			mensaje.setTexto("¡¡Nuevo usuario!!");
			mensaje.setNuevo(true);
			log.debug("notificacion: "+mensaje.getTipo());
			log.debug("notificacion: "+mensaje.getTexto());
			log.debug("notificacion: "+mensaje.isNuevo());
					
		}
		return mensaje;
	}
}
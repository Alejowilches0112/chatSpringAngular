package co.com.chat.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mensajes")

public class Mensaje implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String mensaje;

	private long timestamp;

	private String dentination;

	private String username;

	private String tipo;

	private String color;

	private List<String> listUser;

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getDentination() {
		return dentination;
	}

	public void setDentination(String dentination) {
		this.dentination = dentination;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<String> getListUser() {
		return listUser;
	}

	public void setListUser(List<String> listUser) {
		this.listUser = listUser;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

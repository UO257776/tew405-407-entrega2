package com.tew.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="agente")
public class Agente implements Serializable{
	private static final long serialVersionUID = -260849377585444752L;
	private long id;
	private String login;
	private String passwd;
	
	public Agente() {
	}
	
	public Agente(long id, String login, String passwd) {
		this.id = id;
		this.login = login;
		this.passwd = passwd;
	}

	@XmlElement
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@XmlElement
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	@XmlElement
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}

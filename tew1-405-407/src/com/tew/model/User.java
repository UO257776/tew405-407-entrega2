package com.tew.model;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 8420789334117957380L;
	private String login;
	private Long id;
	private String role;
		
	public User(String login, Long id, String role) {
		super();
		this.login = login;
		this.id = id;
		this.role = role;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Long getId() {
		return id;
	}
	public void setName(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}

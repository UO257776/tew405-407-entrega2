package com.tew.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "piso")
public class Piso implements Serializable {
	private static final long serialVersionUID = -1327785803761462432L;
	private long id;
	private long idagente;
	private double precio;
	private String direccion;
	private String ciudad;
	private int a�o;
	private int estado;
		
	public Piso() {
	}
	
	public Piso(long id, long idagente, double precio, String direccion, String ciudad, int a�o, int estado) {
		this.id = id;
		this.idagente = idagente;
		this.precio = precio;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.a�o = a�o;
		this.estado = estado;
	}
	
	@XmlElement
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@XmlElement
	public long getIdagente() {
		return idagente;
	}
	public void setIdagente(long idagente) {
		this.idagente = idagente;
	}
	@XmlElement
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@XmlElement
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direcci�n) {
		this.direccion = direcci�n;
	}
	@XmlElement
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	@XmlElement
	public int getA�o() {
		return a�o;
	}
	public void setA�o(int a�o) {
		this.a�o = a�o;
	}
	@XmlElement
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}	
	
}

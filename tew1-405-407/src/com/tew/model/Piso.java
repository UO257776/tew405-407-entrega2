package com.tew.model;

public class Piso {
	private long id;
	private long idagente;
	private double precio;
	private String direccion;
	private String ciudad;
	private int a�o;
	private int estado;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdagente() {
		return idagente;
	}
	public void setIdagente(long idagente) {
		this.idagente = idagente;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direcci�n) {
		this.direccion = direcci�n;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getA�o() {
		return a�o;
	}
	public void setA�o(int a�o) {
		this.a�o = a�o;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}	
	
}

package com.tew.model;

public class PisoParaVisitar {
	private long idpiso;
	private long idcliente;
	private long fechahoracita;
	private int estado;
	
	public long getIdpiso() {
		return idpiso;
	}
	public void setIdpiso(long idpiso) {
		this.idpiso = idpiso;
	}
	public long getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(long idcliente) {
		this.idcliente = idcliente;
	}
	public long getFechahoracita() {
		return fechahoracita;
	}
	public void setFechahoracita(long fechahoracita) {
		this.fechahoracita = fechahoracita;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}	

}

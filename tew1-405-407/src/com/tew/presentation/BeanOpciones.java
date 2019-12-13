package com.tew.presentation;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tew.business.ResetService;
import com.tew.infrastructure.Factories;

@ManagedBean
@SessionScoped
public class BeanOpciones implements Serializable {
	private static final long serialVersionUID = -6606159425503523541L;
	
	public String reset() {
		ResetService service= Factories.services.createResetService();
		service.reset();
		cerrar();
		return "close";
		
	}
	
	public String cerrar() {
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.remove("LOGGEDIN_USER");
		return "close";
	}
	
}

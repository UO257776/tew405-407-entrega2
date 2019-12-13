package com.tew.presentation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tew.business.ClientesService;
import com.tew.business.LoginService;
import com.tew.infrastructure.Factories;
import com.tew.model.Cliente;
import com.tew.model.User;

@ManagedBean
@SessionScoped
public class BeanInicio implements Serializable {
	private static final long serialVersionUID = 55558L;
	// Se aÃ±ade este atributo de entidad para recibir el cliente concreto
	// selecionado de la tabla o de un formulario
	// Es necesario inicializarlo para que al entrar desde el formulario de
	// AltaForm.xml se puedan
	// dejar los avalores en un objeto existente.

	private Cliente cliente = new Cliente();
	private boolean acuerdo = false;
	private String confirm ="";
	private String name = "";
	private String password = "";

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(boolean acuerdo) {
		this.acuerdo = acuerdo;
	}
	
	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String salva() {
		ClientesService service;
		try {
			FacesContext jsfCtx = FacesContext.getCurrentInstance();
			ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
			FacesMessage msg = null;
			// Acceso a la implementacion de la capa de negocio
			// a travï¿½ï¿½s de la factorï¿½ï¿½a
			service = Factories.services.createClientesService();
			// Salvamos o actualizamos el alumno segun sea una operacion de alta o de
			// ediciï¿½ï¿½n

			List<Cliente> listaClientes = service.getClientes();

			// Comprueba que no existe un cliente con ese email
			for (Cliente c : listaClientes) {
				if (c.getEmail().equals(cliente.getEmail())) {
					// si el mail no es válido
					// se prepara el mensaje que saldra en la vista del cliente
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("signup_form_result_error1"),
							null);
					// se añade al element con id=”msg”
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return "index";
				}
			}
			
			//Si las contraseñas no coinciden salta otro error
			if (!cliente.getPasswd().equals(getConfirm())) {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("formCliente_confirmPassword_validatorMessage"),
						null);
				// se añade al element con id=”msg”
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return "index";
			}
			
			//Si no se ha aceptado la política de privacidad salta otro error
			if (!this.isAcuerdo()) {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("signup_form_result_error2"),
						null);
				// se añade al element con id=”msg”
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return "index";
			}


			// Registra al cliente en la base de datos, le mete en sesión y le manda a su
			// página principal
			cliente.setLogin(cliente.getEmail());
			service.saveCliente(cliente);
			LoginService login = Factories.services.createLoginService();
			User user = login.verify(cliente.getLogin(), cliente.getPasswd());
			putUserInSession(user);
			return "cliente";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String verify() {
		// necesario para accede a msgs y a los mensajes en español e ingles de los
		// ficheros
		// de propiedades
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
		FacesMessage msg = null;
		LoginService login = Factories.services.createLoginService();

		User user = login.verify(name, password);
		if (user != null) {
			putUserInSession(user);
			return user.getRole();
		}

		// si el usuario no se encuentra
		// se prepara el mensaje que saldra en la vista del cliente
		msg = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("login_form_result_error"), null);
		// se añade al element con id=”msg”
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return "index";
	}

	private void putUserInSession(User user) {
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.put("LOGGEDIN_USER", user);
	}
	
}

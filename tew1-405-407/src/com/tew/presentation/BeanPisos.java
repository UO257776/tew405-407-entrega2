package com.tew.presentation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import com.tew.business.PisosParaVisitarService;
import com.tew.business.PisosService;
import com.tew.infrastructure.Factories;
import com.tew.model.Piso;
import com.tew.model.PisoParaVisitar;
import com.tew.model.User;

@ManagedBean
@SessionScoped
public class BeanPisos implements Serializable {
	private static final long serialVersionUID = 3492864626126393548L;

	private Piso piso = new Piso();
	private Piso[] pisos = null;
	private String filtroCiudad = "";
	private double precioSuperior = 100000;
	private double precioInferior = 0;
	
	public Piso getPiso() {
		return piso;
	}

	public void setPiso(Piso piso) {
		this.piso = piso;
	}

	public Piso[] getPisos() {
		return pisos;
	}

	public void setPisos(Piso[] pisos) {
		this.pisos = pisos;
	}
	
	public String getFiltroCiudad() {
		return filtroCiudad;
	}

	public void setFiltroCiudad(String filtroCiudad) {
		this.filtroCiudad = filtroCiudad;
	}

	public double getPrecioSuperior() {
		return precioSuperior;
	}

	public void setPrecioSuperior(double precioSuperior) {
		this.precioSuperior = precioSuperior;
	}

	public double getPrecioInferior() {
		return precioInferior;
	}

	public void setPrecioInferior(double precioInferior) {
		this.precioInferior = precioInferior;
	}

	public String listado() {
		PisosService service;
		try {
			// Acceso a la implementacion de la capa de negocio
			// a travï¿½ï¿½s de la factorï¿½ï¿½a
			service = Factories.services.createPisosService();
			// De esta forma le damos informaciï¿½ï¿½n a toArray para poder hacer el casting
			// a Pisos[]
			pisos = (Piso[]) service.getPisos().toArray(new Piso[0]);

			return "exitoListaPisos";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String filtra() {
		PisosService service;
		try {
			service = Factories.services.createPisosService();
			List<Piso> listaFiltrar = service.getPisos();
			
			Iterator<Piso> iter = listaFiltrar.iterator();

			while (iter.hasNext()) {
				Piso p = iter.next();
				if (!p.getCiudad().contains(filtroCiudad) || p.getPrecio() < precioInferior
						|| p.getPrecio() > precioSuperior)
					iter.remove();
			}
			pisos = (Piso[]) listaFiltrar.toArray(new Piso[0]);
			return "exitoListaPisos";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String baja(Piso piso) {
		PisosService serviceP;
		PisosParaVisitarService serviceV;
		try {
			// Acceso a la implementacion de la capa de negocio
			// a travï¿½ï¿½s de la factorï¿½ï¿½a
			serviceP = Factories.services.createPisosService();
			serviceV = Factories.services.createPisosParaVisitarService();
			// Eliminamos antes las visitas relacionadas para evitar inconsistencias en la BD
			List<PisoParaVisitar> listaV = serviceV.getPisosParaVisitar();
			for (PisoParaVisitar v : listaV) {
				if (v.getIdpiso()==piso.getId()) serviceV.deletePisoParaVisitar(v.getIdpiso(), v.getIdcliente());
			}
			// Eliminamos el piso seleccionado en la tabla
			serviceP.deletePiso(piso.getId());
			// Actualizamos el javabean de pisos inyectado en la tabla.
			pisos = (Piso[]) serviceP.getPisos().toArray(new Piso[0]);
			return "exitoListaPisos";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}
	
	public String carga(Piso piso) {
		this.piso = piso;
		return "altaPisosAgente";
	}
	
	public String salva() {
		PisosService serviceP;
		
		try {
			serviceP = Factories.services.createPisosService();
			Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			
			long idagente =  ((User) session.get("LOGGEDIN_USER")).getId();
			
			piso.setIdagente(idagente);
			
			serviceP.savePiso(piso);
			
			//Actualizamos el javabean de alumnos inyectado en la tabla
			pisos = (Piso [])serviceP.getPisos().toArray(new Piso[0]);
			return "exitoListaPisos";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
        
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("cityChange"), bundle.getString("cityOld") + oldValue + bundle.getString("cityNew") + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}

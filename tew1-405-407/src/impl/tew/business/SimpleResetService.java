package impl.tew.business;

import java.util.Date;
import java.util.List;

import com.tew.business.AgentesService;
import com.tew.business.ClientesService;
import com.tew.business.PisosParaVisitarService;
import com.tew.business.PisosService;
import com.tew.business.ResetService;
import com.tew.infrastructure.Factories;
import com.tew.model.Agente;
import com.tew.model.Cliente;
import com.tew.model.Piso;
import com.tew.model.PisoParaVisitar;

public class SimpleResetService implements ResetService {
	
	AgentesService servicioA;
	ClientesService servicioC;
	PisosService servicioP;
	PisosParaVisitarService servicioV;
	
	public SimpleResetService() {
		servicioA = Factories.services.createAgentesService();
		servicioC = Factories.services.createClientesService();
		servicioP = Factories.services.createPisosService();
		servicioV = Factories.services.createPisosParaVisitarService();
	}

	@Override
	public void delete() {
		try {
		List<Agente> listaA = servicioA.getAgentes();
		List<Cliente> listaC = servicioC.getClientes();
		List<Piso> listaP = servicioP.getPisos();
		List<PisoParaVisitar> listaV = servicioV.getPisosParaVisitar();

		for (PisoParaVisitar v : listaV)
			servicioV.deletePisoParaVisitar(v.getIdpiso(), v.getIdcliente());
		for (Piso p : listaP)
			servicioP.deletePiso(p.getId());
		for (Cliente c : listaC)
			servicioC.deleteCliente(c.getId());
		for (Agente a : listaA)
			servicioA.deleteAgente(a.getId());

		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert() {
		try {
			Agente agente1 = new Agente();
			agente1.setLogin("agente1@micorreo.com");
			agente1.setPasswd("clave1");
			
			Agente agente2 = new Agente();
			agente2.setLogin("agente2@micorreo.com");
			agente2.setPasswd("clave2");
			
			servicioA.saveAgente(agente1);
			servicioA.saveAgente(agente2);
			
			Cliente cliente1 = new Cliente();
			cliente1.setLogin("user1@micorreo.com");
			cliente1.setPasswd("clave1");
			cliente1.setNombre("Cliente");
			cliente1.setApellidos("1");
			cliente1.setEmail("user1@micorreo.com");
			
			Cliente cliente2 = new Cliente();
			cliente2.setLogin("user2@micorreo.com");
			cliente2.setPasswd("clave2");
			cliente2.setNombre("Cliente");
			cliente2.setApellidos("2");
			cliente2.setEmail("user2@micorreo.com");
			
			servicioC.saveCliente(cliente1);
			servicioC.saveCliente(cliente2);
			
			agente1 = servicioA.getAgentes().get(0);
			agente2 = servicioA.getAgentes().get(1);
			
			Piso piso1 = new Piso();
			piso1.setIdagente(agente1.getId());
			piso1.setPrecio(10000.00);
			piso1.setDireccion("Calle Cabrales");
			piso1.setCiudad("Gijón");
			piso1.setAño(2005);
			piso1.setEstado(2);
			
			Piso piso2 = new Piso();
			piso2.setIdagente(agente1.getId());
			piso2.setPrecio(20000.00);
			piso2.setDireccion("Calle Brasil");
			piso2.setCiudad("Gijón");
			piso2.setAño(2015);
			piso2.setEstado(3);
			
			Piso piso3 = new Piso();
			piso3.setIdagente(agente2.getId());
			piso3.setPrecio(15000.00);
			piso3.setDireccion("Calle Uría");
			piso3.setCiudad("Oviedo");
			piso3.setAño(2010);
			piso3.setEstado(4);
			
			Piso piso4 = new Piso();
			piso4.setIdagente(agente2.getId());
			piso4.setPrecio(30000.00);
			piso4.setDireccion("Calle Campoamor");
			piso4.setCiudad("Oviedo");
			piso4.setAño(2019);
			piso4.setEstado(5);			
			
			servicioP.savePiso(piso1);
			servicioP.savePiso(piso2);
			servicioP.savePiso(piso3);
			servicioP.savePiso(piso4);
			
			cliente1 = servicioC.getClientes().get(0);
			cliente2 = servicioC.getClientes().get(1);
			
			piso1 = servicioP.getPisos().get(0);
			piso2 = servicioP.getPisos().get(1);
			piso3 = servicioP.getPisos().get(2);
			piso4 = servicioP.getPisos().get(3);
			
			PisoParaVisitar cita1 = new PisoParaVisitar();
			cita1.setIdpiso(piso1.getId());
			cita1.setIdcliente(cliente1.getId());
			cita1.setFechahoracita(new Date().getTime());
			cita1.setEstado(1);
			
			PisoParaVisitar cita2 = new PisoParaVisitar();
			cita2.setIdpiso(piso2.getId());
			cita2.setIdcliente(cliente1.getId());
			cita2.setFechahoracita(new Date().getTime());
			cita2.setEstado(2);
			
			PisoParaVisitar cita3 = new PisoParaVisitar();
			cita3.setIdpiso(piso3.getId());
			cita3.setIdcliente(cliente1.getId());
			cita3.setFechahoracita(new Date().getTime());
			cita3.setEstado(3);
			
			PisoParaVisitar cita4 = new PisoParaVisitar();
			cita4.setIdpiso(piso4.getId());
			cita4.setIdcliente(cliente2.getId());
			cita4.setFechahoracita(new Date().getTime());
			cita4.setEstado(1);
			
			PisoParaVisitar cita5 = new PisoParaVisitar();
			cita5.setIdpiso(piso1.getId());
			cita5.setIdcliente(cliente2.getId());
			cita5.setFechahoracita(new Date().getTime());
			cita5.setEstado(2);
			
			PisoParaVisitar cita6 = new PisoParaVisitar();
			cita6.setIdpiso(piso2.getId());
			cita6.setIdcliente(cliente2.getId());
			cita6.setFechahoracita(new Date().getTime());
			cita6.setEstado(3);
						
			servicioV.savePisoParaVisitar(cita1);
			servicioV.savePisoParaVisitar(cita2);
			servicioV.savePisoParaVisitar(cita3);
			servicioV.savePisoParaVisitar(cita4);
			servicioV.savePisoParaVisitar(cita5);
			servicioV.savePisoParaVisitar(cita6);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void reset() {
		delete();
		insert();
	}

}

package impl.tew.business;

import com.tew.business.AgentesService;
import com.tew.business.ClientesService;
import com.tew.business.LoginService;
import com.tew.infrastructure.Factories;
import com.tew.model.Agente;
import com.tew.model.Cliente;
import com.tew.model.User;

public class SimpleLoginService implements LoginService {

	@Override
	public User verify(String login, String password) {
		AgentesService serviceA = Factories.services.createAgentesService();
		try {
			for (Agente a : serviceA.getAgentes()) {
				if ((a.getLogin().equals(login)) && (a.getPasswd().equals(password)))
					return new User(login, a.getId(), "agente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ClientesService serviceC = Factories.services.createClientesService();
		try {
			for (Cliente c : serviceC.getClientes()) {
				if ((c.getLogin().equals(login)) && (c.getPasswd().equals(password)))
					return new User (login, c.getId(), "cliente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

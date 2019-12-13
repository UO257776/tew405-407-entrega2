package impl.tew.business;


import com.tew.business.AgentesService;
import com.tew.business.ClientesService;
import com.tew.business.LoginService;
import com.tew.business.PisosParaVisitarService;
import com.tew.business.PisosService;
import com.tew.business.ResetService;
import com.tew.business.ServicesFactory;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public AgentesService createAgentesService() {
		return new SimpleAgentesService();
	}

	@Override
	public ClientesService createClientesService() {
		return new SimpleClientesService();
	}

	@Override
	public PisosService createPisosService() {
		return new SimplePisosService();
	}

	@Override
	public PisosParaVisitarService createPisosParaVisitarService() {
		return new SimplePisosParaVisitarService();
	}

	@Override
	public LoginService createLoginService() {
		return new SimpleLoginService();
	}

	@Override
	public ResetService createResetService() {
		return new SimpleResetService();
	}
	
}

package impl.tew.business.resteasy;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.business.resteasy.AgentesServicesRs;
import com.tew.model.Agente;
import impl.tew.business.classes.*;

public class AgentesServicesRsImpl implements AgentesServicesRs {

	@Override
	public List<Agente> getAgentes() throws Exception {
		return new AgentesListado().getAgentes();
	}

	@Override
	public Agente findById(Long id) throws EntityNotFoundException {
		return new AgentesBuscar().find(id);
	}

	@Override
	public void deleteAgente(Long id) throws EntityNotFoundException {
		new AgentesBaja().delete(id);
	}

	@Override
	public void saveAgente(Agente agente) throws EntityAlreadyExistsException {
		new AgentesAlta().save(agente);
	}

	@Override
	public void updateAgente(Agente agente) throws EntityNotFoundException {
		new AgentesUpdate().update(agente);
	}

}

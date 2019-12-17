package impl.tew.business;

import java.util.List;

import com.tew.business.AgentesService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Agente;

import impl.tew.business.classes.AgentesAlta;
import impl.tew.business.classes.AgentesBaja;
import impl.tew.business.classes.AgentesBuscar;
import impl.tew.business.classes.AgentesListado;
import impl.tew.business.classes.AgentesUpdate;

public class SimpleAgentesService implements AgentesService {

	@Override
	public List<Agente> getAgentes() throws Exception{
		return new AgentesListado().getAgentes();
	}

	@Override
	public void saveAgente(Agente agente) throws EntityAlreadyExistsException {
		new AgentesAlta().save(agente);
	}

	@Override
	public void updateAgente(Agente agente) throws EntityNotFoundException {
		new AgentesUpdate().update(agente);
	}

	@Override
	public void deleteAgente(Long id) throws EntityNotFoundException {
		new AgentesBaja().delete(id);
	}

	@Override
	public Agente findById(Long id) throws EntityNotFoundException {
		return new AgentesBuscar().find(id);
	}

}

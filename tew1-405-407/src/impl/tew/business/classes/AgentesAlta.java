package impl.tew.business.classes;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.infrastructure.Factories;
import com.tew.model.Agente;
import com.tew.persistence.AgenteDao;
import com.tew.persistence.exception.AlreadyPersistedException;

public class AgentesAlta {

	public void save(Agente agente) throws EntityAlreadyExistsException {
		AgenteDao dao = Factories.persistence.createAgenteDao();
		try {
			dao.save(agente);
		}
		catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("Agente ya existe " + agente, ex);
		}
	}

}

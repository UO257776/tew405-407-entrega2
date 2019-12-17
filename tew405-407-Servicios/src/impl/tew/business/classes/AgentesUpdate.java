package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Agente;
import com.tew.persistence.AgenteDao;
import com.tew.persistence.exception.NotPersistedException;

public class AgentesUpdate {

	public void update(Agente agente) throws EntityNotFoundException {
		AgenteDao dao = Factories.persistence.createAgenteDao();
		try {
			dao.update(agente);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Agente no eliminado " + agente, ex);
		}
	}

}

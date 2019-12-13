package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.persistence.AgenteDao;
import com.tew.persistence.exception.NotPersistedException;

public class AgentesBaja {

	public void delete(Long id) throws EntityNotFoundException {
		AgenteDao dao = Factories.persistence.createAgenteDao();
		try {
			dao.delete(id);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Agente no eliminado " + id, ex);
		}
	}
}

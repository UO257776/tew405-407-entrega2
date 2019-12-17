package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.persistence.ClienteDao;
import com.tew.persistence.exception.NotPersistedException;

public class ClientesBaja {

	public void delete(Long id) throws EntityNotFoundException {
		ClienteDao dao = Factories.persistence.createClienteDao();
		try {
			dao.delete(id);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Cliente no eliminado " + id, ex);
		}
	}
}

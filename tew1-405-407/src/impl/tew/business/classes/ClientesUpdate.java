package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Cliente;
import com.tew.persistence.ClienteDao;
import com.tew.persistence.exception.NotPersistedException;

public class ClientesUpdate {

	public void update(Cliente cliente) throws EntityNotFoundException {
		ClienteDao dao = Factories.persistence.createClienteDao();
		try {
			dao.update(cliente);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Cliente no eliminado " + cliente, ex);
		}
	}

}

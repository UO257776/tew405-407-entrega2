package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Cliente;
import com.tew.persistence.ClienteDao;

public class ClientesBuscar {

	public Cliente find(Long id) throws EntityNotFoundException {
		ClienteDao dao = Factories.persistence.createClienteDao();
		Cliente a = dao.findById(id);
		if ( a == null) {
			throw new EntityNotFoundException("No se ha encontrado el cliente");
		}
		
		return a;
	}

}

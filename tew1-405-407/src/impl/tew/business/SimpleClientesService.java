package impl.tew.business;

import java.util.List;

import com.tew.business.ClientesService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Cliente;

import impl.tew.business.classes.ClientesAlta;
import impl.tew.business.classes.ClientesBaja;
import impl.tew.business.classes.ClientesBuscar;
import impl.tew.business.classes.ClientesListado;
import impl.tew.business.classes.ClientesUpdate;

public class SimpleClientesService implements ClientesService {

	@Override
	public List<Cliente> getClientes() throws Exception{
		return new ClientesListado().getClientes();
	}

	@Override
	public void saveCliente(Cliente cliente) throws EntityAlreadyExistsException {
		new ClientesAlta().save(cliente);
	}

	@Override
	public void updateCliente(Cliente cliente) throws EntityNotFoundException {
		new ClientesUpdate().update(cliente);
	}

	@Override
	public void deleteCliente(Long id) throws EntityNotFoundException {
		new ClientesBaja().delete(id);
	}

	@Override
	public Cliente findById(Long id) throws EntityNotFoundException {
		return new ClientesBuscar().find(id);
	}

}

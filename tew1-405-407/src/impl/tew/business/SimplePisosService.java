package impl.tew.business;

import java.util.List;

import com.tew.business.PisosService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Piso;

import impl.tew.business.classes.PisosAlta;
import impl.tew.business.classes.PisosBaja;
import impl.tew.business.classes.PisosBuscar;
import impl.tew.business.classes.PisosListado;
import impl.tew.business.classes.PisosUpdate;

public class SimplePisosService implements PisosService {

	@Override
	public List<Piso> getPisos() throws Exception{
		return new PisosListado().getPisos();
	}

	@Override
	public void savePiso(Piso piso) throws EntityAlreadyExistsException {
		new PisosAlta().save(piso);
	}

	@Override
	public void updatePiso(Piso piso) throws EntityNotFoundException {
		new PisosUpdate().update(piso);
	}

	@Override
	public void deletePiso(Long id) throws EntityNotFoundException {
		new PisosBaja().delete(id);
	}

	@Override
	public Piso findById(Long id) throws EntityNotFoundException {
		return new PisosBuscar().find(id);
	}

}

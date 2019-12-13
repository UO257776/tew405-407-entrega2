package impl.tew.business;

import java.util.List;

import com.tew.business.PisosParaVisitarService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.PisoParaVisitar;

import impl.tew.business.classes.PisosParaVisitarAlta;
import impl.tew.business.classes.PisosParaVisitarBaja;
import impl.tew.business.classes.PisosParaVisitarBuscar;
import impl.tew.business.classes.PisosParaVisitarListado;
import impl.tew.business.classes.PisosParaVisitarUpdate;

public class SimplePisosParaVisitarService implements PisosParaVisitarService {

	@Override
	public List<PisoParaVisitar> getPisosParaVisitar() throws Exception{
		return new PisosParaVisitarListado().getPisosParaVisitar();
	}

	@Override
	public void savePisoParaVisitar(PisoParaVisitar piso) throws EntityAlreadyExistsException {
		new PisosParaVisitarAlta().save(piso);
	}

	@Override
	public void updatePisoParaVisitar(PisoParaVisitar piso) throws EntityNotFoundException {
		new PisosParaVisitarUpdate().update(piso);
	}

	@Override
	public void deletePisoParaVisitar(Long idPiso, Long idCliente) throws EntityNotFoundException {
		new PisosParaVisitarBaja().delete(idPiso, idCliente);
	}

	@Override
	public PisoParaVisitar findById(Long idPiso, Long idCliente) throws EntityNotFoundException {
		return new PisosParaVisitarBuscar().find(idPiso, idCliente);
	}

}

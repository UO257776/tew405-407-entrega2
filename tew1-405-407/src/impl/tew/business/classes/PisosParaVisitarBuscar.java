package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.PisoParaVisitar;
import com.tew.persistence.PisoParaVisitarDao;

public class PisosParaVisitarBuscar {

	public PisoParaVisitar find(Long idPiso, Long idCliente) throws EntityNotFoundException {
		PisoParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		PisoParaVisitar a = dao.findById(idPiso, idCliente);
		if ( a == null) {
			throw new EntityNotFoundException("No se ha encontrado el PisoParaVisitar");
		}
		
		return a;
	}

}

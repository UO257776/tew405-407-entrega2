package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.persistence.PisoParaVisitarDao;
import com.tew.persistence.exception.NotPersistedException;

public class PisosParaVisitarBaja {

	public void delete(Long idPiso, Long idCliente) throws EntityNotFoundException {
		PisoParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		try {
			dao.delete(idPiso, idCliente);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("PisoParaVisitar no eliminado " + idPiso + "-" + idCliente, ex);
		}
	}
}

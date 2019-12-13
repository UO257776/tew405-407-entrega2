package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.PisoParaVisitar;
import com.tew.persistence.PisoParaVisitarDao;
import com.tew.persistence.exception.NotPersistedException;

public class PisosParaVisitarUpdate {

	public void update(PisoParaVisitar piso) throws EntityNotFoundException {
		PisoParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		try {
			dao.update(piso);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("PisoParaVisitar no eliminado " + piso, ex);
		}
	}

}

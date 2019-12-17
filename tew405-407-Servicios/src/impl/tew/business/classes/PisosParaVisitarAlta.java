package impl.tew.business.classes;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.infrastructure.Factories;
import com.tew.model.PisoParaVisitar;
import com.tew.persistence.PisoParaVisitarDao;
import com.tew.persistence.exception.AlreadyPersistedException;

public class PisosParaVisitarAlta {

	public void save(PisoParaVisitar piso) throws EntityAlreadyExistsException {
		PisoParaVisitarDao dao = Factories.persistence.createPisoParaVisitarDao();
		try {
			dao.save(piso);
		}
		catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("PisoParaVisitar ya existe " + piso, ex);
		}
	}

}

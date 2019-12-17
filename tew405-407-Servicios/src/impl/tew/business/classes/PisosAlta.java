package impl.tew.business.classes;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.infrastructure.Factories;
import com.tew.model.Piso;
import com.tew.persistence.PisoDao;
import com.tew.persistence.exception.AlreadyPersistedException;

public class PisosAlta {

	public void save(Piso piso) throws EntityAlreadyExistsException {
		PisoDao dao = Factories.persistence.createPisoDao();
		try {
			dao.save(piso);
		}
		catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("Piso ya existe " + piso, ex);
		}
	}

}

package impl.tew.persistence;


import com.tew.persistence.AgenteDao;
import com.tew.persistence.ClienteDao;
import com.tew.persistence.PersistenceFactory;
import com.tew.persistence.PisoDao;
import com.tew.persistence.PisoParaVisitarDao;

/**
 * Implementaci??????n de la factoria que devuelve implementaci??????n de la capa
 * de persistencia con Jdbc 
 * 
 * @author Enrique
 *
 */
public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public AgenteDao createAgenteDao() {
		// TODO Auto-generated method stub
		return new AgenteJdbcDao();
	}

	@Override
	public ClienteDao createClienteDao() {
		// TODO Auto-generated method stub
		return new ClienteJdbcDao();
	}

	@Override
	public PisoDao createPisoDao() {
		// TODO Auto-generated method stub
		return new PisoJdbcDao();
	}

	@Override
	public PisoParaVisitarDao createPisoParaVisitarDao() {
		// TODO Auto-generated method stub
		return new PisoParaVisitarJdbcDao();
	}

}

package impl.tew.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tew.model.PisoParaVisitar;
import com.tew.persistence.PisoParaVisitarDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;
import com.tew.persistence.exception.PersistenceException;

public class PisoParaVisitarJdbcDao implements PisoParaVisitarDao {

	@Override
	public List<PisoParaVisitar> getPisosParaVisitar() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<PisoParaVisitar> pisos = new ArrayList<PisoParaVisitar>();

		try {
			// En una implemenntaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from pisosparavisitar");
			rs = ps.executeQuery();

			while (rs.next()) {
				PisoParaVisitar piso = new PisoParaVisitar();
				piso.setIdpiso(rs.getLong("IDPiso"));
				piso.setIdcliente(rs.getLong("IDCliente"));
				piso.setFechahoracita(rs.getLong("FechaHoraCita"));
				piso.setEstado(rs.getInt("Estado"));
				
				pisos.add(piso);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return pisos;
	}

	@Override
	public void save(PisoParaVisitar a) throws AlreadyPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"insert into pisosparavisitar (idpiso, idcliente, fechahoracita, estado) " +
					"values (?, ?, ?, ?)");
			
			ps.setLong(1, a.getIdpiso());
			ps.setLong(2, a.getIdcliente());
			ps.setLong(3, a.getFechahoracita());
			ps.setInt(4, a.getEstado());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("pisoparavisitar " + a + " already persisted");
			} 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}

	}

	@Override
	public void update(PisoParaVisitar a) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"update pisosparavisitar " +
					"set idpiso = ?, idcliente = ?, fechahoracita = ?, estado = ?" +
					"where idpiso = ? AND idcliente = ?");
			
			ps.setLong(1, a.getIdpiso());
			ps.setLong(2, a.getIdcliente());
			ps.setLong(3, a.getFechahoracita());
			ps.setInt(4, a.getEstado());
			ps.setLong(5, a.getIdpiso());
			ps.setLong(6, a.getIdcliente());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("PisoParaVisitar " + a + " not found");
			} 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}

	}

	@Override
	public void delete(Long idPiso, Long idCliente) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("delete from pisosparavisitar where idPiso = ? AND idCliente = ?");
			
			ps.setLong(1, idPiso);
			ps.setLong(2, idCliente);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("PisoParaVisitar " + idPiso + "-" + idCliente + " not found");
			} 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}

	}

	@Override
	public PisoParaVisitar findById(Long idPiso, Long idCliente) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		PisoParaVisitar piso = null;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from pisosparavisitar where idPiso = ? AND idCliente = ?");
			ps.setLong(1, idPiso);
			ps.setLong(2, idCliente);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				piso = new PisoParaVisitar();
				
				piso.setIdpiso(rs.getLong("IDPiso"));
				piso.setIdcliente(rs.getLong("IDCliente"));
				piso.setFechahoracita(rs.getLong("FechaHoraCita"));
				piso.setEstado(rs.getInt("Estado"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return piso;
	}

}

package impl.tew.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tew.model.Piso;
import com.tew.persistence.PisoDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;
import com.tew.persistence.exception.PersistenceException;

public class PisoJdbcDao implements PisoDao {

	@Override
	public List<Piso> getPisos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Piso> pisos = new ArrayList<Piso>();

		try {
			// En una implemenntaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a
			// que sacarlas a un sistema de configuraciï¿½ï¿½n:
			// xml, properties, descriptores de despliege, etc
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from pisos");
			rs = ps.executeQuery();

			while (rs.next()) {
				Piso piso = new Piso();
				piso.setId(rs.getLong("ID"));
				piso.setIdagente(rs.getLong("Idagente"));
				piso.setPrecio(rs.getDouble("Precio"));
				piso.setDireccion(rs.getString("Direccion"));
				piso.setCiudad(rs.getString("Ciudad"));
				piso.setAnyo(rs.getInt("Año"));
				piso.setEstado(rs.getInt("Estado"));
				piso.setFoto(rs.getString("Foto"));

				pisos.add(piso);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
				}
			}
			;
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return pisos;
	}

	@Override
	public void save(Piso a) throws AlreadyPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;

		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a
			// que sacarlas a un sistema de configuraciï¿½ï¿½n:
			// xml, properties, descriptores de despliege, etc
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			if (a.getId() != 0L) { //Sentencia en el caso de que se especifique la ID en el nuevo piso
				ps = con.prepareStatement("insert into pisos (id, idagente, precio, direccion, ciudad, año, estado, foto) "
						+ "values (?, ?, ?, ?, ?, ?, ?, ?)");

				ps.setLong(1, a.getId());
				ps.setLong(2, a.getIdagente());
				ps.setDouble(3, a.getPrecio());
				ps.setString(4, a.getDireccion());
				ps.setString(5, a.getCiudad());
				ps.setInt(6, a.getAnyo());
				ps.setInt(7, a.getEstado());
				ps.setString(8, a.getFoto());

			} else { //Sentencia si no se especifica la ID, usa Autoincrement
				ps = con.prepareStatement("insert into pisos (idagente, precio, direccion, ciudad, año, estado, foto) "
						+ "values (?, ?, ?, ?, ?, ?, ?)");

				ps.setLong(1, a.getIdagente());
				ps.setDouble(2, a.getPrecio());
				ps.setString(3, a.getDireccion());
				ps.setString(4, a.getCiudad());
				ps.setInt(5, a.getAnyo());
				ps.setInt(6, a.getEstado());
				ps.setString(7, a.getFoto());
			}

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("piso " + a + " already persisted");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

	}

	@Override
	public void update(Piso a) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;

		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a
			// que sacarlas a un sistema de configuraciï¿½ï¿½n:
			// xml, properties, descriptores de despliege, etc
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("update pisos "
					+ "set idagente = ?, precio = ?, direccion = ?, ciudad = ?, año = ?, estado = ?, foto = ?"
					+ "where id = ?");

			ps.setLong(1, a.getIdagente());
			ps.setDouble(2, a.getPrecio());
			ps.setString(3, a.getDireccion());
			ps.setString(4, a.getCiudad());
			ps.setInt(5, a.getAnyo());
			ps.setInt(6, a.getEstado());
			ps.setString(7, a.getFoto());
			ps.setLong(8, a.getId());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Piso " + a + " not found");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

	}

	@Override
	public void delete(Long id) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;

		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a
			// que sacarlas a un sistema de configuraciï¿½ï¿½n:
			// xml, properties, descriptores de despliege, etc
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("delete from pisos where id = ?");

			ps.setLong(1, id);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Piso " + id + " not found");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

	}

	@Override
	public Piso findById(Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Piso piso = null;

		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a
			// que sacarlas a un sistema de configuraciï¿½ï¿½n:
			// xml, properties, descriptores de despliege, etc
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from pisos where id = ?");
			ps.setLong(1, id);

			rs = ps.executeQuery();
			if (rs.next()) {
				piso = new Piso();

				piso.setId(rs.getLong("ID"));
				piso.setIdagente(rs.getLong("Idagente"));
				piso.setPrecio(rs.getDouble("Precio"));
				piso.setDireccion(rs.getString("Direccion"));
				piso.setCiudad(rs.getString("Ciudad"));
				piso.setAnyo(rs.getInt("Año"));
				piso.setEstado(rs.getInt("Estado"));
				piso.setFoto(rs.getString("Foto"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
				}
			}
			;
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return piso;
	}

}

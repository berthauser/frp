package ar.frp.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import ar.frp.model.Facultad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FacultadDAO {
	// *************************************
	// SELECT (con LIKE) de una/s FACULTAD
	// *************************************
	public static ObservableList<Facultad> buscarFacultad(String cadena)
			throws SQLException, ClassNotFoundException {
		// Declaro la sentencia SELECT
		String selectStmt = "SELECT * FROM facultad WHERE lower(nombre) LIKE lower('%" + cadena + "%')";
		// lower(nombre) LIKE lower('%nal Parana%')

		// Ejecuto la sentencia SELECT
		try {
			// Obtengo resultados del método dbExecuteQuery
			ResultSet rsFacus = DBUtil.dbExecuteQuery(selectStmt);
			// Envío el resultado al método getFacultades y obtengo el objeto Facultad
			ObservableList<Facultad> facuList = getFacultades(rsFacus);
			// Devuelve el objeto Facultad
			return facuList;
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de SQL");
			alert2.setContentText("Imposible mostrar las Facultades" + e);
			e.printStackTrace();
			alert2.showAndWait();
			// Devuelve la excepción
			throw e;
		}
	}

	// *************************************
	// SELECT * de la tabla FACULTAD
	// *************************************
	public static ObservableList<Facultad> buscarFacultades() throws SQLException, ClassNotFoundException {
		// Declaro la sentencia SELECT
		String selectStmt = "SELECT * FROM facultad";
		// Ejecuto la sentencia SELECT
		try {
			// Obtengo resultados del método dbExecuteQuery
			ResultSet rsFacus = DBUtil.dbExecuteQuery(selectStmt);
			// Envío el resultado al método getFacultades y obtengo el objeto Facultad
			ObservableList<Facultad> facuList = getFacultades(rsFacus);
			// Devuelve el objeto Facultad
			return facuList;
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de SQL");
			alert2.setContentText("Imposible mostrar las Facultades" + e);
			e.printStackTrace();
			alert2.showAndWait();
			// Devuelve la excepción
			throw e;
		}
	}

	private static ObservableList<Facultad> getFacultades(ResultSet rs)	throws SQLException, ClassNotFoundException {
		// Declaro una lista Observable que comprende a los objetos facultades
		ObservableList<Facultad> facuList = FXCollections.observableArrayList();
		while (rs.next()) {
			Facultad facu = new Facultad();
			facu.setId_facultad(rs.getInt("id_facultad"));
			facu.setNombre(rs.getString("nombre"));
			facu.setDireccion(rs.getString("direccion"));
			facu.setCuit(rs.getString("cuit"));
			facu.setSucursal(rs.getInt("sucursal"));
			facu.setTelefono(rs.getString("telefonos"));
			facu.setCorreo(rs.getString("email"));
			facu.setDefecto(rs.getBoolean("defecto"));
			facuList.add(facu);
		}
		// Devuelvo facuList (ObservableList de Facultades)
		return facuList;
	}

	// *************************************
	// INSERT a la tabla de FACULTAD
	// *************************************
	public static void insertarFacultad(Integer idFacultad, String nombre, String direccion, String cuit, Integer sucursal,
			String telefonos, String email, Boolean defecto)
			throws SQLException, ClassNotFoundException {
		// Declaro la sentencia INSERT
		String updateStmt = "INSERT INTO facultad (ID_FACULTAD, NOMBRE, DIRECCION, CUIT, SUCURSAL, TELEFONOS, EMAIL, DEFECTO)\n" +
				"VALUES ('" + idFacultad +"', '" + nombre + "', '" + direccion + "', '" + cuit + "', " + sucursal + ", '" + telefonos
				+ "', '" + email + "', " + defecto + ")";
		// Ejecuto la operación INSERT
		try {
			DBUtil.dbExecuteUpdate(updateStmt);

		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de SQL");
			alert2.setContentText("Imposible dar de Alta la Facultad" + e);
			e.printStackTrace();
			alert2.showAndWait();
			// Devuelve la excepción
			throw e;
		}
	}

	// *************************************
	// UPDATE de la tabla FACULTAD
	// *************************************
	public static void modificarFacultad(Integer idFacultad, String nombre, String direccion, String cuit,
			Integer sucursal, String telefonos, String email, Boolean defecto)
			throws SQLException, ClassNotFoundException {
		// Declaro la sentencia INSERT
		String updateStmt = "UPDATE facultad set NOMBRE = '" + nombre + "'," + "DIRECCION = '" + direccion + "',"
				+ "CUIT = '" + cuit + "'," + "SUCURSAL = " + sucursal + "," + "TELEFONOS = '" + telefonos + "',"
				+ "EMAIL = '" + email + "'," + "DEFECTO = " + defecto + " WHERE id_facultad = " + idFacultad;
		// Ejecuto la operación UPDATE
		try {
			DBUtil.dbExecuteUpdate(updateStmt);
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de SQL");
			alert2.setContentText("Imposible modificar la Facultad" + e);
			e.printStackTrace();
			alert2.showAndWait();
			// Devuelve la excepción
			throw e;
		}
	}

	// *************************************
	// DELETE de una FACULTAD
	// *************************************
	public static void borrarFacultad(Integer idFacultad) throws SQLException, ClassNotFoundException {
		// Declaro la sentencia DELETE
		String updateStmt = "DELETE FROM facultad"
				+ " WHERE id_facultad = " + idFacultad;
		// Ejecuto la sentencia DELETE
		try {
			DBUtil.dbExecuteUpdate(updateStmt);

		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de SQL");
			alert2.setContentText("Imposible dar de Baja la Facultad" + e);
			e.printStackTrace();
			alert2.showAndWait();
			// Devuelve la excepción
			throw e;
		}
	}
}
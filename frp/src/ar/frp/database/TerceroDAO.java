package ar.frp.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import ar.frp.model.Tercero;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TerceroDAO {
	// *************************************
	// SELECT (con LIKE) de un TERCERO
	// *************************************
	public static ObservableList<Tercero> buscarTercero(String cadena)
			throws SQLException, ClassNotFoundException {
		// Declaro la sentencia SELECT
		String selectStmt = "SELECT * FROM terceros WHERE lower(nombre) LIKE lower('%" + cadena + "%')";
		// lower(nombre) LIKE lower('%nal Parana%')

		// Ejecuto la sentencia SELECT
		try {
			// Obtengo resultados del m�todo dbExecuteQuery
			ResultSet rsTer = DBUtil.dbExecuteQuery(selectStmt);
			// Env�o el resultado al m�todo getFacultades y obtengo el objeto Facultad
			ObservableList<Tercero> terceroList = getTerceros(rsTer);
			// Devuelve el objeto Facultad
			return terceroList;
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de SQL");
			alert2.setContentText("Imposible mostrar los Terceros" + e);
			e.printStackTrace();
			alert2.showAndWait();
			// Devuelve la excepci�n
			throw e;
		}
	}

	// *******************************
	// SELECT Terceros
	// *******************************
	public static ObservableList<Tercero> buscarTerceros() throws SQLException, ClassNotFoundException {
		// Declaro la sentencia SELECT
		String selectStmt = "SELECT * FROM terceros";

		// Ejecuto la sentencia SELECT
		try {
			// Obtengo el ResultSet del m�todo dbExecuteQuery
			ResultSet rsTers = DBUtil.dbExecuteQuery(selectStmt);
			// Env�o el ResultSet al m�todo getListaTercero y obtengo un objeto "tercero"
			ObservableList<Tercero> terList = getTerceros(rsTers);
			// Devuelvo un objeto Tercero
			return terList;
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de SQL");
			alert2.setContentText("No existen Terceros con ese criterio" + e);
			e.printStackTrace();
			alert2.showAndWait();
			// Devuelve la excepci�n
			throw e;
		}
	}

	// Operaci�n Select * from terceros
	private static ObservableList<Tercero> getTerceros(ResultSet rs) throws SQLException, ClassNotFoundException {
		// Declare a observable List which comprises of Employee objects
		ObservableList<Tercero> terList = FXCollections.observableArrayList();
		while (rs.next()) {
			Tercero ter = new Tercero();
			ter.setId_tercero(Integer.parseInt(rs.getString("ID_TERCERO")));
			ter.setNombre(rs.getString("NOMBRE"));
			ter.setCuitl(rs.getString("CUITL"));
			ter.setSitiva(rs.getString("SITIVA"));
			ter.setDireccion(rs.getString("DIRECCION"));
			ter.setLocalidad(rs.getString("LOCALIDAD"));
			ter.setProvincia(rs.getString("PROVINCIA"));
			ter.setTelefono(rs.getString("TELEFONOS"));
			ter.setSaldoApertura(rs.getDouble("SALDO_APERTURA"));
			ter.setTipoSaldo(rs.getString("TIPO_SALDO"));
			// Agrego un Tercero a la ObservableList
			terList.add(ter);
		}
		// devuelvo terList (ObservableList de Terceros)
		return terList;
	}

	// *************************************
	// DELETE de un TERCERO
	// *************************************
	public static void borrarTercero(Integer IdTercero) throws SQLException, ClassNotFoundException {
		// Declaro la sentencia DELETE
		String updateStmt = "DELETE FROM terceros" + " WHERE id_tercero = " + IdTercero;
		// Ejecuto la sentencia DELETE
		try {
			DBUtil.dbExecuteUpdate(updateStmt);
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de SQL");
			alert2.setContentText("Imposible dar de Baja el Tercero" + e);
			e.printStackTrace();
			alert2.showAndWait();
			// Devuelve la excepci�n
			throw e;
		}
	}

	// *************************************
	// INSERT un tercero
	// *************************************
	public static void insertarTercero(String nombre, String cuitl, String sitiva, String direccion, String localidad,
			String provincia, String telefonos, Double saldo_apertura, String tipo_saldo)
			throws SQLException, ClassNotFoundException {
		// Declaro la sentencia INSERT
		String updateStmt = "INSERT INTO terceros (NOMBRE, CUITL, SITIVA, DIRECCION, LOCALIDAD, PROVINCIA, TELEFONOS, SALDO_APERTURA, TIPO_SALDO)\n"
				+ "VALUES ('" + nombre + "', '" + cuitl + "', '" + sitiva + "', '" + direccion + "', '" + localidad
				+ "', '" + provincia + "', '" + telefonos + "', " + saldo_apertura + ", '" + tipo_saldo + "')";
		// Ejecuto la operaci�n INSERT
		try {
			DBUtil.dbExecuteUpdate(updateStmt);
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de SQL");
			alert2.setContentText("Imposible dar de Alta" + e);
			e.printStackTrace();
			alert2.showAndWait();
			// Devuelve la excepci�n
			throw e;
		}
	}

	// *************************************
	// UPDATE de la tabla TERCEROS
	// *************************************
	public static void modificarTercero(Integer idTercero, String nombre, String cuit, String sitiva, String direccion,
			String localidad, String provincia, String telefonos, Double saldo_apertura, String tipo_saldo)
			throws SQLException, ClassNotFoundException {
		// Declaro la sentencia UPDATE
		String updateStmt = "UPDATE terceros set NOMBRE = '" + nombre + "'," + "CUITL = '" + cuit + "'," + "SITIVA = '"
				+ sitiva + "'," + "DIRECCION = '" + direccion + "'," + "LOCALIDAD = '" + localidad + "',"
				+ "PROVINCIA = '" + provincia + "'," + "TELEFONOS = '" + telefonos + "'," + "SALDO_APERTURA = "
				+ saldo_apertura + "," + "TIPO_SALDO = '" + tipo_saldo + "' " + " WHERE id_tercero = " + idTercero;
		// Ejecuto la operaci�n UPDATE
		try {
			DBUtil.dbExecuteUpdate(updateStmt);
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de SQL");
			alert2.setContentText("Imposible modificar el Tercero" + e);
			e.printStackTrace();
			alert2.showAndWait();
			// Devuelve la excepci�n
			throw e;
		}
	}
}
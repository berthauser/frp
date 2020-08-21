package ar.frp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/*
 * Creado por bertHäuser - 17/10/2017
 * 
 */

public class DBUtil {
	// Conexión
	public static Connection conn = null;
	// String de conexión
	private static final String connStr = "jdbc:postgresql://192.168.0.10:5432/terceros";
//	private static final String connStr = "jdbc:postgresql://localhost:5432/terceros";

	// Conectar a la BD
	public static void dbConnect() throws SQLException, ClassNotFoundException {
		// Trato de establecer la Conexión a la BD
		try {
			conn = DriverManager.getConnection(connStr, "postgres", "");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Información");
			alert.setHeaderText(null);
			alert.setContentText("¡Conexión exitosa a la BD!");
			alert.showAndWait();
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de Conexión");
			alert2.setHeaderText("¡No hay conexión a la BD!");
			alert2.setContentText("Falló la Conexión" + e);
			e.printStackTrace();
			alert2.showAndWait();
			throw e;
		}
	}
	
	// Cerrar la Conexión a la BD
	public static void dbDisconnect() throws SQLException {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}		
		} catch (Exception e) {
			throw e;
		}
	}

	// Operaciones DDL sobre la BD
	@SuppressWarnings("null")
	public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
		// Declaro null todos los argumentos del método
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			// Creo el objeto query
			stmt = conn.createStatement();
			// Ejecuto la operación query (SELECT)
			resultSet = stmt.executeQuery(queryStmt);
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de SQL");
			alert2.setContentText("Problema en la ejecución del Query" + e);
			e.printStackTrace();
			alert2.showAndWait();
			throw e;
		} finally {
			if (resultSet == null) {
				resultSet.close();
			}
			if (stmt == null) {
				stmt.close();
			}
		}
		return resultSet;
	}

	// Operaciones DDL para Update/Insert/Delete
	public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
		// Declaro statement como null
		Statement stmt = null;
		try {
			// Creo el objeto Statement
			stmt = conn.createStatement();
			// Ejecuto operaciones con executeUpdate con las sentencias SQL
			stmt.executeUpdate(sqlStmt);
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de SQL");
			alert2.setContentText("Problema con operación de Update/Insert/Delete" + e);
			e.printStackTrace();
			alert2.showAndWait();
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
}
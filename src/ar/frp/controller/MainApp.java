package ar.frp.controller;

import java.io.IOException;
import java.sql.SQLException;

import ar.frp.database.DBUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp {
	@FXML
	private MenuItem mnmItmDatosFacultad;
	@FXML
	private MenuItem mnmItmSalir;
	@FXML
	private MenuItem mnmItmCrearFactura;
	@FXML
	private MenuItem mnmItmTerceros;
	@FXML
	private Button btnConectar;
	@FXML
	private Button btnDesconectar;
	@FXML
	private Button btnSalir;

	public MainApp() {

	}

	@FXML
	private void initialize() {

		mnmItmDatosFacultad.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/ar/frp/view/Facultad.fxml"));

				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				
				Scene scene = new Scene(loader.load());
				stage.centerOnScreen();
				stage.setScene(scene);
				stage.show();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		mnmItmCrearFactura.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/ar/frp/view/Facturacion.fxml"));

				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				
				Scene scene = new Scene(loader.load());
				stage.centerOnScreen();
				stage.setScene(scene);
				stage.show();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		mnmItmTerceros.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/ar/frp/view/Tercero.fxml"));

				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				
				Scene scene = new Scene(loader.load());
				stage.centerOnScreen();
				stage.setScene(scene);
				stage.show();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		btnConectar.setOnAction(e -> {
			try {
				DBUtil.dbConnect();
			} catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		});
		
		btnSalir.setOnAction(e -> {
			try {
				DBUtil.dbDisconnect();
				Platform.exit();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		mnmItmSalir.setOnAction(e -> {
			try {
				DBUtil.dbDisconnect();
				Platform.exit();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
	}
}
package ar.frp.principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ar/frp/view/MainApp.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainApp.fxml"));
			Scene scene = new Scene(loader.load());
			stage.setTitle("Servicios a Terceros UTN - Facultad Regional Paraná");
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

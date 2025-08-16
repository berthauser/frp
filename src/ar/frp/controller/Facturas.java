package ar.frp.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import ar.frp.database.TerceroDAO;
import ar.frp.model.Tercero;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Facturas {
	private File archivo = null;
	private FileReader fr = null;
	private BufferedReader br = null;

	@FXML
	private Label lblUltimaFactura;
	@FXML
	private Label lblUltimaFechaFactura;
	@FXML
	private TableView<?> tblFactura;
	@FXML
	private TableColumn<?, ?> colCantidad;
	@FXML
	private TableColumn<?, ?> colDetalle;
	@FXML
	private TableColumn<?, ?> colMonto;
	@FXML
	private JFXComboBox<Tercero> cmbTerceros;
	
	@FXML
	private JFXTextField txtCuitCuil;
	@FXML
	private JFXComboBox<String> cmbSituacionIva;
	private ObservableList<String> ivaData = FXCollections.observableArrayList("Responsable Inscripto",
			"Responsable No Inscripto", "IVA No Responsable", "Sujeto Exento", "Consumidor Final",
			"Responsable Monotributo", "Sujeto no Categorizado", "Proveedor del Exterior", "Cliente del Exterior",
			"IVA Liberado – Ley Nº 19.640", "Responsable Inscripto – Agente de Percepción",
			"Pequeño Contribuyente Eventual", "Monotributista Social", "Pequeño Contribuyente Eventual Social");
	@FXML
	private JFXTextField txtDomicilio;
	@FXML
	private JFXTextField txtLocalidad;
	@FXML
	private JFXComboBox<String> cmbProvincia;
	private ObservableList<String> provinciasData = FXCollections.observableArrayList("AR-B (Buenos Aires)",
			"AR-K (Catamarca)", "AR-H (Chaco)", "AR-U (Chubut)", "AR-X (Córdoba)", "AR-W (Corrientes)",
			"AR-E (Entre Ríos)", "AR-P (Formosa)", "AR-Y (Jujuy)", "AR-L (La Pampa)", "AR-F (La Rioja)",
			"AR-M (Mendoza)", "AR-N (Misiones)", "AR-Q (Neuquén)", "AR-R (Río Negro)", "AR-A (Salta)",
			"AR-J (San Juan)", "AR-D (San Luis)", "AR-Z (Santa Cruz)", "AR-S (Santa Fe)", "AR-G (Santiago del Estero)",
			"AR-V (Tierra del Fuego)", "AR-T (Tucumán)");
	@FXML
	private TextField txtCantidad;
	@FXML
	private TextArea txaDetalle;
	@FXML
	private TextField txtMonto;
	@FXML
	private JFXButton btnEliminarItem;
	@FXML
	private JFXButton btnAgregarItem;
	@FXML
	private Label lblGranTotal;
	@FXML
	private JFXButton btnImprimirFactura;
	@FXML
	private JFXButton btnCancelarFactura;
	@FXML
	private JFXButton btnSalir;

	/**
	 * Este constructor se llama ANTES del método initialize(). No debe tener
	 * argumentos
	 */
	public Facturas() {

	}

	@FXML
	public void initialize() {

		btnSalir.setOnAction(event -> {
			Platform.exit();
			System.exit(0);
		});

		try {
			archivo = new File("C:\\Users\\ernesto\\eclipsews\\frpRecipes\\bin\\resources\\factura.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			// Lectura del archivo
			String linea;
			int i = 1;
			while ((linea = br.readLine()) != null) {
				if (i == 1) {
					lblUltimaFactura.setText("0013-" + linea);
					++i;
				} else
					lblUltimaFechaFactura.setText(linea);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el archivo, para asegurarnos
			// que se cierra tanto si todo va bien como si salta 
			// una excepción.
			try {                    
				if (null != fr) {   
					fr.close();     
				}                  
			} catch (Exception e2) { 
				e2.printStackTrace();
			}
		}

		cmbSituacionIva.setItems(ivaData);
		cmbProvincia.setItems(provinciasData);

		Platform.runLater(() -> cmbTerceros.requestFocus());

	};

	@FXML
	private void mostrarTerceros() throws SQLException, ClassNotFoundException {
		try {
			// Obtengo la información de todos los terceros
			ObservableList<Tercero> tercerosData = TerceroDAO.buscarTerceros();
			cmbTerceros.setItems(tercerosData);

		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setTitle("Error de SQL");
			alert2.setContentText("Imposible mostrar los Terceros" + e);
			e.printStackTrace();
			alert2.showAndWait();
			// Devuelve la excepción
			throw e;
		}
	}
};

	// txtUltimaFactura.setOnAction((event) -> {
	// // Button was clicked, do something...
	// outputTextArea.appendText("Button Action\n");
	// });

	/**
	 * El método setMainApp(...) debe ser invocado desde la clase MainApp. Esto nos
	 * da la oportunidad de acceder al objeto MainApp para obtener la lista de
	 * responsables frente al IVA y otras cosas
	 * 
	 * @param mainApp
	 */
	/*
	 * public void setMainApp(MainApp mainApp) { this.mainApp = mainApp;
	 * 
	 * cmbIva.getItems().clear(); // Agrego la lista "observable" al combobox
	 * cmbIva.setItems(mainApp.getSitIva());
	 * //cmbIva.getSelectionModel().selectFirst();
	 * cmbIva.getSelectionModel().getSelectedItem();
	 * 
	 * cmbIva.setCellFactory(new Callback<ListView<Iva>, ListCell<Iva>>() {
	 * 
	 * @Override public ListCell<Iva> call(ListView<Iva> param) { // TODO
	 * Auto-generated method stub return new ListCell<Iva>(){
	 * 
	 * @Override public void updateItem(Iva item, boolean empty){
	 * super.updateItem(item, empty); if(!empty) { setText(item.getSitIva());
	 * setGraphic(null); } else { setText(null); } } }; } });
	 * 
	 * // Define rendering of selected value shown in ComboBox.
	 * cmbIva.setConverter(new StringConverter<Iva>() { public String toString(Iva
	 * iva) { if (iva == null) { return null; } else { return iva.getSitIva(); } }
	 * 
	 * public Iva fromString(String ivaString) { return null; // No conversion
	 * fromString needed. } });
	 * 
	 * cmbProvincias.getItems().clear();
	 * cmbProvincias.setItems(mainApp.getListaProvincias());
	 * cmbProvincias.getSelectionModel().getSelectedItem();
	 * 
	 * cmbProvincias.setCellFactory(new Callback<ListView<Provincias>,
	 * ListCell<Provincias>>() {
	 * 
	 * @Override public ListCell<Provincias> call(ListView<Provincias> param) { //
	 * TODO Auto-generated method stub return new ListCell<Provincias>(){
	 * 
	 * @Override public void updateItem(Provincias item, boolean empty){
	 * super.updateItem(item, empty); if(!empty) { setText(item.getNombre());
	 * setGraphic(null); } else { setText(null); } } }; } });
	 */
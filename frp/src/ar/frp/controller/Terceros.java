package ar.frp.controller;

import java.sql.SQLException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import ar.frp.database.DBUtil;
import ar.frp.database.TerceroDAO;
import ar.frp.model.Tercero;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Terceros {
	// Variables para el ID del Tercero y el "index" de la tabla; a la primera la
	// uso en el evento de la tabla de Terceros y la segunda para remover
	// "visualmente" de la tabla el ítem seleccionado
	private Integer idTercero;
	private Integer inxTabla;
	private String tipoIva, provincia, tipoSaldo;
	// Tabla para Terceros
	@FXML
	private TableView<Tercero> tblTerceros;
	@FXML
	private TableColumn<Tercero, String> colId;
	@FXML
	private TableColumn<Tercero, String> colApyNom;
	@FXML
	private TableColumn<Tercero, String> colCuitCuil;
	// Fina Tabla de Terceros
	@FXML
	private JFXTextField txtBuscar;
	@FXML
	private JFXButton btnInsertar;
	@FXML
	private JFXButton btnBorrar;
	@FXML
	private JFXButton btnModificar;
	@FXML
	private JFXButton btnImprimir;
	@FXML
	private JFXButton btnLimpiar;
	@FXML
	private JFXButton btnSalir;
	@FXML
	private JFXTextField txtApellidoyNombres;
	@FXML
	private JFXTextField txtCuilCuit;
	@FXML
	private JFXComboBox<String> cmbSitIva;
	private ObservableList<String> ivaData = FXCollections.observableArrayList("Responsable Inscripto",
			"Responsable No Inscripto", "IVA No Responsable", "Sujeto Exento", "Consumidor Final",
			"Responsable Monotributo", "Sujeto no Categorizado", "Proveedor del Exterior", "Cliente del Exterior",
			"IVA Liberado – Ley Nº 19.640", "Responsable Inscripto – Agente de Percepción",
			"Pequeño Contribuyente Eventual", "Monotributista Social", "Pequeño Contribuyente Eventual Social");
	@FXML
	private JFXTextField txtDireccion;
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
	private JFXTextField txtTelefonos;
	@FXML
	private JFXTextField txtSaldoApertura;
	@FXML
	private JFXComboBox<String> cmbTipoSaldo;
	private ObservableList<String> saldosData = FXCollections.observableArrayList("Ninguno", "Deudor", "Acreedor");
	@FXML
	private JFXButton btnBuscar;
	@FXML
	private JFXButton btnBuscarTodos;

	/**
	 * Este constructor se llama ANTES del método initialize(). No debe tener
	 * argumentos
	 */
	public Terceros() {
		// TODO Auto-generated constructor stub
	}
	// Inicializo la clase controladora
	// Este se llama automáticamente después que el archivo fxml se ha cargado.
	public void initialize() {
		// TODO Auto-generated method stub
		/**
		 * Inicializo la tabla de Terceros con sus columnas. El método
		 * setCellValueFactory(...) que aplico sobre las columnas de la tabla se usa
		 * para determinar qué atributos de la clase TerceroModel deben ser usados para
		 * cada columna particular.
		 */
		colId.setCellValueFactory(cellData -> cellData.getValue().id_terceroProperty().asString());
		colApyNom.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		colCuitCuil.setCellValueFactory(cellData -> cellData.getValue().cuitlProperty());
		cmbSitIva.setItems(ivaData);
		cmbProvincia.setItems(provinciasData);
		cmbTipoSaldo.setItems(saldosData);

		/**
		 * Para enterarse de que el usuario ha seleccionado a un tercero en la tabla
		 * de terceros, necesitamos "escuchar" los cambios. Esto se consigue mediante
		 * la implementación de un interface de JavaFX que se llama ChangeListener con
		 * un método que sólo tiene tres parámetros: observable, oldValue, y newValue.
		 */
		tblTerceros.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetallesTercero(newValue));

		// Foco al primer campo
		Platform.runLater(() -> {
			txtBuscar.requestFocus();
		});

		/**
		 * Evento para la tabla de Terceros. Cada vez que se "clickea" sobre la fila de
		 * la tabla, se produce este evento y obtengo el dato de la columna que preciso;
		 * en este caso el ID del Tercero, luego se lo asigno a la variable de clase
		 */
		tblTerceros.setOnMouseClicked(event -> {
			ObservableList<Tercero> tercerosData;
			tercerosData = tblTerceros.getSelectionModel().getSelectedItems();
			for (Tercero tercero : tercerosData) {
				idTercero = tercero.getId_tercero();
			}
		});

		btnLimpiar.setOnAction(event -> {
			txtBuscar.clear();
			mostrarDetallesTercero(null);
			tblTerceros.getItems().clear();
			txtBuscar.requestFocus();
		});

		btnSalir.setOnAction(event -> {
			((Node) (event.getSource())).getScene().getWindow().hide();
		});

		btnInsertar.setOnAction(event -> {
			try {
				insertarTercero();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		btnBorrar.setOnAction(event -> {
			try {
				borrarTercero();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		btnModificar.setOnAction(event -> {
			try {
				modificarTercero();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		btnImprimir.setOnAction(event -> {
			String reportPath = "C:\\Users\\ernesto\\eclipsews\\frpRecipes\\bin\\resources\\reports\\repTerceros.jrxml";
			try {
				JasperReport jr = JasperCompileManager.compileReport(reportPath);
				JasperPrint jp = JasperFillManager.fillReport(jr, null, DBUtil.conn);
				JasperViewer.viewReport(jp);
			} catch (JRException e) {
				e.printStackTrace();
			}
		});

		btnBuscar.setOnAction(event -> {
			try {
				mostrarTercero(txtBuscar.getText());
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		btnBuscarTodos.setOnAction(event -> {
			try {
				mostrarTerceros();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		cmbSitIva.setOnAction(event -> {
			tipoIva = cmbSitIva.getValue();
		});

		cmbProvincia.setOnAction(event -> {
			provincia = cmbProvincia.getValue();
		});

		cmbTipoSaldo.setOnAction(event -> {
			tipoSaldo = cmbTipoSaldo.getValue();
		});

		/**
		 * Limpio todos los campos de texto
		 */
		mostrarDetallesTercero(null);
	}
	
	private void mostrarTerceros() throws SQLException, ClassNotFoundException {
		try {
			// Obtengo la información de todos los terceros
			ObservableList<Tercero> tercerosData = TerceroDAO.buscarTerceros();
			populateTerceros(tercerosData);
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

	private void mostrarTercero(String cadena) throws SQLException, ClassNotFoundException {
		try {
			// Obtengo la información de todos los terceros (con %LIKE%)
			ObservableList<Tercero> tercerosData = TerceroDAO.buscarTercero(cadena);
			populateTerceros(tercerosData);
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setTitle("Error de SQL");
			alert2.setContentText("Imposible mostrar los Terceros con ese criterio" + e);
			e.printStackTrace();
			alert2.showAndWait();
			// Devuelve la excepción
			throw e;
		}
	}

	private void modificarTercero() throws SQLException, ClassNotFoundException {
		try {
			if (idTercero != null) {
				TerceroDAO.modificarTercero(idTercero, txtApellidoyNombres.getText(), txtCuilCuit.getText(), tipoIva,
						txtDireccion.getText(), txtLocalidad.getText(), provincia, txtTelefonos.getText(),
						Double.parseDouble(txtSaldoApertura.getText()), tipoSaldo);

				mostrarDetallesTercero(null);
				txtBuscar.requestFocus();
			} else {
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Error de SQL");
				alert2.setContentText("Seleccione un Tercero para modificarlo");
				alert2.showAndWait();
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	private void mostrarDetallesTercero(Tercero tercero) {
		if (tercero != null) {
			txtApellidoyNombres.setText(tercero.getNombre());
			txtCuilCuit.setText(tercero.getCuitl());
			cmbSitIva.setValue(tercero.getSitiva());
			txtDireccion.setText(tercero.getDireccion());
			txtLocalidad.setText(tercero.getLocalidad());
			cmbProvincia.setValue(tercero.getProvincia());
			txtTelefonos.setText(tercero.getTelefono());
			txtSaldoApertura.setText(String.valueOf(tercero.getSaldoApertura()));
			cmbTipoSaldo.setValue(tercero.getTipoSaldo());
		} else {
			txtApellidoyNombres.clear();
			txtCuilCuit.clear();
			txtDireccion.clear();
			txtLocalidad.clear();
			txtSaldoApertura.clear();
			txtTelefonos.clear();
			cmbProvincia.valueProperty().set(null);
			cmbSitIva.valueProperty().set(null);
			cmbTipoSaldo.valueProperty().set(null);
		}
	}
	// Busco un tercero
	@FXML
	private void buscarTercero(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		try {
			// Obtengo información del Tercero
			ObservableList<Tercero> terceroData = TerceroDAO.buscarTercero(txtBuscar.getText());
			// Populate Tercero en la TableView y lo muestra en el TextArea
			populateTerceros(terceroData);
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de Consulta");
			alert2.setContentText("No existe un Tercero con ese criterio" + e);
			e.printStackTrace();
			alert2.showAndWait();
			throw e;
		}
	}

	// Busco todos los Terceros
	@FXML
	private void buscarTerceros(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
		try {
			// Obtengo toda la información de los Terceros
			ObservableList<Tercero> terceroData = TerceroDAO.buscarTerceros();
			// Populate Terceros en la tabla
			populateTerceros(terceroData);
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de Consulta");
			alert2.setContentText("No existen Terceros ingresados" + e);
			e.printStackTrace();
			alert2.showAndWait();
			throw e;
		}
	}

	// Puebla (populates) con terceros la tabla
	private void populateTerceros(ObservableList<Tercero> tercerosData) {
		// setea los ítems a la tabla
		tblTerceros.setItems(tercerosData);
	}

	// Insertar un Tercero en la BD
	@FXML
	private void insertarTercero() throws SQLException, ClassNotFoundException {
		try {
			TerceroDAO.insertarTercero(txtApellidoyNombres.getText(), txtCuilCuit.getText(),
					cmbSitIva.getSelectionModel().getSelectedItem().toString(), txtDireccion.getText(),
					txtLocalidad.getText(), cmbProvincia.getSelectionModel().getSelectedItem().toString(),
					txtTelefonos.getText(), Double.parseDouble(txtSaldoApertura.getText()),
					cmbTipoSaldo.getSelectionModel().getSelectedItem().toString());
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Error de Inserción");
			alert2.setContentText("Imposible insertar el Tercero" + e);
			e.printStackTrace();
			alert2.showAndWait();
			throw e;
		}
	}

	/**
	 * Uso la variable de clase "idTercero" obtenida del evento "setOnMouseClicked"
	 * de la tabla de Terceros
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unlikely-arg-type")
	private void borrarTercero() throws SQLException, ClassNotFoundException {
		try {
			if (idTercero != null) {
				TerceroDAO.borrarTercero(idTercero);
				inxTabla = tblTerceros.getSelectionModel().getSelectedIndex();
				tblTerceros.getItems().remove(inxTabla);
			} else {
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Error de SQL");
				alert2.setContentText("Seleccione un Tercero para borrarlo");
				alert2.showAndWait();
			}
		} catch (SQLException e) {
			throw e;
		}
	}
}
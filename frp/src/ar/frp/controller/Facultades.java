package ar.frp.controller;

import java.sql.SQLException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import ar.frp.database.DBUtil;
import ar.frp.database.FacultadDAO;
import ar.frp.model.Facultad;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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

public class Facultades {
	// Variables para el ID de la facultad y el "index" de la tabla; a la primera la
	// uso en el evento de la tabla de Facultades y la segunda para remover
	// "visualmente" de la tabla el ítem seleccionado
	private Integer idFacultad;
	private Integer inxTabla;
	@FXML
	private JFXButton btnInsertar;
	@FXML
	private JFXButton btnBorrar;
	@FXML
	private JFXButton btnModificar;
	@FXML
	private JFXButton btnSalir;
	@FXML
	private JFXButton btnImprimir;
	@FXML
	private JFXButton btnLimpiar;
	@FXML
	private JFXButton btnBuscar;
	@FXML
	private JFXButton btnBuscarTodos;
	@FXML
	private JFXTextField txtBuscar;
	// Tabla para las Facultades
	@FXML
	private TableView<Facultad> tblFacultad;
	@FXML
	private TableColumn<Facultad, String> colId;
	@FXML
	private TableColumn<Facultad, String> colNombre;
	@FXML
	private TableColumn<Facultad, String> colDireccion;
	@FXML
	private TableColumn<Facultad, String> colCuit;
	@FXML
	private TableColumn<Facultad, Integer> colSucursal;
	@FXML
	private TableColumn<Facultad, String> colTelefonos;
	@FXML
	private TableColumn<Facultad, String> colCorreo;
	@FXML
	private TableColumn<Facultad, Boolean> colDefecto;
	// Fin Tabla para Facultades
	@FXML
	private JFXTextField txtFacultad;
	@FXML
	private JFXTextField txtDireccion;
	@FXML
	private JFXTextField txtCuit;
	@FXML
	private JFXTextField txtSucursal;
	@FXML
	private JFXTextField txtTelefonos;
	@FXML
	private JFXTextField txtCorreo;
	@FXML
	private JFXTextField txtEmail;
	@FXML
	private JFXCheckBox chbDefecto;

	/**
	 * Este constructor se llama ANTES del método initialize(). No debe tener
	 * argumentos
	 */
	public Facultades() {
		
	}

	@FXML
	public void initialize() {
		/**
		 * Inicializo la tabla de Facultades con sus columnas. El método
		 * setCellValueFactory(...) que aplico sobre las columnas de la tabla se usa
		 * para determinar qué atributos de la clase Facultad en el paquete "model"
		 * deben ser usados para cada columna particular.
		 */
		colId.setCellValueFactory(ColData -> ColData.getValue().id_facultadProperty().asString());
		colNombre.setCellValueFactory(ColData -> ColData.getValue().nombreProperty());
		colDireccion.setCellValueFactory(ColData -> ColData.getValue().direccionProperty());
		colCuit.setCellValueFactory(ColData -> ColData.getValue().cuitProperty());
		colSucursal.setCellValueFactory(ColData -> ColData.getValue().sucursalProperty().asObject());
		colTelefonos.setCellValueFactory(ColData -> ColData.getValue().telefonoProperty());
		colCorreo.setCellValueFactory(ColData -> ColData.getValue().correoProperty());
		colDefecto.setCellValueFactory(ColData -> ColData.getValue().defectoProperty());

		/**
		 * Limpio todos los campos de texto
		 */
		mostrarDetallesFacultad(null);

		tblFacultad.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetallesFacultad(newValue));

		// Foco al primer campo
		Platform.runLater(() -> {
			txtBuscar.requestFocus();
		});

		/**
		 * Evento para la tabla de Facultad. Cada vez que se "clickea" sobre la fila de
		 * la tabla, se produce este evento y obtengo el dato de la columna que preciso;
		 * en este caso el ID de la facultad, luego se lo asigno a la variable de clase
		 */
		tblFacultad.setOnMouseClicked(event -> {
			ObservableList<Facultad> facultadesData;
			facultadesData = tblFacultad.getSelectionModel().getSelectedItems();
			for (Facultad facultad : facultadesData) {
				idFacultad = facultad.getId_facultad();
			}
		});

		btnLimpiar.setOnAction(event -> {
			txtBuscar.clear();
			mostrarDetallesFacultad(null);
			tblFacultad.getItems().clear();
			txtBuscar.requestFocus();
		});

		btnSalir.setOnAction(event -> {
			((Node) (event.getSource())).getScene().getWindow().hide();
			
		});

		btnInsertar.setOnAction(event -> {
			try {
				insertarFacultad();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});

		btnBorrar.setOnAction(event -> {
			try {
				borrarFacultad();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});

		btnModificar.setOnAction(event -> {
			try {
				modificarFacultad();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});

		btnImprimir.setOnAction(event -> {
			String reportPath = "C:\\Users\\ernesto\\eclipsews\\frpRecipes\\bin\\resources\\reports\\repFacultades.jrxml";
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
				mostrarFacultad(txtBuscar.getText());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		
		btnBuscarTodos.setOnAction(event -> {
			try {
				mostrarFacultades();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
	};

	private void modificarFacultad() throws SQLException, ClassNotFoundException {
		try {
			if (idFacultad != null) {
				FacultadDAO.modificarFacultad(idFacultad, txtFacultad.getText(),
						txtDireccion.getText(), txtCuit.getText(), Integer.parseInt(txtSucursal.getText()),
						txtTelefonos.getText(), txtEmail.getText(), Boolean.parseBoolean(chbDefecto.getText()));
				mostrarDetallesFacultad(null);
				txtBuscar.requestFocus();
			} else {
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Error de SQL");
				alert2.setContentText("Seleccione una Facultad para modificarla");
				alert2.showAndWait();
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	private void mostrarFacultad(String cadena) throws SQLException, ClassNotFoundException {
		try {
			// Obtengo la información de todas las facultades (con %LIKE%)
			ObservableList<Facultad> facultadesData = FacultadDAO.buscarFacultad(cadena);
			//populateFacultades(facultadesData);
			tblFacultad.setItems(facultadesData);
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setTitle("Error de SQL");
			alert2.setContentText("Imposible mostrar las Facultades con ese criterio" + e);
			e.printStackTrace();
			alert2.showAndWait();
			// Devuelve la excepción
			throw e;
		}
	}

	/**
	 * Uso la variable de clase "idFacultad" obtenida del evento "setOnMouseClicked"
	 * de la tabla de Facultad
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unlikely-arg-type")
	private void borrarFacultad() throws SQLException, ClassNotFoundException {
		try {
			if (idFacultad != null) {
				FacultadDAO.borrarFacultad(idFacultad);
				inxTabla = tblFacultad.getSelectionModel().getSelectedIndex();
				tblFacultad.getItems().remove(inxTabla);
			} else {
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Error de SQL");
				alert2.setContentText("Seleccione una Facultad para borrarla");
				alert2.showAndWait();
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	private void mostrarFacultades() throws SQLException, ClassNotFoundException {
		try {
			// Obtengo la información de todas las facultades
			ObservableList<Facultad> facultadesData = FacultadDAO.buscarFacultades();
			tblFacultad.setItems(facultadesData);
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setTitle("Error de SQL");
			alert2.setContentText("Imposible mostrar las Facultades" + e);
			e.printStackTrace();
			alert2.showAndWait();
			// Devuelve la excepción
			throw e;
		}
	}

	/**
	 * Llena todos los campos de texto con los detalles de la facultad. Si se
	 * especifica que la facultad es null, todos los campos de texto son limpiados.
	 * 
	 * @param facultad 
	 * la facultad o null
	 */
	private void mostrarDetallesFacultad(Facultad facultad) {
		if (facultad != null) {
			txtFacultad.setText(facultad.getNombre());
			txtDireccion.setText(facultad.getDireccion());
			txtCuit.setText(facultad.getCuit());
			txtSucursal.setText(Integer.toString(facultad.getSucursal()));
			txtTelefonos.setText(facultad.getTelefono());
			txtEmail.setText(facultad.getCorreo());
			chbDefecto.setSelected(facultad.getDefecto());
		} else {
			// facultad es null, borramos todo el contenido.
			txtFacultad.clear();
			txtDireccion.clear();
			txtCuit.clear();
			txtSucursal.clear();
			txtTelefonos.clear();
			txtEmail.clear();
			chbDefecto.setSelected(false);
		}
	}

	private void insertarFacultad() throws SQLException, ClassNotFoundException {
		// Genero un Id aleatorio - NO es la manera correcta
		// función nos devuelve un número aleatorio entre 0 y 1 (de tipo double)
		// En este caso el número generado estará entre 1 y 10, por que primero lo 
		// multiplicamos por 10 con lo que tendríamos un número entre 0 y 9 y al 
		// sumarle 1 a este número tendremos finalmente un número entre 1 y 10.
		Integer idFacultad = (int)(Math.random() * 1500 + 1);
		
		// Inserto una Facultad en la BD
		try {
			FacultadDAO.insertarFacultad(idFacultad, txtFacultad.getText(), txtDireccion.getText(), txtCuit.getText(),
					Integer.parseInt(txtSucursal.getText()), txtTelefonos.getText(), txtEmail.getText(),
					Boolean.parseBoolean(chbDefecto.getText()));
			mostrarDetallesFacultad(null);
			txtFacultad.requestFocus();
		} 
		catch (SQLException e) {
			throw e;
		}
	};
}
package ar.frp.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Creado por bertHäuser el 20.10.2017. Esta clase maneja todos los campos de
 * las tablas Facturas y Facturas_Items. Contiene todos los métodos getters y
 * setters y las propiedades para todos los campos de la clase "modelo". La
 * Propiedad (Property) nos avisa cuando una variable de la clase cambia; esto
 * permite a la vista sincronizarse con los datos.
 */

public class Factura {
	// Declaro las columnas de la tabla FACTURAS
	// private IntegerProperty ID_FACTURA;
	private ObjectProperty<LocalDate> fechaFactura;
	private IntegerProperty idTercero;
	private IntegerProperty numero;

	/**
	 * Los datos son una lista observable de Items de Factura.
	 */
	private ObservableList<Items> itemsData = FXCollections.observableArrayList();

	// Constructor
	public Factura() {
		this.fechaFactura = new SimpleObjectProperty<LocalDate>();
		// new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21))

		this.numero = new SimpleIntegerProperty();
		this.idTercero = new SimpleIntegerProperty();
	}

	public ObservableList<Items> getListaItems() {
		return itemsData;
	}

	public void setItems(ObservableList<Items> Value) {
		this.itemsData = Value;
	}

	// getters, setters y propiedades
	// fechaFactura
	public LocalDate getFechaFactura() {
		return fechaFactura.get();
	}

	public void setFechaFactura(LocalDate Value) {
		fechaFactura.set(Value);
	}

	public ObjectProperty<LocalDate> fechaFacturaProperty() {
		return fechaFactura;
	}

	// numero
	public Integer getNumero() {
		return numero.get();
	}

	public void setNumero(Integer Valor) {
		numero.set(Valor);
	}

	public IntegerProperty numeroProperty() {
		return numero;
	}

	// idTercero
	public Integer getIdTercero() {
		return idTercero.get();
	}

	public void setIdTercero(Integer Valor) {
		idTercero.set(Valor);
	}

	public IntegerProperty IdTerceroProperty() {
		return idTercero;
	}

}

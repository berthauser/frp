package ar.frp.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tercero {
	// Declaro las columnas de la tabla Terceros
	private SimpleIntegerProperty id_tercero;
	private StringProperty nombre;
	private StringProperty cuitl;
	private StringProperty sitiva;
	private StringProperty direccion;
	private StringProperty localidad;
	private StringProperty provincia;
	private StringProperty telefono;
	private DoubleProperty saldo_apertura;
	private StringProperty tipo_saldo;

	/**
	 * Los datos son una lista observable de Terceros.
	 */
	private ObservableList<Tercero> terceroData = FXCollections.observableArrayList();

	public Tercero() {
		this.id_tercero = new SimpleIntegerProperty();
		this.nombre = new SimpleStringProperty();
		this.cuitl = new SimpleStringProperty();
		this.sitiva = new SimpleStringProperty();
		this.direccion = new SimpleStringProperty();
		this.localidad = new SimpleStringProperty();
		this.provincia = new SimpleStringProperty();
		this.telefono = new SimpleStringProperty();
		this.saldo_apertura = new SimpleDoubleProperty();
		this.tipo_saldo = new SimpleStringProperty();
	}

	public final int getId_tercero() {
		return id_terceroProperty().get();
	}

	public final void setId_tercero(int value) {
		id_terceroProperty().set(value);
	}

	public final SimpleIntegerProperty id_terceroProperty() {
		return id_tercero;
	}

	public final String getNombre() {
		return nombreProperty().get();
	}

	public final void setNombre(String value) {
		nombreProperty().set(value);
	}
	public StringProperty nombreProperty() {
		if (nombre == null) {
			nombre = new SimpleStringProperty();
		}
		return nombre;
	}

	public final String getCuitl() {
		return cuitlProperty().get();
	}

	public final void setCuitl(String value) {
		cuitlProperty().set(value);
	}

	public StringProperty cuitlProperty() {
		if (cuitl == null) {
			cuitl = new SimpleStringProperty();
		}
		return cuitl;
	}

	public final String getSitiva() {
		return sitivaProperty().get();
	}

	public final void setSitiva(String value) {
		sitivaProperty().set(value);
	}

	public StringProperty sitivaProperty() {
		if (sitiva == null) {
			sitiva = new SimpleStringProperty();
		}
		return sitiva;
	}

	public final String getDireccion() {
		return direccionProperty().get();
	}

	public final void setDireccion(String value) {
		direccionProperty().set(value);
	}

	public StringProperty direccionProperty() {
		if (direccion == null) {
			direccion = new SimpleStringProperty();
		}
		return direccion;
	}

	public final String getLocalidad() {
		return localidadProperty().get();
	}

	public final void setLocalidad(String value) {
		localidadProperty().set(value);
	}

	public StringProperty localidadProperty() {
		if (localidad == null)
			localidad = new SimpleStringProperty();
		return localidad;
	}

	public final String getProvincia() {
		return provinciaProperty().get();
	}

	public final void setProvincia(String value) {
		provincia.set(value);
	}

	public StringProperty provinciaProperty() {
		if (provincia == null)
			provincia = new SimpleStringProperty();
		return provincia;
	}

	public final String getTelefono() {
		return telefonoProperty().get();
	}

	public final void setTelefono(String value) {
		telefono.set(value);
	}

	public StringProperty telefonoProperty() {
		if (telefono == null)
			telefono = new SimpleStringProperty();
		return telefono;
	}

	public final double getSaldoApertura() {
		return saldoAperturaProperty().get();
	}

	public final void setSaldoApertura(double value) {
		saldo_apertura.set(value);
	}

	public DoubleProperty saldoAperturaProperty() {
		if (saldo_apertura == null)
			saldo_apertura = new SimpleDoubleProperty();
		return saldo_apertura;
	}

	public final String getTipoSaldo() {
		return tipoSaldoProperty().get();
	}

	public final void setTipoSaldo(String value) {
		tipo_saldo.set(value);
	}

	public StringProperty tipoSaldoProperty() {
		if (tipo_saldo == null)
			tipo_saldo = new SimpleStringProperty();
		return tipo_saldo;
	}

	public ObservableList<Tercero> getFacultadData() {
		return terceroData;
	}
}